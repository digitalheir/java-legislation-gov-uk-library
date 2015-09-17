import org.junit.Test;
import org.leibnizcenter.uk.legislation.ApiInterface;
import org.leibnizcenter.uk.legislation.FeedRequestBuilder;
import org.leibnizcenter.uk.legislation.SearchRequestBuilder;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Sample class
 * <p>
 * Created by maarten on 5-8-15.
 */

public class FeedTest {

// TODO feed including ukpga/Vict/13-14/98

    @Test
    public void parse1850Query() {
        try {
            SearchRequestBuilder b = new SearchRequestBuilder();
            System.out.println(">> Opening page ukpga/1850/data.feed");
            b.setStartYear(1850 + "");
            b.setEndYear(1850 + "");
            b.setType("ukpga");
            Feed listing = ApiInterface.getSearchFeed(b);
            //assertEquals("Result size must be 20", listing.getEntries().size(), 20);

            boolean hasPluratiesActs = false;
            for (Entry e : listing.getEntries()) {
                if ("http://www.legislation.gov.uk/id/ukpga/Vict/13-14/98".equals(e.id)) {
                    hasPluratiesActs = true;
                    assertMetadataAboutPluratiesAct(e);
                }
            }
            assertTrue(hasPluratiesActs);
        } catch (IOException | ParserConfigurationException | SAXException | JAXBException e) {
            throw new Error(e);
        }
    }

    private void assertMetadataAboutPluratiesAct(Entry e) {
        assertTrue(e.links.size()>=8);
        assertEquals(e.published, "1850-08-14T00:00:00+01:00");
        assertEquals(e.documentMainType.getValue().value(), "UnitedKingdomPublicGeneralAct");
        assertEquals(e.getYear().asInt(), 1850);
        assertEquals(e.getNumber().asInt(), 98);
        try {
            assertEquals(e.getCreationDate().asDate(), new SimpleDateFormat("YYYY-MM-dd").parse("1850-08-14"));
        } catch (ParseException e1) {
            throw new Error(e1);
        }

        TopLevelUri uri = e.getUriObject();
        System.out.println(">> "+uri.feedURL);
    }

    /**
     * Sample to list the first 10 pages of legislation
     */
    @Test
    public void parseFirstTenPages() {
        try {
            for (int page = 1; page <= 0; page++) {
                FeedRequestBuilder b = new FeedRequestBuilder();
                System.out.println(">> Opening page " + page);
                b.setPage(page);
                Feed listing = ApiInterface.getSearchFeed(b.build());
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
     * Sample to list a page in the feed yields no results
     */
    @Test
    public void parsePageOutOfScope() {
        try {
            SearchRequestBuilder b = new SearchRequestBuilder();
            b.setPage(Integer.MAX_VALUE);
            Feed listing = ApiInterface.getSearchFeed(b);
            assertEquals("Result entries must be 0", listing.getEntries().size(), 0);
        } catch (IOException | ParserConfigurationException | JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }
}
