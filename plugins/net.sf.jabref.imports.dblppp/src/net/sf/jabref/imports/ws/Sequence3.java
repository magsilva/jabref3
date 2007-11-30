/**
 * Sequence3.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.jabref.imports.ws;

public class Sequence3  implements java.io.Serializable {
    private java.lang.String title;

    private java.lang.String dblp_key;

    private java.lang.String year;

    private java.lang.String conference;

    private java.lang.String _abstract;

    private java.lang.String type;

    private java.lang.String ee;

    private java.lang.String source;

    public Sequence3() {
    }

    public Sequence3(
           java.lang.String title,
           java.lang.String dblp_key,
           java.lang.String year,
           java.lang.String conference,
           java.lang.String _abstract,
           java.lang.String type,
           java.lang.String ee,
           java.lang.String source) {
           this.title = title;
           this.dblp_key = dblp_key;
           this.year = year;
           this.conference = conference;
           this._abstract = _abstract;
           this.type = type;
           this.ee = ee;
           this.source = source;
    }


    /**
     * Gets the title value for this Sequence3.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Sequence3.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the dblp_key value for this Sequence3.
     * 
     * @return dblp_key
     */
    public java.lang.String getDblp_key() {
        return dblp_key;
    }


    /**
     * Sets the dblp_key value for this Sequence3.
     * 
     * @param dblp_key
     */
    public void setDblp_key(java.lang.String dblp_key) {
        this.dblp_key = dblp_key;
    }


    /**
     * Gets the year value for this Sequence3.
     * 
     * @return year
     */
    public java.lang.String getYear() {
        return year;
    }


    /**
     * Sets the year value for this Sequence3.
     * 
     * @param year
     */
    public void setYear(java.lang.String year) {
        this.year = year;
    }


    /**
     * Gets the conference value for this Sequence3.
     * 
     * @return conference
     */
    public java.lang.String getConference() {
        return conference;
    }


    /**
     * Sets the conference value for this Sequence3.
     * 
     * @param conference
     */
    public void setConference(java.lang.String conference) {
        this.conference = conference;
    }


    /**
     * Gets the _abstract value for this Sequence3.
     * 
     * @return _abstract
     */
    public java.lang.String get_abstract() {
        return _abstract;
    }


    /**
     * Sets the _abstract value for this Sequence3.
     * 
     * @param _abstract
     */
    public void set_abstract(java.lang.String _abstract) {
        this._abstract = _abstract;
    }


    /**
     * Gets the type value for this Sequence3.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this Sequence3.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the ee value for this Sequence3.
     * 
     * @return ee
     */
    public java.lang.String getEe() {
        return ee;
    }


    /**
     * Sets the ee value for this Sequence3.
     * 
     * @param ee
     */
    public void setEe(java.lang.String ee) {
        this.ee = ee;
    }


    /**
     * Gets the source value for this Sequence3.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Sequence3.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Sequence3)) return false;
        Sequence3 other = (Sequence3) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.dblp_key==null && other.getDblp_key()==null) || 
             (this.dblp_key!=null &&
              this.dblp_key.equals(other.getDblp_key()))) &&
            ((this.year==null && other.getYear()==null) || 
             (this.year!=null &&
              this.year.equals(other.getYear()))) &&
            ((this.conference==null && other.getConference()==null) || 
             (this.conference!=null &&
              this.conference.equals(other.getConference()))) &&
            ((this._abstract==null && other.get_abstract()==null) || 
             (this._abstract!=null &&
              this._abstract.equals(other.get_abstract()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.ee==null && other.getEe()==null) || 
             (this.ee!=null &&
              this.ee.equals(other.getEe()))) &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getDblp_key() != null) {
            _hashCode += getDblp_key().hashCode();
        }
        if (getYear() != null) {
            _hashCode += getYear().hashCode();
        }
        if (getConference() != null) {
            _hashCode += getConference().hashCode();
        }
        if (get_abstract() != null) {
            _hashCode += get_abstract().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getEe() != null) {
            _hashCode += getEe().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Sequence3.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DBLPPlusPlus", "Sequence3"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dblp_key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dblp_key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("", "year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conference");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_abstract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abstract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
