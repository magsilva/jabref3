package spl.gui;


import com.jgoodies.forms.builder.ButtonBarBuilder;
import net.sf.jabref.Globals;
import net.sf.jabref.MetaData;
import net.sf.jabref.Util;
import org.sciplore.xml.XmlDocuments;
import spl.DocumentsWrapper;
import spl.SplWebClient;
import spl.Tools;
import spl.listener.LabelLinkListener;
import spl.localization.LocalizationSupport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JPanel panelMetadata;
    private DefaultTableModel tableModel;
    private int result;
    private XmlDocuments xmlDocuments;
    private String fileName;
    private SplWebClient.WebServiceStatus webserviceStatus;
    private Component thisDialog;
    private boolean showBlankButton;
    private CardLayout cardLayou = new CardLayout();

    public static void main(String[] args) {
        MetaDataListDialog diag = new MetaDataListDialog("test", false);
        diag.setVisible(true);
    }

    public MetaDataListDialog(String fileName, boolean showBlankButton) {
        $$$setupUI$$$();
        this.setText();
        this.showBlankButton = showBlankButton;
        this.thisDialog = this;
        this.fileName = fileName;
        this.labelLogo.addMouseListener(new LabelLinkListener(this.labelLogo, "www.mr-dlib.org"));
        this.setTitle(LocalizationSupport.message("Mr._dLib_Metadata_Entries_Associated_With_PDF_File"));
        this.tableMetadata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setContentPane(contentPane);
        pack();
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

        cardLayou.show(panelMetadata, "panelWait");
        //this.scrollPane.setVisible(false);
        //this.blankButton.setVisible(false);
        this.moreInformationButton.setVisible(true);
        this.setSize(616, 366);
    }

    private void onInfo() {
        try {
            Util.openExternalViewer(new MetaData(), "http://www.mr-dlib.org/docs/jabref_metadata_extraction_alpha.php", "url");
        } catch (IOException exc) {
            exc.printStackTrace();
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
                        cardLayou.show(panelMetadata, "scrollPane");
                        //panelWait.setVisible(false);
                        //scrollPane.setVisible(true);
                        moreInformationButton.setVisible(true);
                    } else {
                        iconLabel.setVisible(false);
                        labelFetch.setText(LocalizationSupport.message("No_metadata_found."));
                        blankButton.setVisible(showBlankButton);
                    }
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.NO_METADATA) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("No_metadata_found."));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.UNAVAILABLE) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("Mr._dLib_web_service_is_temporarily_unavailable."));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.OUTDATED) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("The_Mr._dLib_web_service_version_you_trying_to_access_is_outdated."));
                    blankButton.setVisible(showBlankButton);
                    JOptionPane.showMessageDialog(thisDialog, LocalizationSupport.message("This_JabRef_version_is_trying_to_access_an_old_version_of_Mr._dLib's_webservice_that_is_not_working_any_more.\\nPlease_visit_http://jabref.sourceforge.net_or_http://www.mr-dlib.org_for_more_information_and_updates.\\n\\n\\n"), LocalizationSupport.message("Web_Service_Version_Outdated"), JOptionPane.INFORMATION_MESSAGE);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.WEBSERVICE_DOWN) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("Mr._dLib_web_service_is_temporarily_down._Please_try_again_later."));
                    blankButton.setVisible(showBlankButton);
                }
                if (webserviceStatus == SplWebClient.WebServiceStatus.NO_INTERNET) {
                    iconLabel.setVisible(false);
                    labelFetch.setText(LocalizationSupport.message("No_Internet_Connection."));
                    blankButton.setVisible(showBlankButton);
                    JOptionPane.showMessageDialog(thisDialog, LocalizationSupport.message("You_are_not_connected_to_the_Internet._To_access_Mr._dLib_web_service_an_internet_connection_is_needed."), LocalizationSupport.message("No_Internet_Connection."), JOptionPane.INFORMATION_MESSAGE);
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
        this.tableModel.addColumn(LocalizationSupport.message("Title"));
        this.tableModel.addColumn(LocalizationSupport.message("Author(s)"));
        this.tableModel.addColumn(LocalizationSupport.message("Published_Year"));
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

    private void setText() {
        this.buttonOK.setText(LocalizationSupport.message("Ok"));
        this.buttonCancel.setText(LocalizationSupport.message("Cancel"));
        this.blankButton.setText(LocalizationSupport.message("Create_new_blank_entry"));
        this.moreInformationButton.setText(LocalizationSupport.message("More_Information"));
        this.panelMetadata.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LocalizationSupport.message("Metadata")));
        this.labelFetch.setText(LocalizationSupport.message("Fetching_Metadata..."));
    }


    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        labelLogo = new JLabel();
        labelLogo.setIcon(new ImageIcon(getClass().getResource("/spl/gui/mrdlib header.png")));
        labelLogo.setText("");
        contentPane.add(labelLogo, BorderLayout.NORTH);
        panelMetadata = new JPanel();
        panelMetadata.setLayout(cardLayou);

        //panelMetadata.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 10, 5), -1, -1));
        panelMetadata.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tableMetadata.setAutoCreateRowSorter(false);
        tableMetadata.setEnabled(true);
        tableMetadata.setFillsViewportHeight(true);
        tableMetadata.setShowVerticalLines(true);
        scrollPane.setViewportView(tableMetadata);
        panelMetadata.add(scrollPane, "scrollPane");
        panelWait = new JPanel();
        //panelWait.setLayout(new BorderLayout());
        panelWait.setBackground(new Color(-1));
        panelMetadata.add(panelWait, "panelWait");
        panelWait.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        iconLabel = new JLabel();
        iconLabel.setBackground(new Color(-1));
        iconLabel.setHorizontalAlignment(0);
        iconLabel.setHorizontalTextPosition(11);
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/spl/gui/ajax-loader.gif")));
        iconLabel.setText("");
        panelWait.add(iconLabel);
        labelFetch = new JLabel();
        labelFetch.setFont(new Font(labelFetch.getFont().getName(), labelFetch.getFont().getStyle(), 13));
        labelFetch.setText("");
        panelWait.add(labelFetch);

        cardLayou.show(panelMetadata, "panelWait");
        contentPane.add(panelMetadata, BorderLayout.CENTER);

        buttonOK = new JButton(Globals.lang("Ok"));
        buttonCancel = new JButton(Globals.lang("Cancel"));
        moreInformationButton = new JButton(Globals.lang("More information"));
        ButtonBarBuilder bb = new ButtonBarBuilder();
        bb.addGlue();
        bb.addGridded(moreInformationButton);
        bb.addGridded(buttonOK);
        bb.addGridded(buttonCancel);
        bb.addGlue();
        blankButton = new JButton();
        blankButton.setText("");
        contentPane.add(bb.getPanel(), BorderLayout.SOUTH);
        iconLabel.setLabelFor(scrollPane);

        /*contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
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
        panelMetadata = new JPanel();
        panelMetadata.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 10, 5), -1, -1));
        panel1.add(panelMetadata, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(600, 200), new Dimension(600, 200), null, 0, false));
        panelMetadata.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        scrollPane = new JScrollPane();
        panelMetadata.add(scrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tableMetadata.setAutoCreateRowSorter(false);
        tableMetadata.setEnabled(true);
        tableMetadata.setFillsViewportHeight(true);
        tableMetadata.setShowVerticalLines(true);
        scrollPane.setViewportView(tableMetadata);
        panelWait = new JPanel();
        panelWait.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelWait.setBackground(new Color(-1));
        panelMetadata.add(panelWait, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        labelFetch.setText("");
        panelWait.add(labelFetch, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 10, 10), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("");
        panel4.add(buttonOK, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("");
        panel4.add(buttonCancel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blankButton = new JButton();
        blankButton.setText("");
        panel4.add(blankButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moreInformationButton = new JButton();
        moreInformationButton.setText("");
        panel4.add(moreInformationButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        iconLabel.setLabelFor(scrollPane);*/
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
