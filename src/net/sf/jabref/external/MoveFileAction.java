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
import net.sf.jabref.gui.FileListEditor;
import net.sf.jabref.gui.FileListEntry;
import net.sf.jabref.gui.FileDialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Action for moving or renaming a file that is linked to from an entry in JabRef.
 */
public class MoveFileAction extends AbstractAction {
    private JabRefFrame frame;
    private EntryEditor eEditor;
    private FileListEditor editor;
    private boolean toFileDir;

    public MoveFileAction(JabRefFrame frame, EntryEditor eEditor, FileListEditor editor,
                          boolean toFileDir) {
        this.frame = frame;
        this.eEditor = eEditor;
        this.editor = editor;
        this.toFileDir = toFileDir;
    }

    public void actionPerformed(ActionEvent event) {
        int selected = editor.getSelectedRow();
        if (selected == -1)
            return;
        FileListEntry flEntry = editor.getTableModel().getEntry(selected);
        // Check if the current file exists:
        String ln = flEntry.getLink();
        boolean httpLink = ln.toLowerCase().startsWith("http");
        if (httpLink) {
            // TODO: notify that this operation cannot be done on remote links

        }

        // Get an absolute path representation:
        String[] dirs = frame.basePanel().metaData().getFileDirectory(BibtexFieldManager.FILE_FIELD);
        int found = -1;
        for (int i=0; i<dirs.length; i++)
            if (new File(dirs[i]).exists()) {
                found = i;
                break;
            }
        if (found < 0) {
            JOptionPane.showMessageDialog(frame, Globals.lang("File_directory_is_not_set_or_does_not_exist!"),
                    Globals.lang("Move/Rename file"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        File file = new File(ln);
        if (!file.isAbsolute()) {
            file = Util.expandFilename(ln, dirs);
        }
        if ((file != null) && file.exists()) {
            // Ok, we found the file. Now get a new name:
            String extension = null;
            if (flEntry.getType() != null)
                extension = "." + flEntry.getType().getExtension();

            File newFile = null;
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                String chosenFile;
                if (toFileDir) {
                    // Determine which name to suggest:
                    String suggName = Util.getLinkedFileName(eEditor.getDatabase(), eEditor.getEntry()).
                            concat(".").concat(flEntry.getType().extension);
                    CheckBoxMessage cbm = new CheckBoxMessage(Globals.lang("Move file to file directory?"),
                            Globals.lang("Rename to '%0'",suggName),
                            Globals.prefs.getBoolean("renameOnMoveFileToFileDir"));
                    int answer;
                    // Only ask about renaming file if the file doesn't have the proper name already:
                    if (!suggName.equals(file.getName()))
                        answer = JOptionPane.showConfirmDialog(frame, cbm, Globals.lang("Move/Rename file"),
                                JOptionPane.YES_NO_OPTION);
                    else
                        answer = JOptionPane.showConfirmDialog(frame, Globals.lang("Move file to file directory?"),
                                Globals.lang("Move/Rename file"), JOptionPane.YES_NO_OPTION);
                    if (answer != JOptionPane.YES_OPTION)
                        return;
                    Globals.prefs.putBoolean("renameOnMoveFileToFileDir", cbm.isSelected());
                    StringBuilder sb = new StringBuilder(dirs[found]);
                    if (!dirs[found].endsWith(File.separator))
                        sb.append(File.separator);
                    if (cbm.isSelected()) {
                        // Rename:
                        sb.append(suggName);
                    }
                    else {
                        // Do not rename:
                        sb.append(file.getName());
                    }
                    chosenFile = sb.toString();
                } else {
                    chosenFile = FileDialogs.getNewFile(frame, file, extension, JFileChooser.SAVE_DIALOG, false);
                }
                if (chosenFile == null) {
                    return; // cancelled
                }
                newFile = new File(chosenFile);
                // Check if the file already exists:
                if (newFile.exists() && (JOptionPane.showConfirmDialog
                        (frame, "'" + newFile.getName() + "' " + Globals.lang("exists. Overwrite file?"),
                                Globals.lang("Move/Rename file"), JOptionPane.OK_CANCEL_OPTION)
                        != JOptionPane.OK_OPTION)) {
                    if (!toFileDir)
                        repeat = true;
                    else
                        return;
                }
            }

            if (!newFile.equals(file)) {
                try {
                    boolean success = file.renameTo(newFile);
                    if (!success) {
                        success = Util.copyFile(file, newFile, true);
                    }
                    if (success) {
                        // Remove the original file:
                        file.delete();
                        // Relativise path, if possible.
                        String canPath = (new File(dirs[found])).getCanonicalPath();
                        if (newFile.getCanonicalPath().startsWith(canPath)) {
                            if ((newFile.getCanonicalPath().length() > canPath.length()) &&
                                    (newFile.getCanonicalPath().charAt(canPath.length()) == File.separatorChar))
                                flEntry.setLink(newFile.getCanonicalPath().substring(1+canPath.length()));
                            else
                                flEntry.setLink(newFile.getCanonicalPath().substring(canPath.length()));


                        }
                        else
                            flEntry.setLink(newFile.getCanonicalPath());
                        eEditor.updateField(editor);
                        //JOptionPane.showMessageDialog(frame, Globals.lang("File moved"),
                        //        Globals.lang("Move/Rename file"), JOptionPane.INFORMATION_MESSAGE);
                        frame.output(Globals.lang("File moved"));
                    } else {
                        JOptionPane.showMessageDialog(frame, Globals.lang("Move file failed"),
                                Globals.lang("Move/Rename file"), JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SecurityException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, Globals.lang("Could not move file") + ": " + ex.getMessage(),
                            Globals.lang("Move/Rename file"), JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, Globals.lang("Could not move file") + ": " + ex.getMessage(),
                            Globals.lang("Move/Rename file"), JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        else {

            // File doesn't exist, so we can't move it.
            JOptionPane.showMessageDialog(frame, Globals.lang("Could not find file '%0'.", flEntry.getLink()),
                    Globals.lang("File not found"), JOptionPane.ERROR_MESSAGE);
            
        }

    }
}
