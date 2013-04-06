/***************************************************************
 *  Copyright notice
 *  
 *  (c) 2007 Toennies
 *  All rights reserved
 *
 *  This script is part of the net.sf.jabref.imports.dblppp project. The net.sf.jabref.imports.dblppp project is 
 *  free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 * 
 *  The GNU General Public License can be found at
 *  http://www.gnu.org/copyleft/gpl.html.
 * 
 *  This script is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  This copyright notice MUST APPEAR in all copies of the script!
 ***************************************************************/
package net.sf.jabref.imports.dblppp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.math.BigInteger;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.GUIGlobals;
import net.sf.jabref.Globals;
import net.sf.jabref.OutputPrinter;
import net.sf.jabref.imports.EntryFetcher;
import net.sf.jabref.imports.ImportInspector;
import net.sf.jabref.imports.ws.Sequence4;
import net.sf.jabref.imports.ws.Sequence6;

/*
 * 
 * <p>
 * See the <a href="{@docRoot}/LICENSE">License</a>, <a href="{@docRoot}/README">ReadMe</a>.
 * </p>
 * 
 * @author <a href="mailto:toennies@l3s.de">Sascha Toennies</a>
 * @author $Author: toennies $
 * @version $Revision: 2 $ $Date: 2007-11-05 16:13:40 +0100 (Mo, 05 Nov 2007) $
 * 
 */
public class DBLPppFetcher implements EntryFetcher {

	private boolean shouldContinue;

	private Map<String, String> authorMap;

	private String terms;

	JTextField startYearField = new JTextField(5);

	JTextField endYearField = new JTextField(5);

	JTextField limitField = new JTextField(5);

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.imports.EntryFetcher#getHelpPage()
	 */
	public String getHelpPage() {
		return "DBLP++Help.html";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.imports.EntryFetcher#getIcon()
	 */
	public URL getIcon() {
		return GUIGlobals.getIconUrl("www");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.imports.EntryFetcher#getKeyName()
	 */
	public String getKeyName() {
		return "Fetch DBLP++";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.imports.EntryFetcher#getOptionsPanel()
	 */
	public JPanel getOptionsPanel() {
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// int jahr = cal.get(Calendar.YEAR);
		// startYear.setText(String.valueOf(jahr));
		// endYear.setText(String.valueOf(jahr));

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;

		JPanel optionPan = new JPanel();
		optionPan.setLayout(gbl);

		gbc.gridx = 0;
		gbc.gridy = 0;
		Label sLabel = new Label("Start Year: ");
		gbl.setConstraints(sLabel, gbc);
		optionPan.add(sLabel);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(startYearField, gbc);
		optionPan.add(startYearField);

		gbc.gridx = 0;
		gbc.gridy = 1;
		Label eLabel = new Label("End Year: ");
		gbl.setConstraints(eLabel, gbc);
		optionPan.add(eLabel);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(endYearField, gbc);
		optionPan.add(endYearField);

		gbc.gridx = 0;
		gbc.gridy = 3;
		Label lLabel = new Label("Limit: ");
		gbl.setConstraints(lLabel, gbc);
		optionPan.add(lLabel);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(limitField, gbc);
		optionPan.add(limitField);
		limitField.setText("100");
		return optionPan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.imports.EntryFetcher#getTitle()
	 */
	public String getTitle() {
		return Globals.menuTitle(getKeyName());
	}

	public boolean processQuery(String query, ImportInspector dialog, OutputPrinter frame) {

		shouldContinue = true;

		DBLPppQuerist querist = new DBLPppQuerist();

		BigInteger start, end, limit;

		if (startYearField.getText().equals("")) {
			start = null;
		} else {
			start = BigInteger.valueOf(Long.parseLong(startYearField.getText()));
		}

		if (endYearField.getText().equals("")) {
			end = null;
		} else {
			end = BigInteger.valueOf(Long.parseLong(endYearField.getText()));
		}

		if (limitField.getText().equals("")) {
			limit = null;
		} else {
			limit = BigInteger.valueOf(Long.parseLong(limitField.getText()));
		}

		try {
			Sequence6[] results = querist.keywordQuery(terms, start, end, limit);

			if (results == null) {
				frame.showMessage(Globals
					.lang("No entries found for the search string '%0'", terms), Globals
					.lang("Search DBLP++"), JOptionPane.INFORMATION_MESSAGE);
				return false;
			}

			int progress = 1;
			authorMap = new HashMap<String, String>();
			for (Sequence6 result : results) {
				dialog.setProgress(progress, results.length);
				StringBuffer authorString = new StringBuffer();
				if (result.getDblp_key() != null) {
					Sequence4[] authors = querist.getAuthors(result.getDblp_key());
					for (int i = 0; i < authors.length; i++) {
						authorString.append(authors[i].getAuthor());
						if (i != authors.length - 1)
							authorString.append(" and ");
					}
				}
				progress++;
				authorMap.put(result.getDblp_key(), authorString.toString());
			}

			progress = 1;
			for (Sequence6 result : results) {
				if (!shouldContinue)
					break;

				BibtexEntry entry = querist
					.parseResult(result, authorMap.get(result.getDblp_key()));
				if (entry != null) {
					dialog.addEntry(entry);
				}
				dialog.setProgress(progress, results.length);
				progress++;
			}

			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.gui.ImportInspectionDialog.CallBack#cancelled()
	 */
	public void cancelled() {
		shouldContinue = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.gui.ImportInspectionDialog.CallBack#done(int)
	 */
	public void done(int entriesImported) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jabref.gui.ImportInspectionDialog.CallBack#stopFetching()
	 */
	public void stopFetching() {
		shouldContinue = false;
	}

}
