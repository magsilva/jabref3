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
import net.sf.jabref.undo.UndoableFieldChange;
import net.sf.jabref.gui.FileListEntry;
import net.sf.jabref.gui.FileListTableModel;

import javax.swing.*;
import java.io.IOException;

/**
 * Try to download fulltext PDF for selected entry(ies) by following URL or DOI link.
*/
public class FindFullTextAction extends AbstractWorker {
    private BasePanel basePanel;
    private BibtexEntry entry = null;
    private FindFullText.FindResult result = null;

    public FindFullTextAction(BasePanel basePanel) {
        this.basePanel = basePanel;
    }

    public void init() throws Throwable {
        basePanel.output(Globals.lang("Looking for full text document..."));
    }

    public void run() {
        entry = basePanel.getSelectedEntries()[0];
        FindFullText fft = new FindFullText();
        result = fft.findFullText(entry);
    }

    public void update() {
        //pdfURL = new URL("http://geog-www.sbs.ohio-state.edu/faculty/bmark/abbott_etal_ppp03.pdf");
        if (result.url != null) {
            //System.out.println("PDF URL: "+result.url);
            String bibtexKey = entry.getCiteKey();
            String[] dirs = basePanel.metaData().getFileDirectory(BibtexFieldManager.FILE_FIELD);
            if (dirs.length == 0) {
                // TODO: error message if file dir not defined
                //JOptionPane.showMessageDialog(frame, Globals.lang);
                return;
            }
            DownloadExternalFile def = new DownloadExternalFile(basePanel.frame(), basePanel.metaData(),
                    bibtexKey);
            try {
                def.download(result.url, new DownloadExternalFile.DownloadCallback() {
                    public void downloadComplete(FileListEntry file) {
                        System.out.println("finished");
                        FileListTableModel tm = new FileListTableModel();
                        String oldValue = entry.getField(BibtexFieldManager.FILE_FIELD);
                        tm.setContent(oldValue);
                        tm.addEntry(tm.getRowCount(), file);
                        String newValue = tm.getStringRepresentation();
                        UndoableFieldChange edit = new UndoableFieldChange(entry,
                                BibtexFieldManager.FILE_FIELD, oldValue, newValue);
                        entry.setField(BibtexFieldManager.FILE_FIELD, newValue);
                        basePanel.undoManager.addEdit(edit);
                        basePanel.markBaseChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            basePanel.output(Globals.lang("Finished downloading full text document"));
        }
        else {
            String message = null;
            switch (result.status) {
                case FindFullText.UNKNOWN_DOMAIN:
                    message = Globals.lang("Unable to find full text article. No search algorithm "
                        +"defined for the '%0' web site.", result.host);
                    break;
                case FindFullText.WRONG_MIME_TYPE:
                    message = Globals.lang("Found pdf link, but received the wrong MIME type. "
                        +"This could indicate that you don't have access to the fulltext article.");
                    break;
                case FindFullText.LINK_NOT_FOUND:
                    message = Globals.lang("Unable to find full text document in the linked web page.");
                    break;
                case FindFullText.IO_EXCEPTION:
                    message = Globals.lang("Connection error when trying to find full text document.");
                    break;
                case FindFullText.NO_URLS_DEFINED:
                    message = Globals.lang("This entry provides no URL or DOI links.");
                    break;

            }
            basePanel.output(Globals.lang("Full text article download failed"));
            JOptionPane.showMessageDialog(basePanel.frame(), message, Globals.lang("Full text article download failed"),
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
