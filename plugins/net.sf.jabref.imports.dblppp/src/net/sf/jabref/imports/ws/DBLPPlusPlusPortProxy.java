package net.sf.jabref.imports.ws;

public class DBLPPlusPlusPortProxy implements net.sf.jabref.imports.ws.DBLPPlusPlusPort {
  private String _endpoint = null;
  private net.sf.jabref.imports.ws.DBLPPlusPlusPort dBLPPlusPlusPort = null;
  
  public DBLPPlusPlusPortProxy() {
    _initDBLPPlusPlusPortProxy();
  }
  
  private void _initDBLPPlusPlusPortProxy() {
    try {
      dBLPPlusPlusPort = (new net.sf.jabref.imports.ws.DBLPPlusPlusServiceLocator()).getDBLPPlusPlusPort();
      if (dBLPPlusPlusPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dBLPPlusPlusPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dBLPPlusPlusPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dBLPPlusPlusPort != null)
      ((javax.xml.rpc.Stub)dBLPPlusPlusPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.sf.jabref.imports.ws.DBLPPlusPlusPort getDBLPPlusPlusPort() {
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort;
  }
  
  public net.sf.jabref.imports.ws.Sequence[] all_proceeding_titles() throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_proceeding_titles();
  }
  
  public net.sf.jabref.imports.ws.Sequence[] all_journal_titles() throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_journal_titles();
  }
  
  public net.sf.jabref.imports.ws.Sequence[] all_journal_titles_year(java.math.BigInteger year) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_journal_titles_year(year);
  }
  
  public net.sf.jabref.imports.ws.Sequence2[] all_publications_author_year(java.lang.String firstName, java.lang.String familyName, java.math.BigInteger startYear, java.math.BigInteger endYear) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_publications_author_year(firstName, familyName, startYear, endYear);
  }
  
  public net.sf.jabref.imports.ws.Sequence3[] publication_data(java.lang.String dblp_key) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.publication_data(dblp_key);
  }
  
  public net.sf.jabref.imports.ws.Sequence4[] publication_authors(java.lang.String dblp_key) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.publication_authors(dblp_key);
  }
  
  public java.lang.String venue_title(java.lang.String dblp_key) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.venue_title(dblp_key);
  }
  
  public net.sf.jabref.imports.ws.Sequence5[] all_publications_venue_year(java.lang.String dblp_key_frag, java.math.BigInteger startYear, java.math.BigInteger endYear) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_publications_venue_year(dblp_key_frag, startYear, endYear);
  }
  
  public net.sf.jabref.imports.ws.Sequence6[] all_publications_keywords_year(java.lang.String searchTerm, java.math.BigInteger startYear, java.math.BigInteger endYear, java.math.BigInteger limit) throws java.rmi.RemoteException{
    if (dBLPPlusPlusPort == null)
      _initDBLPPlusPlusPortProxy();
    return dBLPPlusPlusPort.all_publications_keywords_year(searchTerm, startYear, endYear, limit);
  }
  
  
}