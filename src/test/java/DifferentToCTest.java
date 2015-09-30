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

import static org.junit.Assert.assertTrue;

/**
 * Test a document which has two table of contents (one English, one Welsh)
 * <p/>
 * Created by maarten on 28-9-15.
 */
public class DifferentToCTest {

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


}
