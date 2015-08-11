import org.junit.Test;
import org.leibnizcenter.uk.legislation.LegislationGovUkInterface;
import org.leibnizcenter.uk.legislation.UkLawSearchRequestBuilder;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class
 * <p>
 * Created by maarten on 5-8-15.
 */

public class LegislationInterface {

    /**
     * Test to list the first 10 pages of legislation
     */
    @Test
    public void parseFirstTenPages() {
        try {

            for (int page = 1; page <= 10; page++) {
                UkLawSearchRequestBuilder b = new UkLawSearchRequestBuilder();
                System.out.println("Opening page " + page);
                b.setPage(page);
                Feed listing = LegislationGovUkInterface.getSearchFeed(b);
                assertEquals("Result size must be 20", listing.getEntries().size(), 20);

                for (Entry e : listing.getEntries()) {
                    assertTrue(e.id != null);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException | JAXBException e) {
            throw new Error(e);
        }
    }


    /**
     * Test to list a page in the feed yields no results
     */
    @Test
    public void parsePageOutOfScope() {
        try {
            UkLawSearchRequestBuilder b = new UkLawSearchRequestBuilder();
            b.setPage(Integer.MAX_VALUE);
            Feed listing = LegislationGovUkInterface.getSearchFeed(b);
            assertEquals("Result entries must be 0", listing.getEntries().size(), 0);
        } catch (IOException | ParserConfigurationException | JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }
}
