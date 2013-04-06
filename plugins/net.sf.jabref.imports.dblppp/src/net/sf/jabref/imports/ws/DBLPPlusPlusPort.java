/**
 * DBLPPlusPlusPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.jabref.imports.ws;

public interface DBLPPlusPlusPort extends java.rmi.Remote {
    public net.sf.jabref.imports.ws.Sequence[] all_proceeding_titles() throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence[] all_journal_titles() throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence[] all_journal_titles_year(java.math.BigInteger year) throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence2[] all_publications_author_year(java.lang.String firstName, java.lang.String familyName, java.math.BigInteger startYear, java.math.BigInteger endYear) throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence3[] publication_data(java.lang.String dblp_key) throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence4[] publication_authors(java.lang.String dblp_key) throws java.rmi.RemoteException;
    public java.lang.String venue_title(java.lang.String dblp_key) throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence5[] all_publications_venue_year(java.lang.String dblp_key_frag, java.math.BigInteger startYear, java.math.BigInteger endYear) throws java.rmi.RemoteException;
    public net.sf.jabref.imports.ws.Sequence6[] all_publications_keywords_year(java.lang.String searchTerm, java.math.BigInteger startYear, java.math.BigInteger endYear, java.math.BigInteger limit) throws java.rmi.RemoteException;
}
