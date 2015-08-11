package org.leibnizcenter.uk.legislation;

import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Link;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.*;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by maarten on 9-8-15.
 */
public class Test {

    public static void main(String[] args) {
        try {
            UkUriObject uri = new UkUriObject("http://www.legislation.gov.uk/anaw/2015/4");
            Entry e = LegislationGovUkInterface.getSingleEntryFromFeed(uri);

            for (Link l : e.getTableOfContentsLinks()) {
                Legislation leg = LegislationGovUkInterface.parseLegislationDoc(l.getHref() + "/data.xml");

                List<Object> things = leg.getContents().getContentsGroupsAndContentsPartsAndContentsChapters();

                for (Object thing : things) {
                    something(thing, 0);
                }
            }
        } catch (ParserConfigurationException | JAXBException | IOException | SAXException e1) {
            e1.printStackTrace();
        }
    }

    private static void something(Object thing, int depth) {
        for (int i = 0; i < depth; i++) System.out.print(" ");

        System.out.println(thing.getClass().getSimpleName());

        if (thing.getClass().equals(ContentsPart.class)) {
            for (Object thing2 : ((ContentsPart) thing).getContentsChaptersAndContentsPblocksAndContentsItems())
                something(thing2, depth + 1);
        } else if (thing.getClass().equals(ContentsItem.class)) {
            for (Object thing2 : ((ContentsItem) thing).getContentsSubItems())
                something(thing2, depth + 1);
        } else if (thing.getClass().equals(ContentsSubItem.class)) {
            for (Object thing2 : ((ContentsSubItem) thing).getContentsSubItems())
                something(thing2, depth + 1);
        } else if (thing.getClass().equals(ContentsPblock.class)) {
            for (Object thing2 : ((ContentsPblock) thing).getContentsPsubBlocksAndContentsItems())
                something(thing2, depth + 1);
        } else if (thing.getClass().equals(ContentsPsubBlock.class)) {
            for (Object thing2 : ((ContentsPsubBlock) thing).getContentsItems())
                something(thing2, depth + 1);
        }
    }
}
