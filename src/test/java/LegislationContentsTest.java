import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.leibnizcenter.uk.legislation.ApiInterface;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test a document which has two table of contents (one English, one Welsh)
 * <p>
 * Created by maarten on 28-9-15.
 */
public class LegislationContentsTest {

    @Test
    public void testUkpga2015_15() throws ParserConfigurationException, JAXBException, ApiInterface.FeedException, SAXException, IOException {
        TopLevelUri uri = new TopLevelUri("http://www.legislation.gov.uk/id/ukpga/2015/15", 2015, 15);

        Entry entry = ApiInterface.getSingleEntryFromFeed(uri);
        Map<String, Document> langMap = entry.getLanguageToHtmlMap();

        Assert.assertTrue(langMap.size() > 0);
        Document doc = langMap.get("en");

        String className = "body";
        Elements nodez = doc.body().select("body > article > ." + className);

        Assert.assertEquals(1, nodez.size());
    }

    @Test
    public void testDoubleToC() {
        TopLevelUri uri = new TopLevelUri("http://www.legislation.gov.uk/id/anaw/2015/5", 2015, 5);

        Entry entry = null;
        try {
            entry = ApiInterface.getSingleEntryFromFeed(uri);
            Map<String, Legislation> tocs = entry.getTableOfContentsByLanguage();

            assertTrue(tocs.size() > 1);

            Map.Entry<String, Legislation> first = null;
            for (Map.Entry<String, Legislation> e : tocs.entrySet()) {
                if (first == null) {
                    first = e;
                } else {
                    assertTrue(ApiInterface.hasSameIndexStructure(e.getValue().getContents(), first.getValue().getContents()));

                    // This is not true, because there is some idea containing an English/Welsh word, which is why they
                    // are different
                    assertTrue(!ApiInterface.hasSameIdStructure(e.getValue().getContents(), first.getValue().getContents()));
                }
            }
        } catch (ParserConfigurationException | ApiInterface.FeedException | JAXBException | IOException | SAXException e) {
            throw new Error(e);
        }
    }


    @Test
    public void testPrimary() {
        String xmlUrl = "http://legislation.data.gov.uk/ukpga/2015/32/2015-09-21/data.xml";
        try {
            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
            assertNotNull(legislation.getPrimary());
        } catch (JAXBException | IOException e) {
            throw new Error(e);
        }
    }


//    @Test
//    public void testSecondary() {
//        String xmlUrl = " ";
//        try {
//            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
//            assertNotNull(legislation.getSecondary());
//        } catch (JAXBException | IOException e) {
//            throw new Error(e);
//        }
//    }

    @Test
    public void testCommentaries() {
        String xmlUrl = "http://legislation.data.gov.uk/ukpga/2015/30/2015-05-26/data.xml";
        try {
            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
            assertTrue(legislation.getCommentaries().getCommentaries().size() > 0);
        } catch (JAXBException | IOException e) {
            throw new Error(e);
        }
    }

    @Test
    public void testVersions() {
        String xmlUrl = "http://legislation.data.gov.uk/ukpga/2015/21/2015-03-26/data.xml";
        try {
            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
            assertTrue(legislation.getVersions().getVersions().size() > 0);
        } catch (JAXBException | IOException e) {
            throw new Error(e);
        }
    }

    @Test
    public void testResources() {
        String xmlUrl = "http://legislation.data.gov.uk/ukpga/2015/11/2015-03-26/data.xml";
        try {
            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
            assertTrue(legislation.getResources().getResourcesAndResourceGroups().size() > 0);
        } catch (JAXBException | IOException e) {
            throw new Error(e);
        }
    }

//    @Test
//    public void testCitation() {
//
//        String xmlUrl = "";
//
//
//        try {
//            Legislation legislation = ApiInterface.parseLegislationDoc(xmlUrl);
//            assertTrue(legislation.getCitationLists().getCitationLists().size() > 0);
//        } catch (JAXBException | IOException e) {
//            throw new Error(e);
//        }
//    }

}
