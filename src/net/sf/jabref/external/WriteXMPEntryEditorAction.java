/*  Copyright (C) 2003-2011 JabRef contributors.
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package net.sf.jabref.external;

import net.sf.jabref.*;
import net.sf.jabref.util.XMPUtil;
import net.sf.jabref.gui.FileListTableModel;
import net.sf.jabref.gui.FileListEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * Write XMP action for EntryEditor toolbar.
 */
public class WriteXMPEntryEditorAction extends AbstractAction {
    private BasePanel panel;
    private EntryEditor editor;
    private String message = null;

    public WriteXMPEntryEditorAction(BasePanel panel, EntryEditor editor) {
        this.panel = panel;
        this.editor = editor;
        putValue(NAME, Globals.lang("Write XMP"));
        putValue(SMALL_ICON, GUIGlobals.getImage("pdfSmall"));
        putValue(SHORT_DESCRIPTION, Globals.lang("Write BibtexEntry as XMP-metadata to PDF."));
    }

    public void actionPerformed(ActionEvent actionEvent) {
        setEnabled(false);
        panel.output(Globals.lang("Writing XMP metadata..."));
        panel.frame().setProgressBarIndeterminate(true);
        panel.frame().setProgressBarVisible(true);
        BibtexEntry entry = editor.getEntry();

        // Make a list of all PDFs linked from this entry:
        List<File> files = new ArrayList<File>();
        String[] dirs = panel.metaData().getFileDirectory(BibtexFieldManager.FILE_FIELD);
        File f;
        String field = entry.getField(BibtexFieldManager.FILE_FIELD);
        if (field != null) {
            FileListTableModel tm = new FileListTableModel();
            tm.setContent(field);
            for (int j = 0; j < tm.getRowCount(); j++) {
                FileListEntry flEntry = tm.getEntry(j);
                if ((flEntry.getType() != null) && (flEntry.getType().getName().toLowerCase().equals("pdf"))) {
                    f = Util.expandFilename(flEntry.getLink(), dirs);
                    if (f != null)
                        files.add(f);
                }
            }
        }

        // We want to offload the actual work to a background thread, so we have a worker
        // thread:
        AbstractWorker worker = new WriteXMPWorker(files, entry);
        // Using Spin, we get a thread that gets synchronously offloaded to a new thread,
        // blocking the execution of this method:
        worker.getWorker().run();
        // After the worker thread finishes, we are unblocked and ready to print the
        // status message:
        panel.output(message);
        panel.frame().setProgressBarVisible(false);
        setEnabled(true);
    }


    class WriteXMPWorker extends AbstractWorker {

        private List<File> files;
        private BibtexEntry entry;

        public WriteXMPWorker(List<File> files, BibtexEntry entry) {

            this.files = files;
            this.entry = entry;
        }

        public void run() {
            if (files.size() == 0) {
                message = Globals.lang("No PDF linked") + ".\n";
            } else {
                int written = 0, error = 0;
                for (File file : files) {
                    if (!file.exists()) {
                        if (files.size() == 1)
                            message = Globals.lang("PDF does not exist");
                        error++;

                    } else {
                        try {
                            XMPUtil.writeXMP(file, entry, panel.database());
                            if (files.size() == 1)
                                message = Globals.lang("Wrote XMP-metadata");
                            written++;
                        } catch (Exception e) {
                            if (files.size() == 1)
                                message = Globals.lang("Error while writing") + " '" + file.getPath() + "'";
                            error++;

                        }
                    }
                }
                if (files.size() > 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Globals.lang("Finished writing XMP-metadata. Wrote to %0 file(s).",
                            String.valueOf(written)));
                    if (error > 0)
                        sb.append(" " + Globals.lang("Error writing to %0 file(s).", String.valueOf(error)));
                    message = sb.toString();
                }
            }
        }
    }
}
