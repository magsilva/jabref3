package net.sf.jabref.imports;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.imports.ACMFetcher;

public class ACMFetcherTest extends TestCase {

    ACMFetcher f;

    protected void setUp() throws Exception {
        super.setUp();

        f = new ACMFetcher() {

            Map<String, String> urlToHTML;

            public String fetchPost(URL queryTarget, String data)
                throws IOException {

                if (data.contains("noresulttest")) {
                    return getUrlToHTML().get("noresulttest");
                }
                if (data.contains("onepageresult")) {
                    return getUrlToHTML().get("onepageresult");
                }
                if (data.contains("multipageresult")) {
                    return getUrlToHTML().get("multipageresult");
                }

                throw new IOException("Should not try to access the internet");
            }

            /**
             * Returns previously saved html code of result pages, bibtex html
             * etc. from the ACM library as of 19.11.2007
             */
            public String fetchSite(URL site) throws IOException {

                if (!getUrlToHTML().containsKey(site.toExternalForm()))
                    throw new IOException(
                        "Should not try to access the internet");

                return getUrlToHTML().get(site.toExternalForm());
            }

            protected String readFileContents(String filename)
                throws IOException {
                InputStream in = ACMFetcherTest.class
                    .getResourceAsStream(filename);
                StringBuffer sb = new StringBuffer();
                byte[] buffer = new byte[256];
                while (true) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1)
                        break;
                    for (int i = 0; i < bytesRead; i++)
                        sb.append((char) buffer[i]);
                }
                return sb.toString();
            }

            public Map<String, String> getUrlToHTML() throws IOException {
                if (urlToHTML == null) {
                    urlToHTML = new HashMap<String, String>();
                    urlToHTML.put("noresulttest",
                        readFileContents("acm_noresults.html"));
                    urlToHTML.put("onepageresult",
                        readFileContents("acm_onepage.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/citation.cfm?id=1185904&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_article.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/popBibTex.cfm?id=1185904&ids=SERIES382.1185884.1185885.1185904&types=series.proceeding.section.article&reqtype=article&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_bibtex.html"));
                    urlToHTML.put("multipageresult",
                        readFileContents("acm_multipage1.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=21&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage2.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=41&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage3.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=61&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage4.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=81&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage5.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=101&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage6.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=121&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage7.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=141&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage8.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=161&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage9.html"));
                    urlToHTML
                        .put(
                            "http://portal.acm.org/results.cfm?query=cotton&querydisp=cotton&start=181&slide=1&srt=score%20dsc&short=0&parser=Internet&source_parser=Internet&source_disp=&source_query=&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
                            readFileContents("acm_multipage10.html"));
                }
                return urlToHTML;
            }
        };
    }

    /**
     * tests if the fetcher can handle the response of the acm library when
     * there are no results at all
     * 
     * @throws IOException
     */
    public void testNoResults() throws IOException {
        assertEquals(0, f.parseNumberOfHits(f.executeQuery("noresulttest")));
    }

    public void testLoadArticleURLs() throws IOException {
        String hitlist = f.executeQuery("onepageresult");
        List<String> urls = f.loadArticleURLs(hitlist);

        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=1185904&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=981635&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=1298453&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=1125745&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=804492&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=584969&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=997985&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=75340&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
        assertTrue(urls
            .contains("http://portal.acm.org/citation.cfm?id=1149974&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097"));
    }

    public void testParsingBibtexFromArticle() throws IOException {
        String hitlist = f.executeQuery("onepageresult");
        List<String> urls = f.loadArticleURLs(hitlist);

        String url = urls.get(0);
        assertEquals(
            "http://portal.acm.org/citation.cfm?id=1185904&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
            url);

        BibtexEntry e = f.fetchArticleBibtex(url);

        assertEquals("Hans Dehlinger: \"ohne_Titel\" (2004)", e
            .getAuthorTitleYear(500));
    }

    public void testParsingMultipleResultPages() throws IOException {
        String hitlist = f.executeQuery("multipageresult");
        List<String> urls = f.loadArticleURLs(hitlist);

        String url = urls.get(0);
        assertEquals(
            "http://portal.acm.org/citation.cfm?id=1040146&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
            url);
        url = urls.get(199);
        assertEquals(
            "http://portal.acm.org/citation.cfm?id=980604&coll=Portal&dl=ACM&CFID=43787999&CFTOKEN=74051097",
            url);
    }

    public void testGetNumberOfHits() {
        assertEquals(
            200,
            f.parseNumberOfHits("Found <b>540</b> of <b> 215,186</b></td>	</tr></table></td></tr"));
    }
}
