# Legislation.gov.uk library

A wrapper around the

## Usage
To parse a feed and get the table of contents for the first entry

```java
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Contents;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Sample {
    public static void main(String[] args) {
            // Get search feed
            Feed feed = ApiInterface.getSearchFeed(
                    new SearchRequestBuilder().setStartNumber("4").setEndYear("2015").build()
            );
            for (Entry e : feed.getEntries()) {
                System.out.println("Title: " + e.getTitle());
            }

            // Get single entry and ToC
            TopLevelUri uri = new TopLevelUri("http://www.legislation.gov.uk/anaw/2015/4");
            Entry e = ApiInterface.getSingleEntryFromFeed(uri);

            for (Legislation tableOfContentsDocument : e.getAllTableOfContents()) {
                Contents contents = tableOfContentsDocument.getContents();
                System.out.println("Title: " + contents.getContentsTitle());
                for (TableOfContentsElement tocElement : contents.getToCChildren()) {
                    System.out.println("> " + tocElement);
                }
            }
    }
}
```

## More
For more information about the API, see http://www.legislation.gov.uk/developer or [a
report about legislation.gov.uk](http://leibniz-internship-report.herokuapp.com/eu-legal-data-survey/uk#section-legislation-gov-uk).