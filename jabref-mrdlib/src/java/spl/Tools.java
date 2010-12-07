package spl;

import freemind.controller.Controller;
import freemind.modes.MindMapNode;
import net.sf.jabref.Globals;
import net.sf.jabref.Util;
import net.sf.jabref.external.ExternalFileType;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Christoph Arbeit
 * Date: 09.09.2010
 * Time: 10:43:01
 * To change this template use File | Settings | File Templates.
 */
public class Tools {

    public static int WEBSERVICE_APP_ID = 9;
    public static String WEBSERVICE_VERSION_SHORT = "0.1";

    public static byte[] zip(File file){
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream out = new GZIPOutputStream(bos);

            byte[] buf = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            fileInputStream.close();
            out.finish();
            out.close();
            bos.close();
            return bos.toByteArray();
        }catch(IOException e){
            //Todo logging
            return null;
        }
    }

    public static String getStackTraceAsString(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.print(" [ ");
        pw.print(exception.getClass().getName());
        pw.print(" ] ");
        pw.print(exception.getMessage());
        exception.printStackTrace(pw);
        return sw.toString();
    }

    public static void centerRelativeToWindow (java.awt.Dialog diag, java.awt.Container win) {
          int x;
          int y;

          Point topLeft = win.getLocationOnScreen();
          Dimension parentSize = win.getSize();

          Dimension mySize = diag.getSize();

          if (parentSize.width > mySize.width)
            x = ((parentSize.width - mySize.width)/2) + topLeft.x;
          else
            x = topLeft.x;

          if (parentSize.height > mySize.height)
            y = ((parentSize.height - mySize.height)/2) + topLeft.y;
          else
            y = topLeft.y;

          diag.setLocation (x, y);
    }

    public static String getLink(String link, URL mindmapUrl){
        if(link == null || link.isEmpty()){
            return null;
        }
        if(!freemind.main.Tools.isAbsolutePath(link)){
            try{
                if(link.startsWith("\\\\")){
                    link = link.replace("\\\\", "file://");
                    link = link.replace('\\', '/').replaceAll(" ","%20");
                    URL url = new URL(link);
                    File file = new File(url.toURI());
                    return file.getPath();
                }
                else if(mindmapUrl != null){
                    URL url = new URL(mindmapUrl, link);
                    File file = new File(url.toURI());
                    return file.getPath();
                }
            } catch(MalformedURLException e){
                return link;
            } catch (URISyntaxException e) {
                return link;
            }catch(IllegalArgumentException e){
                return link;
            }
        }
        else{
            return link;
        }
        return link;
    }

    public static void openURL(String link){
        ExternalFileType fileType = Globals.prefs.getExternalFileTypeByExt("html");
        try{
            if (Globals.ON_MAC) {

                String[] cmd = ((fileType.getOpenWith() != null) && (fileType.getOpenWith().length() > 0)) ?
                        new String[] { "/usr/bin/open", "-a", fileType.getOpenWith(), link } :
                        new String[] { "/usr/bin/open", link };
                Runtime.getRuntime().exec(cmd);
            } else if (Globals.ON_WIN) {

                if ((fileType.getOpenWith() != null) && (fileType.getOpenWith().length() > 0)) {
                    // Application is specified. Use it:
                    Util.openFileWithApplicationOnWindows(link, fileType.getOpenWith());
                } else
                    Util.openFileOnWindows(link, true);
            } else {
                // Use the given app if specified, and the universal "xdg-open" otherwise:
                String[] openWith;
                if ((fileType.getOpenWith() != null) && (fileType.getOpenWith().length() > 0))
                    openWith = fileType.getOpenWith().split(" ");
                else
                    openWith = new String[] {"xdg-open"};

                String[] cmd = new String[openWith.length+1];
                System.arraycopy(openWith, 0, cmd, 0, openWith.length);
                cmd[cmd.length-1] = link;
                Runtime.getRuntime().exec(cmd);
            }
        } catch(IOException e){

        }
    }
}
