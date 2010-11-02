package spl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import spl.listener.LabelLinkListener;
import spl.localization.LocalizationSupport;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private int result;
    private int dropRow;
    private String fileName;

    public ImportDialog(int dropRow, String fileName) {
        this.dropRow = dropRow;
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
        this.setTitle(LocalizationSupport.message("ImportDialog.Title"));
        setContentPane(contentPane);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
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
        this.$$$loadLabelText$$$(labelSubHeadline, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.SubHeadline"));
        panel2.add(labelSubHeadline, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 4, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1643275));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(600, -1), 0, false));
        labelHeadline = new JLabel();
        labelHeadline.setFont(new Font(labelHeadline.getFont().getName(), Font.BOLD, 14));
        this.$$$loadLabelText$$$(labelHeadline, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.Headline"));
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
        this.$$$loadButtonText$$$(buttonOK, ResourceBundle.getBundle("JabRef").getString("Ok"));
        panel5.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        this.$$$loadButtonText$$$(buttonCancel, ResourceBundle.getBundle("JabRef").getString("Cancel"));
        panel5.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(3, 1, new Insets(0, 5, 0, 5), -1, -1));
        contentPane.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 5, 0, 5), 0, -1));
        panel7.setFont(new Font(panel7.getFont().getName(), panel7.getFont().getStyle(), 11));
        panel6.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.SectionOne.Title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panel7.getFont().getName(), panel7.getFont().getStyle(), 12), new Color(-16777216)));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(3, 3, new Insets(10, 0, 0, 10), 2, -1));
        panel7.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        radioButtonXmp = new JRadioButton();
        radioButtonXmp.setFont(new Font(radioButtonXmp.getFont().getName(), radioButtonXmp.getFont().getStyle(), 13));
        this.$$$loadButtonText$$$(radioButtonXmp, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.ImportXMP"));
        panel8.add(radioButtonXmp, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer4 = new Spacer();
        panel8.add(spacer4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(100, -1), null, null, 0, false));
        radioButtonMrDlib = new JRadioButton();
        radioButtonMrDlib.setFont(new Font(radioButtonMrDlib.getFont().getName(), radioButtonMrDlib.getFont().getStyle(), 13));
        this.$$$loadButtonText$$$(radioButtonMrDlib, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.ImportMrDlib"));
        panel8.add(radioButtonMrDlib, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        radioButtonNoMeta = new JRadioButton();
        radioButtonNoMeta.setFont(new Font(radioButtonNoMeta.getFont().getName(), radioButtonNoMeta.getFont().getStyle(), 13));
        this.$$$loadButtonText$$$(radioButtonNoMeta, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.NoMetaData"));
        panel8.add(radioButtonNoMeta, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        labelMrDlib1 = new JLabel();
        labelMrDlib1.setFont(new Font(labelMrDlib1.getFont().getName(), Font.BOLD, 13));
        labelMrDlib1.setForeground(new Color(-16776961));
        labelMrDlib1.setHorizontalAlignment(2);
        labelMrDlib1.setHorizontalTextPosition(2);
        this.$$$loadLabelText$$$(labelMrDlib1, ResourceBundle.getBundle("spl/localization/spljabref").getString("MrDlib"));
        panel8.add(labelMrDlib1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel6.add(spacer5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelUpdateEntry = new JPanel();
        panelUpdateEntry.setLayout(new GridLayoutManager(2, 3, new Insets(0, 5, 0, 5), 3, -1));
        panelUpdateEntry.setFont(new Font(panelUpdateEntry.getFont().getName(), panelUpdateEntry.getFont().getStyle(), 11));
        panel6.add(panelUpdateEntry, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelUpdateEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.SectionTwo.Title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panelUpdateEntry.getFont().getName(), panelUpdateEntry.getFont().getStyle(), 12), new Color(-16777216)));
        radioButtononlyAttachPDF = new JRadioButton();
        radioButtononlyAttachPDF.setFont(new Font(radioButtononlyAttachPDF.getFont().getName(), radioButtononlyAttachPDF.getFont().getStyle(), 13));
        this.$$$loadButtonText$$$(radioButtononlyAttachPDF, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.attachPdf"));
        panelUpdateEntry.add(radioButtononlyAttachPDF, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        radioButtonUpdateEmptyFields = new JRadioButton();
        radioButtonUpdateEmptyFields.setFont(new Font(radioButtonUpdateEmptyFields.getFont().getName(), radioButtonUpdateEmptyFields.getFont().getStyle(), 13));
        this.$$$loadButtonText$$$(radioButtonUpdateEmptyFields, ResourceBundle.getBundle("spl/localization/spljabref").getString("importDialog.updateEntry"));
        panelUpdateEntry.add(radioButtonUpdateEmptyFields, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final Spacer spacer6 = new Spacer();
        panelUpdateEntry.add(spacer6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelMrDlib2 = new JLabel();
        labelMrDlib2.setFont(new Font(labelMrDlib2.getFont().getName(), Font.BOLD, 13));
        labelMrDlib2.setForeground(new Color(-16776961));
        labelMrDlib2.setHorizontalAlignment(10);
        labelMrDlib2.setHorizontalTextPosition(10);
        this.$$$loadLabelText$$$(labelMrDlib2, ResourceBundle.getBundle("spl/localization/spljabref").getString("MrDlib"));
        labelMrDlib2.setVerticalAlignment(0);
        panelUpdateEntry.add(labelMrDlib2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        contentPane.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
