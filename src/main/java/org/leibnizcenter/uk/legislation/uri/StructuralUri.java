//package org.leibnizcenter.uk.legislation.uri;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Object containing different kinds of URIs for the same object.
// * <p>
// * <em>NOTE</em> This class is meant for non-top level items.
// * Eg. 'ukla/1998/3/enacted/...', not for 'ukla/1998/3'.
// * <p>
// *
// * Created by Maarten on 11-3-2015.
// */
//public class StructuralUri {
//    public static final Pattern uriMatcher = Pattern.compile("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?([^/]*)/([^/]*)/([^/]*)(/.*)?");
//    public final String type;
//    public final String year;
//    public final String number;
//    //   Example: http://www.legislation.gov.uk/ukpga/1985/67-67/data.feed
//    public final String feedURL;
//    public final String focusedUri;
//    public final String relativeUrl;
//    public final String dataXml;
//    public final String idUri;
//
//
//    /**
//     * @param representationUri Legislation.gov.uk URI starting with 'http://www.legislation.gov.uk/'
//     */
//    public StructuralUri(String representationUri) {
//        Matcher m = uriMatcher.matcher(representationUri);
//        if (m.find()) {
//            this.type = m.group(2);
//            this.year = m.group(3);
//            this.number = m.group(4);
//
//            relativeUrl = StructuralUri.getRelativeIdFromURI(representationUri);
//            idUri = "http://www.legislation.gov.uk/" + relativeUrl;
//            dataXml = "http://www.legislation.gov.uk/" + relativeUrl + "/data.xml";
//            feedURL = "http://www.legislation.gov.uk/" + type + "/" + year + "/" + number + "-" + number + "/data.feed";
//            focusedUri = getFullIdFromRelative(type + "/" + year + "/" + number);
//        } else {
//            throw new IllegalArgumentException("Could not parse URI: " + representationUri);
//        }
//    }
//
//    private static String getFullIdFromRelative(String relativeId) {
//        return "http://www.legislation.gov.uk/id/" + relativeId;
//    }
//
//    public static String getRelativeIdFromURI(String uri) {
//        if (!uri.matches("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?.*")) {
//            throw new IllegalArgumentException("URI was not a legislation.gov.uk ID URI: " + uri);
//        }
//        uri = uri.trim();
//        uri = uri.replaceFirst("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?", "");
//        return uri;
//    }
//
//    public static String getTocSnippetUrlFromTocRepresentation(String tocUrl) {
//        if (tocUrl.startsWith("http://www.legislation.gov.uk/")) {
//            String relUrl = tocUrl.replaceFirst("http://www\\.legislation\\.gov\\.uk/", "");
//            relUrl = relUrl.trim();
//            return getHtmlSnippetUrl(relUrl);
//        } else {
//            throw new IllegalArgumentException("TOC representation URL not of the right form: " + tocUrl);
//        }
//    }
//
//
//    public static String getHtmlSnippetUrl(String relativeId) {
//        return "http://legislation.data.gov.uk/" + relativeId + "/data.htm";
//    }
//
//    public static String getRelativeIdFromRepresentation(String repUri) {
//        return repUri.replaceAll("http[s]?://www\\.legislation\\.gov\\.uk/", "");
//    }
//
//    public String createTocUrl() {
//        return "http://www.legislation.gov.uk/" + type + "/" + year + "/" + number + "/contents/data.htm";
//    }
//}
