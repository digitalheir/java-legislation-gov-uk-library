import org.junit.Test;
import org.leibnizcenter.uk.legislation.ApiInterface;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Link;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maarten on 23-9-15.
 */
public class HtmlSnippetsTest {
    // http://www.legislation.gov.uk/anaw/2014/7
    private static final String TYPE = "anaw";
    private static final int YEAR = 2015;
    private static final int NUMBER = 5;

    @Test
    public void parseMetadata() {
        try {
            String url = "http://www.legislation.gov.uk/" + TYPE + "/" + YEAR + "/" + NUMBER;
            TopLevelUri uri = new TopLevelUri(url, YEAR, NUMBER);
            Entry e = ApiInterface.getSingleEntryFromFeed(uri);

            Map<String, Link> htmlURLs = e.getHtmlSnippetsLinksByLanguage();

            for (Link l : htmlURLs.values()) {
//                System.out.println(
                ApiInterface.downloadString(l.getHref()
//                        )
                );
            }

            assertEquals(htmlURLs.size(), 2);
        } catch (ApiInterface.FeedException | ParserConfigurationException | JAXBException | IOException | SAXException e1) {
            throw new Error(e1);
        }
    }
}
