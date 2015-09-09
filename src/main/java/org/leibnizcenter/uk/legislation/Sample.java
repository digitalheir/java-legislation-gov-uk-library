package org.leibnizcenter.uk.legislation;

import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Contents;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Sample class
 * <p>
 * Created by maarten on 9-8-15.
 */
public class Sample {
    public static void main(String[] args) {
        try {
            // Get search feed
            Feed feed = ApiInterface.getSearchFeed(
                    new SearchRequestBuilder().setStartNumber("4").setEndYear("2015").build()
            );
            for (Entry e : feed.getEntries()) {
                System.out.println("Title: " + e.getTitle());
            }

            // Get single entry and ToC
            TopLevelUri uri = new TopLevelUri("http://www.legislation.gov.uk/anaw/2015/4",2015,4);
            Entry e = ApiInterface.getSingleEntryFromFeed(uri);

            for (Legislation tableOfContentsDocument : e.getAllTableOfContents()) {
                Contents contents = tableOfContentsDocument.getContents();
                System.out.println("Title: " + contents.getContentsTitle());
                for (TableOfContentsElement tocElement : contents.getToCChildren()) {
                    System.out.println("> " + tocElement);
                }
            }
        } catch (ParserConfigurationException | JAXBException | IOException | SAXException e1) {
            e1.printStackTrace();
        }
    }

    private static void something(Object thing) {
//        for (int i = 0; i < depth; i++) System.out.print(" ");
//
//        System.out.println(thing.getClass().getSimpleName());
//
//        if (thing.getClass().equals(ContentsPart.class)) {
//            for (Object thing2 : ((ContentsPart) thing).getContentsChaptersAndContentsPblocksAndContentsItems())
//                something(thing2);
//        } else if (thing.getClass().equals(ContentsItem.class)) {
//            for (Object thing2 : ((ContentsItem) thing).getContentsSubItems())
//                something(thing2);
//        } else if (thing.getClass().equals(ContentsSubItem.class)) {
//            for (Object thing2 : ((ContentsSubItem) thing).getContentsSubItems())
//                something(thing2);
//        } else if (thing.getClass().equals(ContentsPblock.class)) {
//            for (Object thing2 : ((ContentsPblock) thing).getContentsPsubBlocksAndContentsItems())
//                something(thing2);
//        } else if (thing.getClass().equals(ContentsPsubBlock.class)) {
//            for (Object thing2 : ((ContentsPsubBlock) thing).getContentsItems())
//                something(thing2);
//        }
    }
}
