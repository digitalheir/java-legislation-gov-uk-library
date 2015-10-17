import org.junit.Test;
import org.leibnizcenter.uk.legislation.ApiInterface;
import org.leibnizcenter.uk.legislation.TableOfContentsElement;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Sample fetching a single legislation ToC
 * <p>
 * Created by maarten on 21-9-15.
 */

public class TableOfContentsTest {
    // http://www.legislation.gov.uk/anaw/2014/7
    private static final String TYPE = "anaw";
    private static final int YEAR = 2014;
    private static final int NUMBER = 7;

    @Test
    public void parseToC() {
        try {
            String url = "http://www.legislation.gov.uk/" + TYPE + "/" + YEAR + "/" + NUMBER;
            TopLevelUri uri = new TopLevelUri(url, YEAR, NUMBER);
            Entry e = ApiInterface.getSingleEntryFromFeed(uri);
            assertMetadataElements(e);

            Map<String, Legislation> tocs = e.getTableOfContentsByLanguage();

            for (Map.Entry<String, Legislation> p : tocs.entrySet()) {
                Legislation toc = p.getValue();
                int totalChildren = traverseToc(toc.getContents(), false);
                assertTrue("Document must contain 150 or more elements", totalChildren >= 150);
            }
        } catch (ApiInterface.FeedException | ParserConfigurationException | JAXBException | IOException | SAXException e1) {
            throw new Error(e1);
        }
    }

    private int traverseToc(TableOfContentsElement contents, boolean scrapedHtmlYet) {
        int total = 0;
        for (TableOfContentsElement child : contents.getToCChildren()) {
            if (child.getIdURI() == null || child.getIdURI().length() <= 0 ||
                    child.getDocumentURI() == null || child.getDocumentURI().length() <= 0
                    ) {
                throw new Error("Element URI or document URL was null or empty");
            }

            if (!scrapedHtmlYet) {
                try {
                    ApiInterface.scrapeHtmlContent(child.getToCChildren().get(0).getDocumentURI());
//                System.out.println(e.toString());
                } catch (IOException e) {
                    throw new Error(e);
                }
            }
            total += traverseToc(child, true);
        }
//        if(contents.getToCChildren().size()<=0){
//            System.out.println(contents);
//        }
        return total + 1;
    }

    private void assertMetadataElements(Entry e) {
        assertEquals("Number must be " + NUMBER, e.getNumber().asInt(), NUMBER);
        assertEquals("Year must be " + YEAR, e.getYear().asInt(), YEAR);
        assertTrue("Must have at least 2 ToCs", e.getTableOfContentsLinks().size() >= 2);
        assertEquals("Creation date must be 2014-09-17", e.creationDate.toString(), "2014-09-17");
//        assertEquals("??", e.getBase(), 20);
//        assertEquals("??", e.getDocumentMainType(), 20);
//        for (Link l : e.getTableOfContentsLinks()) {
//        }
    }
}
