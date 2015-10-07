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
 * <p>en
 * Created by maarten on 5-8-15.
 */

public class FeedTest {


    @Test
    public void parseUkpga() {
        try {
            FeedRequestBuilder b = new FeedRequestBuilder();
            b.setType("ukpga");
            Feed listing = ApiInterface.getSearchFeed(b.build());
            System.out.println(">> Opening page " + b.buildUrl());
            assertThingsAboutUkpga(listing);
            b.setYear(2014);
            System.out.println(">> Opening page " + b.buildUrl());
            listing = ApiInterface.getSearchFeed(b.build());
            assertThingsAboutUkpga2014(listing);

//            b.setNumber(2);
//            System.out.println(">> Opening page "+b.buildUrl());
//            listing = ApiInterface.getSearchFeed(b.build());
//            assertThingsAboutUkpga20142(listing);

        } catch (ApiInterface.FeedException | IOException | JAXBException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void assertThingsAboutUkpga20142(Feed listing) {
        //TODO
    }

    private void assertThingsAboutUkpga2014(Feed listing) {
        //TODO
    }

    private void assertThingsAboutUkpga(Feed listing) {
        //TODO
    }

    /**
     * feed including ukpga/Vict/13-14/98
     */
    @Test
    public void parse1850Query() {
        try {
            FeedRequestBuilder b = new FeedRequestBuilder();
            System.out.println(">> " + b.buildUrl());
            b.setYear(1850);
            b.setType("ukpga");
            Feed listing = ApiInterface.getSearchFeed(b.build());
            //assertEquals("Result size must be 20", listing.getEntries().size(), 20);

            boolean hasPluratiesActs = false;
            for (Entry e : listing.getEntries()) {
                if ("http://www.legislation.gov.uk/id/ukpga/Vict/13-14/98".equals(e.id)) {
                    hasPluratiesActs = true;
                    assertMetadataAboutPluratiesAct(e);
                }
            }
            assertTrue(hasPluratiesActs);
        } catch (ApiInterface.FeedException | IOException | ParserConfigurationException | SAXException | JAXBException e) {
            throw new Error(e);
        }
    }

    private void assertMetadataAboutPluratiesAct(Entry e) {
        assertTrue(e.links.size() >= 8);
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
        System.out.println(">> " + uri.feedURL);
    }

    /**
     * Sample to list the first 10 pages of legislation
     */
    @Test
    public void parseFirstTenPagesOfAll() {
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
        } catch (ApiInterface.FeedException | IOException | ParserConfigurationException | SAXException | JAXBException e) {
            throw new Error(e);
        }
    }

    /**
     * Sample to list a page in the feed yields no results
     */
    @Test
    public void parseSearchPageOutOfScope() {
        try {
            SearchRequestBuilder b = new SearchRequestBuilder();
            b.setPage(999999999);
            try {
                Feed listing = ApiInterface.getSearchFeed(b);
                assertEquals("Result entries must be 0", listing.getEntries().size(), 0);
            } catch (ApiInterface.FeedException ignored) {

            }
        } catch (IOException | ParserConfigurationException | JAXBException | SAXException e) {
            throw new Error(e);
        }
    }

    /**
     * Sample to list a page in the feed yields no results
     */
    @Test
    public void parseFeedPageOutOfScope() {
        try {
            FeedRequestBuilder b = new FeedRequestBuilder();
            b.setPage(999999999);
            try {
                Feed listing = ApiInterface.getFeed(b);
                assertEquals("Result entries must be 0", listing.getEntries().size(), 0);
            } catch (ApiInterface.FeedException ignored) {

            }
        } catch (IOException | ParserConfigurationException | JAXBException | SAXException e) {
            throw new Error(e);
        }
    }

    @Test
    public void testUkpga2015() {
        int page = 0;
        FeedRequestBuilder fBuilder = new FeedRequestBuilder().setPage(page).setType("ukpga").setYear("2015");

        Feed feed = null;
        do {
            try {
                page++;
                fBuilder.setPage(page);
                feed = ApiInterface.getFeed(fBuilder);

            } catch (ParserConfigurationException | JAXBException | SAXException | IOException | ApiInterface.FeedException e) {
                throw new Error(e);
            }
        } while (feed != null && feed.getPage() < feed.getMorePages());

        assertTrue(page >= 2);

        page = 0;
        SearchRequestBuilder sBuilder = new SearchRequestBuilder()
                .setPage(page)
                .setType("ukpga")
                .setEndYear("2015")
                .setStartYear("2015");
        do {
            try {
                page++;
                sBuilder.setPage(page);
                feed = ApiInterface.getFeed(sBuilder);
            } catch (ParserConfigurationException | JAXBException | SAXException | IOException | ApiInterface.FeedException e) {
                throw new Error(e);
            }
        } while (feed != null && feed.getPage() < feed.getMorePages());
        assertTrue(page >= 2);
    }


//    /**
//     * Sample to list a page in the feed yields no results
//     */
//    @Test
//    public void parsePageOutOfScope() {
//        try {
//            SearchRequestBuilder b = new SearchRequestBuilder();
//            b.setPage(999999999);
//
//            try {
//                ApiInterface.getSearchFeed(b);
//                assertEquals("Result must lead to exception", true, false);
//            } catch (ApiInterface.FeedException ignored) {
//            }
//        } catch (IOException | ParserConfigurationException | JAXBException | SAXException e) {
//            e.printStackTrace();
//        }
//    }
}
