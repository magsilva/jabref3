package spl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.sun.awt.AWTUtilities;
import org.sciplore.xml.XmlDocument;
import org.sciplore.xml.XmlDocuments;
import spl.DocumentsWrapper;
import spl.SplWebClient;
import spl.listener.LabelLinkListener;
import spl.localization.LocalizationSupport;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class MetaDataListDialog extends JDialog {
    private JPanel contentPane;
    private JTable tableMetadata;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane scrollPane;
    private JPanel panelWait;
    private JLabel labelFetch;
    private JLabel iconLabel;
    private JButton blankButton;
    private JLabel labelLogo;
    private JButton moreInformationButton;
    private DefaultTableModel tableModel;
    private int result;
    private XmlDocuments xmlDocuments;
    private String fileName;
    private SplWebClient.WebServiceStatus webserviceStatus;
    private Component thisDialog;
    private boolean showBlankButton;


    public MetaDataListDialog(String fileName, boolean showBlankButton) {
        $$$setupUI$$$();
        this.showBlankButton = showBlankButton;
        this.thisDialog = this;
        this.fileName = fileName;
        this.labelLogo.addMouseListener(new LabelLinkListener(this.labelLogo, "www.mr-dlib.org"));
        this.setTitle(LocalizationSupport.message("MetadataDialog.Title"));
        this.tableMetadata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        blankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBlank();
            }
        });

        moreInformationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onInfo();
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


        this.scrollPane.setVisible(false);
        this.blankButton.setVisible(false);
        this.moreInformationButton.setVisible(false);
        this.setSize(616, 366);
    }

    private void onInfo() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("www.mr-dlib.org/docs/pdf_metadata_extraction.php");
            desktop.browse(uri);
        } catch (MalformedURLException e1) {
            //Todo logging
        } catch (IOException e1) {
            //Todo logging
        } catch (URISyntaxException e1) {
            //Todo logging
        }
    }

    private void onBlank() {
        this.result = JOptionPane.NO_OPTION;
        dispose();
    }

    private void onOK() {
        this.result = JOptionPane.OK_OPTION;
        dispose();
    }

    private void onCancel() {
        this.result = JOptionPane.CANCEL_OPTION;
        dispose();
    }

    public void showDialog() {
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                System.out.println("Starting Webclient...");
                webserviceStatus = SplWebClient.getMetaData(new File(fileName));
                return null;
            }

            @Override
            public void done() {
                if (webserviceStatus == SplWebClient.WebServiceStatus.OK) {
                    xmlDocuments = SplWebClient.metadata;
                    if (xmlDocuments != null && xmlDocuments.getDocuments() != null && xmlDocuments.getDocuments().size() > 0) {
                        DocumentsWrapper documents = new DocumentsWrapper(xmlDocuments);
                        List<Vector> vectorList = documents.getDocuments();
                        for (Vector vector : vectorList) {
                            tableModel.addRow(vector);
                        }

                        tableMetadata.getSelectionModel().setSelectionInterval(0, 0);
                        panelWait.setVisible(false);
                        scrollPane.setVisible(true);
                        moreInformationButton.setVisible(true);
                    } else {
                        iconLabel.setVisible(false);
                        labelFetch.setText(LocalizationSupport.message("MetadataDialog.NoMetadata"));
                        blankButton.setVisible(showBlankButton);
                    }
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.NO_METADATA) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("MetadataDialog.NoMetadata"));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.UNAVAILABLE) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("MetadataDialog.ServiceUnavailable"));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.OUTDATED) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("MetadataDialog.ServiceOutdated"));
                    blankButton.setVisible(showBlankButton);
                    JOptionPane.showMessageDialog(thisDialog, LocalizationSupport.message("MetadataDialog.MessageBoxText"), LocalizationSupport.message("MetadataDialog.MessageBoxHeadline"), JOptionPane.INFORMATION_MESSAGE);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.WEBSERVICE_DOWN) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("MetadataDialog.ServiceDown"));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.NO_INTERNET) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("MetadataDialog.NoInternet"));
                    blankButton.setVisible(showBlankButton);
                    JOptionPane.showMessageDialog(thisDialog, LocalizationSupport.message("MetadataDialog.NoInternetMessage"), LocalizationSupport.message("MetadataDialog.NoInternet"), JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        worker.execute();
        this.pack();
        this.setVisible(true);
    }

    public XmlDocuments getXmlDocuments() {
        return xmlDocuments;
    }

    private void createUIComponents() {
        this.tableModel = new MyTableModel();
        this.tableModel.addColumn(LocalizationSupport.message("MetadataDialog.List.HeaderTitle"));
        this.tableModel.addColumn(LocalizationSupport.message("MetadataDialog.List.HeaderAuthors"));
        this.tableModel.addColumn(LocalizationSupport.message("MetadataDialog.List.HeaderPubYear"));
        this.tableMetadata = new JTable(this.tableModel);
    }

    public JTable getTableMetadata() {
        return tableMetadata;
    }

    public int getResult() {
        return result;
    }

    public JButton getBlankButton() {
        return blankButton;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(3, 5, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelLogo = new JLabel();
        labelLogo.setIcon(new ImageIcon(getClass().getResource("/spl/gui/mrdlib header.png")));
        labelLogo.setText("");
        panel2.add(labelLogo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 10, 5), -1, -1));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(600, 200), new Dimension(600, 200), null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Metadata"));
        scrollPane = new JScrollPane();
        panel3.add(scrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tableMetadata.setAutoCreateRowSorter(false);
        tableMetadata.setEnabled(true);
        tableMetadata.setFillsViewportHeight(true);
        tableMetadata.setShowVerticalLines(true);
        scrollPane.setViewportView(tableMetadata);
        panelWait = new JPanel();
        panelWait.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelWait.setBackground(new Color(-1));
        panel3.add(panelWait, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelWait.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        iconLabel = new JLabel();
        iconLabel.setBackground(new Color(-1));
        iconLabel.setHorizontalAlignment(0);
        iconLabel.setHorizontalTextPosition(11);
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/spl/gui/ajax-loader.gif")));
        iconLabel.setText("");
        panelWait.add(iconLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelFetch = new JLabel();
        labelFetch.setFont(new Font(labelFetch.getFont().getName(), labelFetch.getFont().getStyle(), 13));
        labelFetch.setText("Fetching Metadata...");
        panelWait.add(labelFetch, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 10, 10), -1, -1));
        panel4.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        this.$$$loadButtonText$$$(buttonOK, ResourceBundle.getBundle("JabRef").getString("Ok"));
        panel5.add(buttonOK, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        this.$$$loadButtonText$$$(buttonCancel, ResourceBundle.getBundle("JabRef").getString("Cancel"));
        panel5.add(buttonCancel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blankButton = new JButton();
        this.$$$loadButtonText$$$(blankButton, ResourceBundle.getBundle("spl/localization/spljabref").getString("ImportDialog.BlankEntry"));
        panel5.add(blankButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moreInformationButton = new JButton();
        this.$$$loadButtonText$$$(moreInformationButton, ResourceBundle.getBundle("spl/localization/spljabref").getString("MetadataDialog.MoreInfo"));
        panel5.add(moreInformationButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        iconLabel.setLabelFor(scrollPane);
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

    public class MyTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

}
