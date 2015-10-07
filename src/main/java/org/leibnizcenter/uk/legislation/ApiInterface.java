package org.leibnizcenter.uk.legislation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.leibnizcenter.uk.legislation.uri.TopLevelUri;
import org.w3._2005.atom.Entry;
import org.w3._2005.atom.Feed;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.co.tso.assets.namespace.error.ErrorType;
import uk.gov.legislation.namespaces.legislation.Contents;
import uk.gov.legislation.namespaces.legislation.Legislation;
import uk.gov.legislation.namespaces.metadata.Metadata;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Interface for querying Legislation.gov.uk atom feed and individual documents
 * <p>
 * Created by maarten on 18-6-15.
 */
public class ApiInterface {
    /**
     * Do not instantiate
     */
    private ApiInterface() {
    }

    /**
     * @param builder A fully initialized request builder
     * @return The feed resulting from this request
     * @see SearchRequestBuilder
     */
    public static Feed getSearchFeed(SearchRequestBuilder builder) throws IOException, ParserConfigurationException, SAXException, JAXBException, FeedException {
        return getSearchFeed(builder.build());
    }

    /**
     * @param request The HTTP request for the feed, initialized with a correct URL
     * @return The feed resulting from this request
     */
    public static Feed getFeed(Request request) throws IOException, ParserConfigurationException, SAXException, JAXBException, FeedException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(false);
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Feed.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Feed) jaxbUnmarshaller.unmarshal(response.body().byteStream());
        } else {
            if (response.code() == 301) {
                //Follow redirect
                String relativeUrl = response.header("Location");
                Request r = new Request.Builder().url(
//                        new HttpUrl.Builder()
//                                .scheme("http")
//                                .host("www.legislation.gov.uk")
//                                .addEncodedPathSegment(relativeUrl)
//                                .build()
                        HttpUrl.get(new URL(
                                        request.url(), relativeUrl
                                )
                        )
                ).build();
                return getFeed(r);
            } else {
                throw new FeedException("HTTP request not OK: " + response.code() + "\nURL: " + request.url());
            }
        }
    }

    /**
     * @param request The HTTP request for the feed, initialized with a correct URL
     * @return The feed resulting from this request
     */
    public static Feed getSearchFeed(Request request) throws IOException, ParserConfigurationException, SAXException, JAXBException, FeedException {
        return getFeed(request);
    }

    /**
     * <p>
     * Generates Entry from a URL such as
     * </p>
     * <p>
     * <p>NOTE: We use the atom feed to get metadata for single laws, because '{uri}/data.xml' lists links to HTML
     * manifestations that do not exist in actuality. For example;
     * <a href="http://www.legislation.gov.uk/uksi/1986/1628/made/data.xml">uksi/1986/1628</a>.</p>
     *
     * @param uri Representation URI for law
     * @return Feed entry about the given law
     */
    public static Entry getSingleEntryFromFeed(TopLevelUri uri) throws ParserConfigurationException, SAXException, IOException, JAXBException, FeedException {
        List<Entry> feed = getSearchFeed(new Request.Builder().url(uri.feedURL).build()).getEntries();

        if (feed.size() != 1) {
            throw new IllegalStateException(uri + " contained " + feed.size() + " items, expected 1.");
        }
        return feed.get(0);
    }

//    /**
//     * TODO
//     * <p/>
//     * Created by Maarten on 2-3-2015.
//     */
//    public static String getReaderDoc(String representationUri) throws ParserConfigurationException, JAXBException, SAXException, IOException {
//        Entry entry = getSingleEntryFromFeed(new TopLevelUri(representationUri));
//        for (Link link : entry.getLinks()) {
//            switch (link.getRel()) {
//                case "alternate":
//                    String lang = link.getHreflang() == null ? "en" : link.getHreflang(); //Default language is English
//                    switch (link.getType()) {
//                        case "application/xml":
////                            link.href;
//                            break;
//                        case "application/xhtml+xml":
////                            link.href;
//                            break;
//                    }
//            }
//        }
//
//        return null;
//    }

    /**
     * Downloads and parses a given legislation XML URL. This method will throw an exception if the URL does not refer to
     * legislation XML, such as <a href="http://legislation.data.gov.uk/ukpga/2007/25/2014-09-01/data.xml">http://legislation.data.gov.uk/ukpga/2007/25/2014-09-01/data.xml</a>.
     *
     * @param url URL to legislation XML
     * @return Parsed object representing XML
     * @throws IOException
     * @throws JAXBException
     */
    public static Legislation parseLegislationDoc(String url) throws IOException, JAXBException {
        return parseLegislationDoc(new Request.Builder().url(url).build());
    }

    /**
     * Downloads and parses a given legislation XML URL. This method will throw an exception if the URL does not refer to
     * legislation XML, such as <a href="http://legislation.data.gov.uk/ukpga/2007/25/2014-09-01/data.xml">http://legislation.data.gov.uk/ukpga/2007/25/2014-09-01/data.xml</a>.
     *
     * @param request HTTP request to XML document
     * @return Parsed legislation
     * @throws IOException
     * @throws JAXBException
     * @see #parseLegislationDoc(String)
     */
    public static Legislation parseLegislationDoc(Request request) throws IOException, JAXBException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(false);
        Response response = httpClient.newCall(request).execute();

        if (response.code() == 200) {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    org.w3._1998.math.mathml.ObjectFactory.class,
                    org.w3._1999.xhtml.ObjectFactory.class,
                    org.w3._1999.xsl.format.ObjectFactory.class,
                    org.w3._2005.atom.ObjectFactory.class,
                    org.purl.dc.elements._1.ObjectFactory.class,
                    org.purl.dc.terms.ObjectFactory.class,
                    uk.co.tso.assets.namespace.error.ObjectFactory.class,
                    uk.gov.legislation.namespaces.legislation.ObjectFactory.class,
                    uk.gov.legislation.namespaces.metadata.ObjectFactory.class,
                    ErrorType.class,
                    Metadata.class,
                    Legislation.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            String str = response.body().string();
            if (str.length() <= 0) {
                throw new IllegalStateException("Server should respond with XML");
            }
            return (Legislation) jaxbUnmarshaller.unmarshal(new InputSource(new StringReader(str)));
        } else {
            throw new HttpStatusException("Server responded with HTTP code " + response.code() + " instead of 200",
                    response.code(), request.url().toString());
        }
    }

    /**
     * @param builder A fully initialized request builder
     * @return The feed resulting from this request
     * @see SearchRequestBuilder
     */
    public static Feed getFeed(FeedRequestBuilder builder) throws ParserConfigurationException, JAXBException, FeedException, SAXException, IOException {
        return getSearchFeed(builder.build());
    }

    public static Feed getFeed(SearchRequestBuilder sBuilder) throws ParserConfigurationException, JAXBException, FeedException, SAXException, IOException {
        return getSearchFeed(sBuilder);
    }

    public static boolean hasSameIndexStructure(TableOfContentsElement toc1, TableOfContentsElement toc2) {
        List<TableOfContentsElement> toc1Contents = toc1.getToCChildren();
        List<TableOfContentsElement> toc2Contents = toc2.getToCChildren();

        if (toc1Contents.size() != toc2Contents.size()) {
            return false;
        } else {
            for (int i = 0; i < toc1Contents.size(); i++) {
                TableOfContentsElement el1 = toc1Contents.get(i);
                TableOfContentsElement el2 = toc2Contents.get(i);

                if (!hasSameIndexStructure(el1, el2)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean hasSameIdStructure(TableOfContentsElement toc1, TableOfContentsElement toc2) {
        List<TableOfContentsElement> toc1Contents = toc1.getToCChildren();
        List<TableOfContentsElement> toc2Contents = toc2.getToCChildren();

        if (
                toc1Contents.size() != toc2Contents.size()
                        ||
                        (!(toc1 instanceof Contents) && !(toc2 instanceof Contents) && !toc1.getIdURI().equals(toc2.getIdURI()))
                ) {
            return false;
        } else {
            for (int i = 0; i < toc1Contents.size(); i++) {
                TableOfContentsElement el1 = toc1Contents.get(i);
                TableOfContentsElement el2 = toc2Contents.get(i);

                if (!hasSameIdStructure(el1, el2)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Element scrapeHtmlContent(String url) throws IOException {
        return scrapeHtmlContent(HttpUrl.get(URI.create(url)));
    }

    public static Element scrapeHtmlContent(HttpUrl url) throws IOException {
        return parseHtml(url).getElementById("viewLegContents"); //This div contains the HTML snippet we're looking for
    }

    public static Document parseHtml(String url) throws IOException {
        return parseHtml(HttpUrl.get(URI.create(url)));
    }

    public static Document parseHtml(HttpUrl url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(true);
        Response res = httpClient.newCall(new Request.Builder().url(url).build()).execute();
        return Jsoup.parse(res.body().byteStream(), "utf-8", "http://www.legislation.gov.uk/");
    }

    public static String downloadString(String url) throws IOException {
        return downloadString(HttpUrl.get(URI.create(url)));
    }

    public static String downloadString(HttpUrl url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setFollowRedirects(true);
        Response res = httpClient.newCall(new Request.Builder().url(url).build()).execute();
        return res.body().string();
    }

    public static class FeedException extends Exception {
        public FeedException(String s) {
            super(s);
        }
    }

//    public static LegislationDataXmlParser.Metadata getMetadata(InputStream response) throws IOException, SAXException, ParserConfigurationException {
//        LegislationDataXmlParser p = new LegislationDataXmlParser();
//        XMLReader xr = getAtomFeedReader(p);
//        xr.parse(new InputSource(response));
//        return p.getData();
//    }
}
