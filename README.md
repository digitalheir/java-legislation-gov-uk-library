# Legislation.gov.uk library
[![GitHub version](https://badge.fury.io/gh/digitalheir%2Fjava-legislation-gov-uk-library.svg)](http://badge.fury.io/gh/digitalheir%2Fjava-legislation-gov-uk-library)
[![Build Status](https://travis-ci.org/digitalheir/java-legislation-gov-uk-library.svg?branch=master)](https://travis-ci.org/digitalheir/java-legislation-gov-uk-library)

A Java interface to Legislation.gov.uk.

## JavaDoc
JavaDoc is available at http://phil.uu.nl/~trompper/legislation-gov-uk-1.2.1-javadoc/

## Usage
Download [the latest JAR](https://github.com/digitalheir/java-legislation-gov-uk-library/releases/latest) or grab from Maven:

```xml
<dependencies>
        <dependency>
            <groupId>org.leibnizcenter</groupId>
            <artifactId>legislation-gov-uk</artifactId>
            <version>1.2.1</version>
        </dependency>
</dependencies>
```

or Gradle:
```groovy
compile 'org.leibnizcenter:legislation-gov-uk:1.2.1'
```

To parse a feed and get the table of contents for the first entry:

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

Inquiries go to m.f.a.trompper@uva.nl.
