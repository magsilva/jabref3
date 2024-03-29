/**
 * DBLPPlusPlusServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.jabref.imports.ws;

public class DBLPPlusPlusServiceLocator extends org.apache.axis.client.Service implements net.sf.jabref.imports.ws.DBLPPlusPlusService {

    public DBLPPlusPlusServiceLocator() {
    }


    public DBLPPlusPlusServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DBLPPlusPlusServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DBLPPlusPlusPort
    private java.lang.String DBLPPlusPlusPort_address = "http://dblp.l3s.de/WS/dblp++.php";

    public java.lang.String getDBLPPlusPlusPortAddress() {
        return DBLPPlusPlusPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DBLPPlusPlusPortWSDDServiceName = "DBLPPlusPlusPort";

    public java.lang.String getDBLPPlusPlusPortWSDDServiceName() {
        return DBLPPlusPlusPortWSDDServiceName;
    }

    public void setDBLPPlusPlusPortWSDDServiceName(java.lang.String name) {
        DBLPPlusPlusPortWSDDServiceName = name;
    }

    public net.sf.jabref.imports.ws.DBLPPlusPlusPort getDBLPPlusPlusPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DBLPPlusPlusPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDBLPPlusPlusPort(endpoint);
    }

    public net.sf.jabref.imports.ws.DBLPPlusPlusPort getDBLPPlusPlusPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	net.sf.jabref.imports.ws.DBLPPlusPlusBindingStub _stub = new net.sf.jabref.imports.ws.DBLPPlusPlusBindingStub(portAddress, this);
            _stub.setPortName(getDBLPPlusPlusPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDBLPPlusPlusPortEndpointAddress(java.lang.String address) {
        DBLPPlusPlusPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.sf.jabref.imports.ws.DBLPPlusPlusPort.class.isAssignableFrom(serviceEndpointInterface)) {
            	net.sf.jabref.imports.ws.DBLPPlusPlusBindingStub _stub = new net.sf.jabref.imports.ws.DBLPPlusPlusBindingStub(new java.net.URL(DBLPPlusPlusPort_address), this);
                _stub.setPortName(getDBLPPlusPlusPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DBLPPlusPlusPort".equals(inputPortName)) {
            return getDBLPPlusPlusPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:DBLPPlusPlus", "DBLPPlusPlusService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:DBLPPlusPlus", "DBLPPlusPlusPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DBLPPlusPlusPort".equals(portName)) {
            setDBLPPlusPlusPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
