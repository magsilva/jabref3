package spl;

import java.awt.*;
import java.io.*;
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
}
