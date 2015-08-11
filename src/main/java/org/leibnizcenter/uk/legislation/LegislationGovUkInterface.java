package org.leibnizcenter.uk.legislation;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.w3._2005.atom.Link;
import org.xml.sax.SAXException;
import uk.gov.legislation.namespaces.legislation.Legislation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Interface for querying Legislation.gov.uk atom feed and individual documents
 * <p/>
 * Created by maarten on 18-6-15.
 */
public class LegislationGovUkInterface {
    /**
     * Do not instantiate
     */
    private LegislationGovUkInterface() {
    }

    /**
     * @param builder A fully initialized request builder
     * @return The feed resulting from this request
     * @see UkLawSearchRequestBuilder
     */
    public static Feed getSearchFeed(UkLawSearchRequestBuilder builder) throws IOException, ParserConfigurationException, SAXException, JAXBException {
        return getSearchFeed(builder.build());
    }

    /**
     * @param request The HTTP request for the feed, initialized with a correct URL
     * @return The feed resulting from this request
     */
    private static Feed getSearchFeed(Request request) throws IOException, ParserConfigurationException, SAXException, JAXBException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(false);
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Feed.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Feed) jaxbUnmarshaller.unmarshal(response.body().byteStream());
        } else {
            return new Feed();
        }
    }

    /**
     * We use the atom feed to get metadata for single laws, because '{uri}/data.xml' lists links to HTML
     * manifestations that do not exist in actuality. For example;
     * <a href="http://www.legislation.gov.uk/uksi/1986/1628/made/data.xml">uksi/1986/1628</a>.
     */
    public static Entry getSingleEntryFromFeed(UkUriObject uri) throws ParserConfigurationException, SAXException, IOException, JAXBException {
        List<Entry> feed = getSearchFeed(new Request.Builder().url(uri.feedURL).build()).getEntries();

        if (feed.size() != 1) {
            throw new IllegalStateException(uri + " contained " + feed.size() + " items, expected 1.");
        }
        return feed.get(0);
    }

    /**
     * TODO
     * <p/>
     * Created by Maarten on 2-3-2015.
     */
    public static String getReaderDoc(String representationUri) throws ParserConfigurationException, JAXBException, SAXException, IOException {
        Entry entry = getSingleEntryFromFeed(new UkUriObject(representationUri));
        for (Link link : entry.getLinks()) {
            switch (link.getRel()) {
                case "alternate":
                    String lang = link.getHreflang() == null ? "en" : link.getHreflang(); //Default language is English
                    switch (link.getType()) {
                        case "application/xml":
//                            link.href;
                            break;
                        case "application/xhtml+xml":
//                            link.href;
                            break;
                    }
            }
        }

        return null;
    }

    public static Legislation parseLegislationDoc(String url) throws IOException, JAXBException {
        return parseLegislationDoc(new Request.Builder().url(url).build());
    }

    public static Legislation parseLegislationDoc(Request request) throws IOException, JAXBException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(false);
        Response response = httpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Legislation.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Legislation) jaxbUnmarshaller.unmarshal(response.body().byteStream());
        } else {
            return null;
        }
    }

//    public static LegislationDataXmlParser.Metadata getMetadata(InputStream response) throws IOException, SAXException, ParserConfigurationException {
//        LegislationDataXmlParser p = new LegislationDataXmlParser();
//        XMLReader xr = getAtomFeedReader(p);
//        xr.parse(new InputSource(response));
//        return p.getData();
//    }
}
