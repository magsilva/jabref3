package net.sf.jabref.external;

import java.io.IOException;

import javax.swing.*;

import net.sf.jabref.*;

public class PushToWinEdt implements PushToApplication {

    private boolean couldNotCall=false;
    private boolean notDefined=false;
    private JPanel settings = null;
    private JTextField winEdtPath = new JTextField(30);

    public String getName() {
        return Globals.lang("Insert selected citations into WinEdt");
    }

    public String getApplicationName() {
        return "WinEdt";
    }

    public String getTooltip() {
        return Globals.lang("Push selection to WinEdt");
    }

    public Icon getIcon() {
        return GUIGlobals.getImage("winedt");
    }

    public String getKeyStrokeName() {
        return "Push to WinEdt";
    }

    public void pushEntries(BibtexDatabase database, BibtexEntry[] entries, String keyString, MetaData metaData) {

        couldNotCall = false;
        notDefined = false;

        String winEdt = Globals.prefs.get("winEdtPath");
        if ((winEdt == null) || (winEdt.trim().length() == 0)) {
            notDefined = true;
            return;
        }

        try {
            StringBuffer toSend = new StringBuffer("\"[InsText('\\")
                    .append(Globals.prefs.get("citeCommand")).append("{")
                    .append(keyString.replaceAll("'", "''"))
                    .append("}');]\"");
            Runtime.getRuntime().exec(winEdt + " " + toSend.toString());

        }

        catch (IOException excep) {
            couldNotCall = true;
            excep.printStackTrace();
        }


    }

    public void operationCompleted(BasePanel panel) {
        if (notDefined) {
            panel.output(Globals.lang("Error") + ": "+
                    Globals.lang("Path to %0 not defined", getApplicationName())+".");
        }
        else if (couldNotCall) {
            panel.output(Globals.lang("Error") + ": " + Globals.lang("Could not call executable") + " '"
                    +Globals.prefs.get("winEdtPath") + "'.");
        }
        else
            Globals.lang("Pushed citations to WinEdt");
    }

    public boolean requiresBibtexKeys() {
        return true;
    }

    public JPanel getSettingsPanel() {
        if (settings == null)
            initSettingsPanel();
        winEdtPath.setText(Globals.prefs.get("winEdtPath"));
        return settings;
    }

    private void initSettingsPanel() {
        settings = new JPanel();
        settings.add(new JLabel(Globals.lang("Path to WinEdt.exe") + ":"));
        settings.add(winEdtPath);
    }

    public void storeSettings() {
        Globals.prefs.put("winEdtPath", winEdtPath.getText());
    }
}
