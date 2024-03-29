/*  Copyright (C) 2003-2011 JabRef contributors.
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package net.sf.jabref.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.*;

import net.sf.jabref.BibtexField;
import net.sf.jabref.BibtexFieldManager;
import net.sf.jabref.GUIGlobals;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefFrame;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Created by IntelliJ IDEA.
 * User: alver
 * Date: Aug 23, 2005
 * Time: 11:30:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldWeightDialog extends JDialog {

    JabRefFrame frame;
    HashMap<JSlider, SliderInfo> sliders = new HashMap<JSlider, SliderInfo>();
    JButton ok = new JButton(Globals.lang("OK")),
        cancel = new JButton(Globals.lang("Cancel"));

   public static void main(String[] args) {
        new FieldWeightDialog(null).setVisible(true);
    }

    public FieldWeightDialog(JabRefFrame frame) {
        this.frame = frame;
        JPanel main = buildMainPanel();
        main.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        getContentPane().add(main, BorderLayout.CENTER);
        getContentPane().add(buildButtonPanel(), BorderLayout.SOUTH);
        pack();
    }

    public JPanel buildMainPanel() {
        FormLayout layout = new FormLayout
            ("right:pref, 4dlu, fill:pref, 8dlu, right:pref, 4dlu, fill:pref", // 4dlu, left:pref, 4dlu",
             "");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        builder.appendSeparator(Globals.lang("Field sizes"));

        // We use this list to build an alphabetical list of field names:
        TreeSet<String> fields = new TreeSet<String>();
        // We use this map to remember which slider represents which field name:
        sliders.clear();
        for (int i = 0; i < BibtexFieldManager.singleton.numberOfPublicFields(); i++) {
            fields.add(BibtexFieldManager.singleton.getFieldName(i));
        }
        fields.remove("bibtexkey"); // bibtex key doesn't need weight.

        // Here is the place to add other fields:

        // --------------

        for (Iterator<String> i=fields.iterator(); i.hasNext();) {
            String fieldName = i.next();
            BibtexField field = BibtexFieldManager.singleton.getField(fieldName);
            builder.append(fieldName);
            int weight = (int)(100 * field.getWeight() / GUIGlobals.MAX_FIELD_WEIGHT) ;
            JSlider slider = new JSlider(0, 100, weight);
            sliders.put(slider, new SliderInfo(fieldName, weight));
            builder.append(slider);
        }
        builder.appendSeparator();

        return builder.getPanel();

    }

    public JPanel buildButtonPanel() {

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                storeSettings();
                dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        ButtonBarBuilder builder = new ButtonBarBuilder();
        builder.addGlue();
        builder.addButton(ok);
        builder.addButton(cancel);
        builder.addGlue();
        return builder.getPanel();
    }

    public void storeSettings() {
        for (Iterator<JSlider> i=sliders.keySet().iterator(); i.hasNext();) {
            JSlider slider = i.next();
            SliderInfo sInfo = sliders.get(slider);
            // Only list the value if it has changed:
            if (sInfo.originalValue != slider.getValue()) {
                double weight = GUIGlobals.MAX_FIELD_WEIGHT*(slider.getValue())/100d;
                BibtexFieldManager.singleton.getField(sInfo.fieldName).setWeight(weight);
            }
        }
        frame.removeCachedEntryEditors();
    }

    /**
     * "Struct" class to hold the necessary info about one of our JSliders:
     * which field it represents, and what value it started out with.
     */
    static class SliderInfo {
        String fieldName;
        int originalValue;
        public SliderInfo(String fieldName, int originalValue) {
            this.fieldName = fieldName;
            this.originalValue = originalValue;
        }
    }
}
