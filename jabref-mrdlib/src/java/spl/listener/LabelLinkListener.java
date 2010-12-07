package spl.listener;


import spl.Tools;
import splmm.Localization.LocalizationSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: Christoph Arbeit
 * Date: 28.09.2010
 * Time: 12:06:57
 * To change this template use File | Settings | File Templates.
 */
public class LabelLinkListener implements MouseListener {

        private String link;
        private Component component;

        public LabelLinkListener(Component c, String link) {
            this.link = link;
            this.component = c;
        }

        public void mousePressed(MouseEvent e) {
            Tools.openURL(link);
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {
            component.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public void mouseExited(MouseEvent e) {
            component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        public void mouseClicked(MouseEvent e) {
        }

}
