import org.junit.Test;
import org.leibnizcenter.uk.legislation.ApiInterface;
import org.leibnizcenter.uk.legislation.SearchRequestBuilder;
import org.leibnizcenter.uk.legislation.TableOfContentsElement;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Sample fetching a single legislation
 * <p>
 * Created by maarten on 31-8-15.
 */

public class SingleDocTest {

    @Test
    public void parseMetadata() {
        try {
            TopLevelUri uri = new TopLevelUri("http://www.legislation.gov.uk/anaw/2015/4", 2015, 4);
            Entry e = ApiInterface.getSingleEntryFromFeed(uri);
            assertMetadataElements(e);

            List<Legislation> tocs = e.getAllTableOfContents();

            for (Legislation toc : tocs) {
                traverseToc(toc.getContents());
            }
        } catch (ApiInterface.FeedException | ParserConfigurationException | JAXBException | IOException | SAXException e1) {
            throw new Error(e1);
        }
    }

    private void traverseToc(TableOfContentsElement contents) {
        for (TableOfContentsElement child : contents.getToCChildren()) {
            traverseToc(child);
        }
    }

    private void assertMetadataElements(Entry e) {
//        assertEquals("??", e.getNumber(), 20);
//        assertEquals("??", e.getYear(), 20);
//        assertEquals("??", e.getTableOfContentsLinks(), 20);
//        assertEquals("??", e.getBase(), 20);
//        assertEquals("??", e.getDocumentMainType(), 20);
        System.out.println(e.getDocumentMainType());
    }


}
