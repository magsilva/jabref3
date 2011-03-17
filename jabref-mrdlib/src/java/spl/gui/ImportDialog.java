package spl.gui;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import net.sf.jabref.Globals;
import spl.listener.LabelLinkListener;
import spl.localization.LocalizationSupport;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ResourceBundle;

public class ImportDialog extends JDialog {
    private JPanel contentPane;
    private JLabel labelSubHeadline;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton radioButtonXmp;
    private JRadioButton radioButtonMrDlib;
    private JRadioButton radioButtonNoMeta;
    private JLabel labelHeadline;
    private JLabel labelFileName;
    private JRadioButton radioButtononlyAttachPDF;
    private JRadioButton radioButtonUpdateEmptyFields;
    private JPanel panelUpdateEntry;
    private JLabel labelMrDlib1;
    private JLabel labelMrDlib2;
    private JPanel panelNewEntry;
    private int result;
    private int dropRow;
    private String fileName;

    public ImportDialog(int dropRow, String fileName) {
        this.dropRow = dropRow;
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(-1643275));
        labelHeadline = new JLabel(Globals.lang("Import_Metadata_from:"));
        labelHeadline.setFont(new Font(labelHeadline.getFont().getName(), Font.BOLD, 14));
        labelSubHeadline = new JLabel(Globals.lang("Choose_the_source_for_the_metadata_import"));
        labelSubHeadline.setFont(new Font(labelSubHeadline.getFont().getName(), labelSubHeadline.getFont().getStyle(), 13));
        GridLayout gl = new GridLayout(2,1);
        gl.setVgap(10);
        gl.setHgap(10);
        panel3.setLayout(gl);
        panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel3.add(labelHeadline);
        panel3.add(labelSubHeadline);
        radioButtonNoMeta = new JRadioButton(Globals.lang("Create_blank_entry_linking_the_PDF"));
        radioButtonXmp = new JRadioButton(Globals.lang("Create_entry_based_on_XMP_data"));
        radioButtonMrDlib = new JRadioButton(Globals.lang("Create_entry_based_on_data_fetched_from"));
        radioButtononlyAttachPDF = new JRadioButton(Globals.lang("Only_attach_PDF"));
        radioButtonUpdateEmptyFields = new JRadioButton(Globals.lang("Update_empty_fields_with_data_fetched_from"));
        labelMrDlib1 = new JLabel("Mr._dLib");
        labelMrDlib1.setFont(new Font(labelMrDlib1.getFont().getName(), Font.BOLD, 13));
        labelMrDlib1.setForeground(new Color(-16776961));
        labelMrDlib2 = new JLabel("Mr._dLib");
        labelFileName = new JLabel();
        buttonOK = new JButton(Globals.lang("Ok"));
        buttonCancel = new JButton(Globals.lang("Cancel"));
        DefaultFormBuilder b = new DefaultFormBuilder(new FormLayout("left:pref, 4dlu, left:pref",""));
        b.appendSeparator(Globals.lang("Create New Entry"));
        b.append(radioButtonNoMeta, 3);
        b.append(radioButtonXmp, 3);
        b.append(radioButtonMrDlib);
        b.append(labelMrDlib2);
        b.appendSeparator(Globals.lang("Update_Existing_Entry"));
        b.append(radioButtononlyAttachPDF, 3);
        b.append(radioButtonUpdateEmptyFields, 3);
        b.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        ButtonBarBuilder bb = new ButtonBarBuilder();
        bb.addGlue();
        bb.addGridded(buttonOK);
        bb.addGridded(buttonCancel);
        bb.addGlue();
        bb.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        contentPane.add(panel3, BorderLayout.NORTH);
        contentPane.add(b.getPanel(), BorderLayout.CENTER);
        contentPane.add(bb.getPanel(), BorderLayout.SOUTH);

        //$$$setupUI$$$();
        //this.setText();
        if (this.dropRow < 0) {
            this.radioButtononlyAttachPDF.setEnabled(false);
            this.radioButtonUpdateEmptyFields.setEnabled(false);
            this.labelMrDlib2.setEnabled(false);
        }
        this.fileName = fileName;
        String name = new File(this.fileName).getName();
        if (name.length() < 34) {
            this.labelFileName.setText(name);
        } else {
            this.labelFileName.setText(new File(this.fileName).getName().substring(0, 33) + "...");
        }
        this.labelMrDlib1.addMouseListener(new LabelLinkListener(this.labelMrDlib1, "www.mr-dlib.org/docs/pdf_metadata_extraction.php"));
        this.labelMrDlib2.addMouseListener(new LabelLinkListener(this.labelMrDlib2, "www.mr-dlib.org/docs/pdf_metadata_extraction.php"));
        this.setTitle(LocalizationSupport.message("Import_Metadata_From_PDF"));

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        radioButtonXmp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onXmp();
            }
        });

        radioButtonMrDlib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMrDlib();
            }
        });

        radioButtonNoMeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNoMeta();
            }
        });

        radioButtononlyAttachPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAttachPDF();
            }
        });

        radioButtonUpdateEmptyFields.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onUpdateEntry();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.radioButtonMrDlib.setSelected(true);
        this.radioButtonMrDlib.requestFocus();
        this.setSize(555, 371);
    }

    private void onOK() {
        this.result = JOptionPane.OK_OPTION;
        dispose();
    }

    private void onCancel() {
        this.result = JOptionPane.CANCEL_OPTION;
        dispose();
    }

    private void onXmp() {
        this.setSelection(this.radioButtonXmp);
    }

    private void onAttachPDF() {
        this.setSelection(this.radioButtononlyAttachPDF);
    }

    private void onUpdateEntry() {
        this.setSelection(this.radioButtonUpdateEmptyFields);
    }

    private void onMrDlib() {
        this.setSelection(this.radioButtonMrDlib);
    }

    private void onNoMeta() {
        this.setSelection(this.radioButtonNoMeta);
    }

    private void setSelection(JRadioButton button) {
        if (button != this.radioButtonMrDlib) {
            this.radioButtonMrDlib.setSelected(false);
        }
        if (button != this.radioButtonUpdateEmptyFields) {
            this.radioButtonUpdateEmptyFields.setSelected(false);
        }
        if (button != this.radioButtononlyAttachPDF) {
            this.radioButtononlyAttachPDF.setSelected(false);
        }
        if (button != this.radioButtonXmp) {
            this.radioButtonXmp.setSelected(false);
        }
        if (button != this.radioButtonNoMeta) {
            this.radioButtonNoMeta.setSelected(false);
        }
    }

    public void showDialog() {
        this.pack();
        this.setVisible(true);
    }

    public JRadioButton getRadioButtonXmp() {
        return radioButtonXmp;
    }

    public JRadioButton getRadioButtonMrDlib() {
        return radioButtonMrDlib;
    }

    public JRadioButton getRadioButtonNoMeta() {
        return radioButtonNoMeta;
    }

    public JRadioButton getRadioButtononlyAttachPDF() {
        return radioButtononlyAttachPDF;
    }

    public JRadioButton getRadioButtonUpdateEmptyFields() {
        return radioButtonUpdateEmptyFields;
    }

    public int getResult() {
        return result;
    }

    private void setText() {
        this.labelHeadline.setText(LocalizationSupport.message("Import_Metadata_from:"));
        this.labelSubHeadline.setText(LocalizationSupport.message("Choose_the_source_for_the_metadata_import"));
        this.buttonOK.setText(LocalizationSupport.message("Ok"));
        this.buttonCancel.setText(LocalizationSupport.message("Cancel"));
        this.radioButtonXmp.setText(LocalizationSupport.message("Create_entry_based_on_XMP_data"));
        this.radioButtonUpdateEmptyFields.setText(LocalizationSupport.message("Update_empty_fields_with_data_fetched_from"));
        this.radioButtonMrDlib.setText(LocalizationSupport.message("Create_entry_based_on_data_fetched_from"));
        this.radioButtonNoMeta.setText(LocalizationSupport.message("Create_blank_entry_linking_the_PDF"));
        this.radioButtononlyAttachPDF.setText(LocalizationSupport.message("Only_attach_PDF"));
        this.labelMrDlib1.setText(LocalizationSupport.message("Mr._dLib"));
        this.labelMrDlib2.setText(LocalizationSupport.message("Mr._dLib"));
        this.panelNewEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LocalizationSupport.message("Create_New_Entry"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panelNewEntry.getFont().getName(), panelNewEntry.getFont().getStyle(), 12), new Color(-16777216)));
        panelUpdateEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LocalizationSupport.message("Update_Existing_Entry"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panelUpdateEntry.getFont().getName(), panelUpdateEntry.getFont().getStyle(), 12), new Color(-16777216)));
    }


    private void createUIComponents() {
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    /*private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(5, 0, 20, 0), -1, -1));
        panel1.setBackground(new Color(-1643275));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(5, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1643275));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelSubHeadline = new JLabel();
        labelSubHeadline.setFont(new Font(labelSubHeadline.getFont().getName(), labelSubHeadline.getFont().getStyle(), 13));
        labelSubHeadline.setText("");
        panel2.add(labelSubHeadline, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 4, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1643275));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(600, -1), 0, false));
        labelHeadline = new JLabel();
        labelHeadline.setFont(new Font(labelHeadline.getFont().getName(), Font.BOLD, 14));
        labelHeadline.setText("");
        panel3.add(labelHeadline, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        labelFileName = new JLabel();
        labelFileName.setFont(new Font(labelFileName.getFont().getName(), Font.BOLD, 14));
        labelFileName.setText("[fileName]");
        panel3.add(labelFileName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(300, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel4.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 10, 10), -1, -1, true, false));
        panel4.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("");
        panel5.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("");
        panel5.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(3, 1, new Insets(0, 5, 0, 5), -1, -1));
        contentPane.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelNewEntry = new JPanel();
        panelNewEntry.setLayout(new GridLayoutManager(1, 1, new Insets(0, 5, 0, 5), 0, -1));
        panelNewEntry.setFont(new Font(panelNewEntry.getFont().getName(), panelNewEntry.getFont().getStyle(), 11));
        panel6.add(panelNewEntry, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelNewEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panelNewEntry.getFont().getName(), panelNewEntry.getFont().getStyle(), 12), new Color(-16777216)));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(3, 3, new Insets(10, 0, 0, 10), 2, -1));
        panelNewEntry.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        radioButtonXmp = new JRadioButton();
        radioButtonXmp.setFont(new Font(radioButtonXmp.getFont().getName(), radioButtonXmp.getFont().getStyle(), 13));
        radioButtonXmp.setText("");
        panel7.add(radioButtonXmp, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer4 = new Spacer();
        panel7.add(spacer4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(100, -1), null, null, 0, false));
        radioButtonMrDlib = new JRadioButton();
        radioButtonMrDlib.setFont(new Font(radioButtonMrDlib.getFont().getName(), radioButtonMrDlib.getFont().getStyle(), 13));
        radioButtonMrDlib.setText("");
        panel7.add(radioButtonMrDlib, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        radioButtonNoMeta = new JRadioButton();
        radioButtonNoMeta.setFont(new Font(radioButtonNoMeta.getFont().getName(), radioButtonNoMeta.getFont().getStyle(), 13));
        radioButtonNoMeta.setText("");
        panel7.add(radioButtonNoMeta, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        labelMrDlib1 = new JLabel();
        labelMrDlib1.setFont(new Font(labelMrDlib1.getFont().getName(), Font.BOLD, 13));
        labelMrDlib1.setForeground(new Color(-16776961));
        labelMrDlib1.setHorizontalAlignment(2);
        labelMrDlib1.setHorizontalTextPosition(2);
        labelMrDlib1.setText("");
        panel7.add(labelMrDlib1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel6.add(spacer5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelUpdateEntry = new JPanel();
        panelUpdateEntry.setLayout(new GridLayoutManager(2, 3, new Insets(0, 5, 0, 5), 3, -1));
        panelUpdateEntry.setFont(new Font(panelUpdateEntry.getFont().getName(), panelUpdateEntry.getFont().getStyle(), 11));
        panel6.add(panelUpdateEntry, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelUpdateEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panelUpdateEntry.getFont().getName(), panelUpdateEntry.getFont().getStyle(), 12), new Color(-16777216)));
        radioButtononlyAttachPDF = new JRadioButton();
        radioButtononlyAttachPDF.setFont(new Font(radioButtononlyAttachPDF.getFont().getName(), radioButtononlyAttachPDF.getFont().getStyle(), 13));
        radioButtononlyAttachPDF.setText("");
        panelUpdateEntry.add(radioButtononlyAttachPDF, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        radioButtonUpdateEmptyFields = new JRadioButton();
        radioButtonUpdateEmptyFields.setFont(new Font(radioButtonUpdateEmptyFields.getFont().getName(), radioButtonUpdateEmptyFields.getFont().getStyle(), 13));
        radioButtonUpdateEmptyFields.setText("");
        panelUpdateEntry.add(radioButtonUpdateEmptyFields, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer6 = new Spacer();
        panelUpdateEntry.add(spacer6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelMrDlib2 = new JLabel();
        labelMrDlib2.setFont(new Font(labelMrDlib2.getFont().getName(), Font.BOLD, 13));
        labelMrDlib2.setForeground(new Color(-16776961));
        labelMrDlib2.setHorizontalAlignment(10);
        labelMrDlib2.setHorizontalTextPosition(10);
        labelMrDlib2.setText("");
        labelMrDlib2.setVerticalAlignment(0);
        panelUpdateEntry.add(labelMrDlib2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        contentPane.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
    } */

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
