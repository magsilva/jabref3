package net.sf.jabref.oo;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.star.text.XTextDocument;
import net.sf.jabref.*;
import net.sf.jabref.external.PushToApplication;
import net.sf.jabref.plugin.SidePanePlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Map;

/**
 * This test panel can be opened by reflection from JabRef, passing the JabRefFrame as an
 * argument to the start() method. It displays buttons for testing interaction functions
 * between JabRef and OpenOffice.
 */
public class OOTestPanel extends AbstractWorker implements SidePanePlugin, PushToApplication {

    TestPanel comp;
    JDialog diag;
    static JButton
        connect = new JButton(Globals.lang("Connect")),
        manualConnect = new JButton(Globals.lang("Manual connect")),
        selectDocument = new JButton(Globals.lang("Select Writer document")),
        setStyleFile = new JButton(Globals.lang("Select style file")),
        pushEntries = new JButton(Globals.lang("Cite")),
        pushEntriesInt = new JButton(Globals.lang("Cite in-text")),
        focus = new JButton("Focus OO document"),
        update = new JButton(Globals.lang("Sync OO bibliography")),
        insertFullRef = new JButton("Insert reference text"),
        merge = new JButton(Globals.lang("Merge citations")),
        test = new JButton("Test");
    JRadioButton inPar, inText;
    private JPanel settings = null;
    private static String styleFile = null;
    private static OOBibBase ooBase;
    private static JabRefFrame frame;
    private SidePaneManager manager;
    private static OOBibStyle style = null;
    private StyleSelectDialog styleDialog = null;
    private boolean dialogOkPressed = false, autoDetected = false;
    private String sOffice = null;
    private Exception connectException = null;

    public OOTestPanel() {
        
        String defExecutable, defJarsDir;
        if (Globals.ON_WIN) {
            Globals.prefs.putDefaultValue("ooPath", "C:\\Program Files\\OpenOffice.org 3");
            Globals.prefs.putDefaultValue("ooExecutablePath", "C:\\Program Files\\OpenOffice.org 2.3\\program\\soffice.exe");
            Globals.prefs.putDefaultValue("ooJarsPath", "C:\\Program Files\\OpenOffice.org 2.3\\program\\classes");
        } else if (Globals.ON_MAC) {
            Globals.prefs.putDefaultValue("ooExecutablePath", "/Applications/OpenOffice.org.app/Contents/MacOS/soffice.bin");
            Globals.prefs.putDefaultValue("ooPath", "/Applications/OpenOffice.org.app");
            Globals.prefs.putDefaultValue("ooJarsPath", "/Applications/OpenOffice.org.app/Contents/basis-link");
        } else { // Linux
            //Globals.prefs.putDefaultValue("ooPath", "/usr/lib/openoffice");
            Globals.prefs.putDefaultValue("ooPath", "/opt/openoffice.org3");
            Globals.prefs.putDefaultValue("ooExecutablePath", "/usr/lib/openoffice/program/soffice");
            //Globals.prefs.putDefaultValue("ooJarsPath", "/usr/share/java/openoffice");
            Globals.prefs.putDefaultValue("ooJarsPath", "/opt/openoffice.org/basis3.0");
        }
        Globals.prefs.putDefaultValue("connectToOO3", Boolean.TRUE);
        
        //Globals.prefs.putDefaultValue("ooStyleFileDirectories", System.getProperty("user.home")+";false");
        Globals.prefs.putDefaultValue("ooStyleFileLastDir", System.getProperty("user.home"));
        Globals.prefs.putDefaultValue("ooInParCitation", true);

        styleFile = Globals.prefs.get("ooBibliographyStyleFile");

    }

    public SidePaneComponent getSidePaneComponent() {
        return comp;
    }


    public void init(JabRefFrame frame, SidePaneManager manager) {
        this.frame = frame;
        this.manager = manager;
        comp = new TestPanel(manager, GUIGlobals.getIconUrl("openoffice"), Globals.lang("OpenOffice"));
        try {
            initPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JMenuItem getMenuItem() {
        JMenuItem item = new JMenuItem("OpenOffice.org panel");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manager.show(getName());
            }
        });
        return item;
    }

    public String getShortcutKey() {
        return null;
    }


    private void initPanel() throws Exception {

        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect(true);
            }
        });
        manualConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                connect(false);
            }
        });
        selectDocument.setToolTipText(Globals.lang("Select which open Writer document to work on"));
        selectDocument.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    ooBase.selectDocument();
                    frame.output(Globals.lang("Connected to document")+": "+ooBase.getCurrentDocumentTitle());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), Globals.lang("Error"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setStyleFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (styleDialog == null) {
                    styleDialog = new StyleSelectDialog(frame, styleFile);
                }
                styleDialog.setVisible(true);
                if (styleDialog.isOkPressed()) {
                    style = styleDialog.getSelectedStyle();
                    if (style != null) {
                        styleFile = style.getFile().getPath();
                        Globals.prefs.put("ooBibliographyStyleFile", styleFile);
                    }
                }
            }
        });

        pushEntries.setToolTipText(Globals.lang("Cite selected entries"));
        pushEntries.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushEntries(true);
            }
        });
        pushEntries.setToolTipText(Globals.lang("Cite selected entries with in-text citation"));
        pushEntriesInt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushEntries(false);
            }
        });
        focus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ooBase.setFocus();
            }
        });
        update.setToolTipText(Globals.lang("Ensure that the bibliography is up-to-date"));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    style.ensureUpToDate();
                    ooBase.refreshCiteMarkers(frame.basePanel().database(), style);
                    ooBase.rebuildBibTextSection(frame.basePanel().database(), style);
                    //ooBase.sync(frame.basePanel().database(), style);
                } catch (BibtexEntryNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, Globals.lang("Your OpenOffice document references the BibTeX key '%0', which could not be found in your current database.",
                            ex.getBibtexKey()), Globals.lang("Unable to synchronize bibliography"), JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        insertFullRef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    insertFullRefs();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        merge.setToolTipText(Globals.lang("Combine pairs of citations that are separated by spaces only"));
        merge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    ooBase.combineCiteMarkers(frame.basePanel().database(), style);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    ooBase.combineCiteMarkers(frame.basePanel().database(), style);
                    //insertUsingBST();
                    //ooBase.testFootnote();
                    //ooBase.refreshCiteMarkers(frame.basePanel().database(), style);
                    //ooBase.createBibTextSection(true);
                    //ooBase.clearBibTextSectionContent();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        selectDocument.setEnabled(false);
        pushEntries.setEnabled(false);
        pushEntriesInt.setEnabled(false);
        focus.setEnabled(false);
        update.setEnabled(false);
        insertFullRef.setEnabled(false);
        merge.setEnabled(false);
        test.setEnabled(false);
        diag = new JDialog((JFrame)null, "OpenOffice panel", false);

        DefaultFormBuilder b = new DefaultFormBuilder(new FormLayout("fill:pref:grow",
                //"p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu,p,0dlu"));
                "p,p,p,p,p,p,p,p,p,p"));
        b.append(connect);
        b.append(manualConnect);
        b.append(selectDocument);
        b.append(setStyleFile);
        b.append(pushEntries);
        b.append(pushEntriesInt);
        b.append(merge);
        //b.append(focus);
        b.append(update);

        //b.append(insertFullRef);
        //b.append(test);
        //diag.getContentPane().add(b.getPanel(), BorderLayout.CENTER);

        JPanel content = new JPanel();
        comp.setContent(content);
        content.setLayout(new BorderLayout());
        content.add(b.getPanel(), BorderLayout.CENTER);
        
        //diag.pack();
        //diag.setVisible(true);
    }


    public void connect(boolean auto) {
        if (ooBase != null) {
            try {
                java.util.List<XTextDocument> list = ooBase.getTextDocuments();
                // TODO: how to find the title of the documents?
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }

        String unoilDir, ooBaseDirectory;
        if (auto) {
            AutoDetectPaths adp = new AutoDetectPaths(diag);

            if (adp.runAutodetection()) {
                autoDetected = true;
                dialogOkPressed = true;
                diag.dispose();
            } else if (!adp.cancelled()) {
                JOptionPane.showMessageDialog(diag,
                        Globals.lang("Autodetection failed"),
                        Globals.lang("Autodetecton failed"),
                        JOptionPane.ERROR_MESSAGE);
            }
            if (!autoDetected)
                return;

            // User clicked Auto, and the system successfully detected paths:
            unoilDir = Globals.prefs.get("ooUnoilPath");
            ooBaseDirectory = Globals.prefs.get("ooJurtPath");
            sOffice = Globals.prefs.get("ooExecutablePath");

            //System.out.println("unoilDir: "+unoilDir);
            //System.out.println("ooBaseDir: "+ooBaseDirectory);
            //System.out.println("soffice: "+sOffice);

        }
        else { // Manual connect

            showConnectDialog();
            if (!dialogOkPressed)
                return;

            String ooPath = Globals.prefs.get("ooPath");
            String ooJars = Globals.prefs.get("ooJarsPath");
            sOffice = Globals.prefs.get("ooExecutablePath");

            boolean openOffice3 = Globals.prefs.getBoolean("connectToOO3");
            if (Globals.ON_WIN) {
                if (openOffice3) {
                    unoilDir = ooPath+"\\Basis\\program\\classes";
                    ooBaseDirectory = ooPath+"\\URE\\java";
                    sOffice = ooPath+"\\program\\soffice.exe";
                }
                else {
                    unoilDir = ooPath+"\\program\\classes";
                    ooBaseDirectory = unoilDir;
                    sOffice = ooPath+"\\program\\soffice.exe";
                }

            }
            else if (Globals.ON_MAC) {
                if (openOffice3) {
                    sOffice = ooPath+"/Contents/MacOS/soffice.bin";
                    ooBaseDirectory = ooPath+"/Contents/basis-link/share/java";
                    unoilDir = ooPath+"/Contents/basis-link/program/classes"; 
                }
                else {
                    sOffice = ooPath+"/Contents/MacOS/soffice.bin";
                    ooBaseDirectory = ooPath+"/Contents/MacOS/classes";
                    unoilDir = ooPath+"/Contents/MacOS/classes";
                }
            }
            else {
                // Linux:
                if (openOffice3) {
                    unoilDir = ooJars+"/program/classes";
                    ooBaseDirectory = ooJars+"/ure-link/share/java";
                    //sOffice = ooPath+"/program/soffice";
                }
                else {
                    unoilDir = ooJars;
                    ooBaseDirectory = ooJars;
                    System.out.println(sOffice);
                    System.out.println(ooJars);
                    //sOffice = ooPath+"/program/soffice";
                }
            }
        }

        //String unoilDir = "/opt/openoffice.org/basis3.0/program/classes";
        //String ooBaseDirectory = Globals.prefs.get("ooJarsPath");//"/usr/share/java/openoffice";
        //String sOffice = Globals.prefs.get("ooExecutablePath");
        //System.getProperty( "os.name" ).startsWith( "Windows" ) ? "soffice.exe" : "soffice";

        // Add OO jars to the classpath:
        try {

            URL[] jarList = new URL[] {
                new File(unoilDir, "unoil.jar").toURI().toURL(),
                new File(ooBaseDirectory, "jurt.jar").toURI().toURL(),
                new File(ooBaseDirectory, "juh.jar").toURI().toURL(),
                new File(ooBaseDirectory, "ridl.jar").toURI().toURL(),
            };
            addURL(jarList);
            /*ClassLoader aCL = Thread.currentThread().getContextClassLoader();
            loader = new URLClassLoader(jarList, aCL);
            Thread.currentThread().setContextClassLoader(aUrlCL);
            loader = new URLClassLoader(jarList);
            */

            if (styleFile == null) {
                JOptionPane.showMessageDialog(diag, "You must choose a style file before you can connect.", "No style file selected", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (style == null)
                readStyleFile();


            // Show progress dialog:
            final JDialog progDiag = (new AutoDetectPaths(diag)).showProgressDialog(diag, Globals.lang("Connecting"),
                    Globals.lang("Please wait..."), false);
            getWorker().run(); // Do the actual connection, using Spin to get off the EDT.
            progDiag.dispose();
            diag.dispose();
            if (ooBase == null)
                throw connectException;

            if (ooBase.isConnectedToDocument())
                frame.output(Globals.lang("Connected to document")+": "+ooBase.getCurrentDocumentTitle());

            // Enable actions that depend on Connect:
            selectDocument.setEnabled(true);
            pushEntries.setEnabled(true);
            pushEntriesInt.setEnabled(true);
            focus.setEnabled(true);
            update.setEnabled(true);
            insertFullRef.setEnabled(true);
            merge.setEnabled(true);
            test.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(frame, Globals.lang("Could not connect to running OpenOffice.\n"
                +"Make sure you have the UNO jars (unoil.jar, juh.jar, jurt.jar and ridl.jar) on your classpath,\n"
                +"as well as the OpenOffice bin directory.\nError message: "+e.getMessage()));
        }
    }

    public void run() {
        try {
            // Connect:
            ooBase = new OOBibBase(sOffice, true);
        } catch (Exception e) {
            ooBase = null;
            connectException = e;
            //JOptionPane.showMessageDialog(frame, Globals.lang("Unable to connect"));
        }
    }

    /**
     * Read the style file. Record the last modified time of the file.
     * @throws Exception
     */
    public void readStyleFile() throws Exception {

        style = new OOBibStyle(new File(styleFile));
    }




    // The methods addFile and associated final Class[] parameters were gratefully copied from
	// anthony_miguel @ http://forum.java.sun.com/thread.jsp?forum=32&thread=300557&tstart=0&trange=15
	private static final Class[] parameters = new Class[]{URL.class};

    public static void addURL(URL[] u) throws IOException {
		URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;

        try {
			Method method = sysclass.getDeclaredMethod("addURL",parameters);
			method.setAccessible(true);
            for (int i=0; i<u.length; i++)
                method.invoke(sysloader, u[i]);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new IOException("Error, could not add URL to system classloader");
		}//end try catch
	}//end method

    public void setStyleFile() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home"));
        int answer = jfc.showOpenDialog(null);
        if (answer == JFileChooser.APPROVE_OPTION) {
            styleFile = jfc.getSelectedFile().getPath();
            Globals.prefs.put("ooBibliographyStyleFile", styleFile);
            try {
                style = new OOBibStyle(new FileReader(styleFile));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(diag, e.getMessage());
            }
        }
    }

    public void updateConnectionParams(String ooPath, String ooExec, String ooJars, boolean oo3) {
        Globals.prefs.put("ooPath", ooPath);
        Globals.prefs.put("ooExecutablePath", ooExec);
        Globals.prefs.put("ooJarsPath", ooJars);
        Globals.prefs.putBoolean("connectToOO3", oo3);
    }

    public void showConnectDialog() {
        dialogOkPressed = false;
        final JDialog diag = new JDialog(frame, Globals.lang("Set connection parameters"), true);
        final JTextField ooPath = new JTextField(30);
        JButton browseOOPath = new JButton(Globals.lang("Browse"));
        ooPath.setText(Globals.prefs.get("ooPath"));
        final JTextField ooExec = new JTextField(30);
        JButton browseOOExec = new JButton(Globals.lang("Browse"));
        browseOOExec.addActionListener(new BrowseAction(null, ooExec, false));
        final JTextField ooJars = new JTextField(30);
        JButton browseOOJars = new JButton(Globals.lang("Browse"));
        browseOOJars.addActionListener(new BrowseAction(null, ooJars, true));
        ooExec.setText(Globals.prefs.get("ooExecutablePath"));
        ooJars.setText(Globals.prefs.get("ooJarsPath"));
        final JRadioButton oo2 = new JRadioButton("OpenOffice.org 2.x", !Globals.prefs.getBoolean("connectToOO3"));
        final JRadioButton oo3 = new JRadioButton("OpenOffice.org 3.x", Globals.prefs.getBoolean("connectToOO3"));
        ButtonGroup bg = new ButtonGroup();
        bg.add(oo2);
        bg.add(oo3);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("left:pref, 4dlu, fill:pref:grow, 4dlu, fill:pref", ""));
        if (Globals.ON_WIN || Globals.ON_MAC) {
            builder.append(Globals.lang("Path to OpenOffice directory"));
            builder.append(ooPath);
            builder.append(browseOOPath);
            builder.nextLine();
        }
        else {
            builder.append(Globals.lang("Path to OpenOffice executable"));
            builder.append(ooExec);
            builder.append(browseOOExec);
            builder.nextLine();

            builder.append(Globals.lang("Path to OpenOffice library dir"));
            builder.append(ooJars);
            builder.append(browseOOJars);
            builder.nextLine();
        }
        builder.append(Globals.lang("Program version"));
        builder.append(oo2); builder.nextLine();
        builder.append(new JPanel());
        builder.append(oo3); builder.nextLine();

        ButtonBarBuilder bb = new ButtonBarBuilder();
        JButton ok = new JButton(Globals.lang("Ok"));
        JButton cancel = new JButton(Globals.lang("Cancel"));
        //JButton auto = new JButton(Globals.lang("Autodetect"));
        ActionListener tfListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updateConnectionParams(ooPath.getText(), ooExec.getText(), ooJars.getText(),
                        oo3.isSelected());
                diag.dispose();
            }
        };

        ooPath.addActionListener(tfListener);
        ooExec.addActionListener(tfListener);
        ooJars.addActionListener(tfListener);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updateConnectionParams(ooPath.getText(), ooExec.getText(), ooJars.getText(),
                        oo3.isSelected());
                dialogOkPressed = true;
                diag.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                diag.dispose();
            }
        });
        bb.addGlue();
        bb.addRelatedGap();
        bb.addGridded(ok);
        bb.addGridded(cancel);
        bb.addGlue();
        builder.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bb.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        diag.getContentPane().add(builder.getPanel(), BorderLayout.CENTER);
        diag.getContentPane().add(bb.getPanel(), BorderLayout.SOUTH);
        diag.pack();
        diag.setLocationRelativeTo(frame);
        diag.setVisible(true);
        
    }



    public void pushEntries(boolean inParenthesis) {
        if (!ooBase.isConnectedToDocument()) {
            JOptionPane.showMessageDialog(frame, Globals.lang("Not connected to any Writer document. Please"
                +" make sure a document is open, and use the 'Select Writer document' button to connect to it."),
                    Globals.lang("Error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        BasePanel panel =frame.basePanel();
        final BibtexDatabase database = panel.database();
        if (panel != null) {
            BibtexEntry[] entries = panel.getSelectedEntries();
            if (entries.length > 0) {
                try {
                    ooBase.insertEntry(entries, database, style, inParenthesis);
                } catch (UndefinedParagraphFormatException ex) {
                   reportUndefinedParagraphFormat(ex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
    

    public void insertFullRefs() {
        try {
            // Create or clear bibliography:
            /*boolean hadBib = ooBase.createBibTextSection(true);
            if (hadBib)
                ooBase.clearBibTextSectionContent();
              */
            BasePanel panel =frame.basePanel();
            final BibtexDatabase database = panel.database();
            if (panel != null) {
                BibtexEntry[] entries = panel.getSelectedEntries();
                ArrayList<BibtexEntry> el = new ArrayList<BibtexEntry>();
                for (int i = 0; i < entries.length; i++) {
                    el.add(entries[i]);
                }

                ooBase.insertFullReferenceAtViewCursor(database, el, style, "Default");
            }
        } catch (UndefinedParagraphFormatException ex) {
            reportUndefinedParagraphFormat(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void reportUndefinedParagraphFormat(UndefinedParagraphFormatException ex) {
        JOptionPane.showMessageDialog(frame, "<html>"+Globals.lang("Your style file specifies the paragraph formta '%0', "
            +"which is undefined in your current OpenOffice document.", ex.getFormatName())+"<br>"
            +Globals.lang("The paragraph format is controlled by the property 'ReferenceParagraphFormat' in the style file.")
            +"</html>",
            Globals.lang(""), JOptionPane.ERROR_MESSAGE);
    }


    public void insertUsingBST() {
        try {
            BasePanel panel =frame.basePanel();
            final BibtexDatabase database = panel.database();
            if (panel != null) {
                BibtexEntry[] entries = panel.getSelectedEntries();
                ArrayList<BibtexEntry> el = new ArrayList<BibtexEntry>();
                for (int i = 0; i < entries.length; i++) {
                    el.add(entries[i]);
                }

                BstWrapper wrapper = new BstWrapper();
                //wrapper.loadBstFile(new File("/home/usr/share/texmf-tetex/bibtex/bst/base/plain.bst"));
                wrapper.loadBstFile(new File("/home/usr/share/texmf-tetex/bibtex/bst/ams/amsalpha.bst"));
                Map<String,String> result = wrapper.processEntries(el, database);
                for (String key : result.keySet()) {
                    System.out.println("Key: "+key);
                    System.out.println("Entry: "+result.get(key));
                    ooBase.insertMarkedUpTextAtViewCursor(result.get(key), "Default");
                }
                //System.out.println(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pushEntries(boolean inParenthesis, BibtexEntry[] entries) {
        final BibtexDatabase database = frame.basePanel().database();
        if (entries.length > 0) {
            try {
                ooBase.insertEntry(entries, database, style, inParenthesis);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getName() {
        return "Push to OpenOffice";
    }

    public String getApplicationName() {
        return "OpenOffice";
    }

    public String getTooltip() {
        return "Push selection to OpenOffice";
    }

    public Icon getIcon() {
        return GUIGlobals.getImage("openoffice");
    }

    public String getKeyStrokeName() {
        return null;
    }

    public JPanel getSettingsPanel() {
        if (settings == null)
            initSettingsPanel();
        return settings;
    }

    private void initSettingsPanel() {
        boolean inParen = Globals.prefs.getBoolean("ooInParCitation");
        inPar = new JRadioButton(Globals.lang("Use in-parenthesis citation"), inParen);
        inText = new JRadioButton(Globals.lang("Use in-text citation"), !inParen);
        ButtonGroup bg = new ButtonGroup();
        bg.add(inPar);
        bg.add(inText);
        settings = new JPanel();
        settings.setLayout(new BorderLayout());
        settings.add(inPar, BorderLayout.NORTH);
        settings.add(inText, BorderLayout.SOUTH);
    }

    public void storeSettings() {
        Globals.prefs.putBoolean("ooInParCitation", inPar.isSelected());
    }

    public void pushEntries(BibtexDatabase bibtexDatabase, BibtexEntry[] entries, String s, MetaData metaData) {
        if (ooBase == null) {
            connect(true);
        }
        if (ooBase != null) {
            pushEntries(Globals.prefs.getBoolean("ooInParCitation"), entries);
        }
    }

    public void operationCompleted(BasePanel basePanel) {

    }

    public boolean requiresBibtexKeys() {
        return true;
    }

    class TestPanel extends SidePaneComponent {

        public TestPanel(SidePaneManager sidePaneManager, URL url, String s) {
            super(sidePaneManager, url, s);
        }

        public String getName() {
            return OOTestPanel.this.getName();
        }
    }


}
