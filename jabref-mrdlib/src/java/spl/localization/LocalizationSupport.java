package spl.localization;

import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Christoph Arbeit
 * Date: 08.09.2010
 * Time: 09:56:31
 * To change this template use File | Settings | File Templates.
 */
public class LocalizationSupport {

    private static final ResourceBundle splmmBundle = ResourceBundle.getBundle("resource/JabRef");

    public static String message(String key){
        String value = splmmBundle.getString(key);
        return value;
    }

}
