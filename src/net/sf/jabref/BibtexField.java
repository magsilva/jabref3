/*
Copyright (C) 2003-2011 Raik Nagel and JabRef contributors
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

package net.sf.jabref;

/**
 * A container class for all properties of a bibtex-field.
 */
public class BibtexField
{
	/**
	 * Flag for standard bibtex-field.
	 */
	private static final int STANDARD = 0x01;
	
	/**
	 * Flag for field used only internally by JabRef (owner, timestamp).
	 */
	private static final int PRIVATE = 0x02;
	
	/**
	 * Flag for fields that should be displayable for editing.
	 */
	private static final int DISPLAYABLE = 0x04;

	/**
	 * Flag to control whether the field should be written to .bib.
	 */
	private static final int WRITEABLE = 0x08;

	/**
	 * Field name.
	 */
	private String name;

	/**
	 * Default flag for field (not standard, public, displayable and writable).
	 */
	private int flag = DISPLAYABLE | WRITEABLE;

	/**
	 * Length of the field (useful for GUI purposes only).
	 */
	private int length = GUIGlobals.DEFAULT_FIELD_LENGTH;

	/**
	 * Weight of the field (useful for GUI purposes only).
	 */
	private double weight = GUIGlobals.DEFAULT_FIELD_WEIGHT;

	/**
	 * Editor type to be used when editing the field (useful for GUI purposes only).
	 */
	private int editorType = GUIGlobals.STANDARD_EDITOR;

	/**
	 * Defines whether contents of this field are expected to be numeric values.
	 * This can be used to sort (e.g. volume numbers) correctly;
	 */
	private boolean numeric = false;

	public BibtexField(String fieldName) {
		name = fieldName;
	}

	public BibtexField(String fieldName, boolean pStandard) {
		name = fieldName;
		setFlag(pStandard, STANDARD);
	}

	public BibtexField(String fieldName, boolean pStandard, double pWeight) {
		name = fieldName;
		setFlag(pStandard, STANDARD);
		weight = pWeight;
	}

	public BibtexField(String fieldName, boolean pStandard, int pLength) {
		name = fieldName;
		setFlag(pStandard, STANDARD);
		length = pLength;
	}

	public BibtexField(String fieldName, boolean pStandard, double pWeight, int pLength) {
		name = fieldName;
		setFlag(pStandard, STANDARD);
		weight = pWeight;
		length = pLength;
	}

	/**
	 * Change the value of the flag for the field.
	 * 
	 * @param onOff New value of the flag
	 * @param flagID Flag numerical id.
	 */
	private void setFlag(boolean enabled, int flagID) {
		if (enabled) {
			flag = flag | flagID;
		} else {
			flag = flag & (0xff ^ flagID);
		}
	}

	/**
	 * Check if a flag is enabled.
	 * 
	 * @param flagID Flag numerical id.
	 * @return True if enabled, false otherwise.
	 */
	private boolean isSet(int flagID) {
		if ((flag & flagID) == flagID) {
			return true;
		}
		return false;
	}

	public boolean isStandard() {
		return isSet(STANDARD);
	}

	public BibtexField setPrivate() {
		setFlag(true, PRIVATE);
		return this;
	}

	public boolean isPrivate() {
		return isSet(PRIVATE);
	}

	public BibtexField setPublic() {
		setFlag(false, PRIVATE);
		return this;
	}

	public boolean isPublic() {
		return ! isSet(PRIVATE);
	}

	public BibtexField setDisplayable(boolean value) {
		setFlag(value, DISPLAYABLE);
		return this;
	}

	public boolean isDisplayable() {
		return isSet(DISPLAYABLE);
	}

	public BibtexField setWriteable(boolean value) {
		setFlag(value, WRITEABLE);
		return this;
	}

	public boolean isWriteable() {
		return isSet(WRITEABLE);
	}


	public void setEditorType(int type) {
		editorType = type;
	}

	public int getEditorType() {
		return editorType;
	}

	public void setWeight(double value) {
		this.weight = value;
	}

	public double getWeight() {
		return this.weight;
	}

	public int getLength() {
		return this.length;
	}

	public String getFieldName() {
		return name;
	}

	/**
	 * Set this field's numeric propery
	 * 
	 * @param numeric True to indicate that this is a numeric field.
	 * @return Reference to this object (that make it easier to initialize the field)
	 */
	public BibtexField setNumeric(boolean numeric) {
		this.numeric = numeric;
		return this;
	}

	public boolean isNumeric() {
		return numeric;
	}
}
