package org.leibnizcenter.uk.legislation.uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object containing different kinds of URIs for the same law.
 * <p>
 * <em>NOTE</em> This class is meant only for top level items, corresponding to the work of a law.
 * Eg. 'ukla/1998/3', not for 'ukla/1998/3/enacted/...'.
 * <p>
 * <p>
 * Created by Maarten on 11-3-2015.
 */
public class TopLevelUri {
    public static final Pattern uriMatcher = Pattern.compile("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?([^/]*)/([^/]*)/([^/]*)(/.*)?");
    //   Example: http://www.legislation.gov.uk/ukpga/1985/67-67/data.feed
    public final String feedURL;
    //   Example: http://www.legislation.gov.uk/id/ukpga/1985/67
    public final String fullUri;
    public final String relativeUrl;
    public final String dataXml;
    public final String idUri;
    public final String type;
    public final int year;
    public final int number;


    /**
     * This constructor makes assumptions that are not always true, namely that all representation URIs end with @code{/{type}/{year}/{number}}.
     *
     * @see #TopLevelUri(String, int, int)
     **/
    @Deprecated
    public TopLevelUri(String representationUri) {
        Matcher m = uriMatcher.matcher(representationUri);
        if (m.find()) {
            type = m.group(2);
            year = Integer.parseInt(m.group(3));
            number = Integer.parseInt(m.group(4));

            relativeUrl = TopLevelUri.getRelativeIdFromURI(representationUri);
            idUri = "http://www.legislation.gov.uk/" + relativeUrl;
            dataXml = "http://www.legislation.gov.uk/" + relativeUrl + "/data.xml";
            feedURL = "http://www.legislation.gov.uk/" + type + "/" + year + "/" + number + "-" + number + "/data.feed";
            fullUri = getFullIdFromRelative(type + "/" + year + "/" + number);
        } else {
            throw new IllegalArgumentException("Could not parse URI: " + representationUri);
        }
    }

    /**
     * @param representationUri Legislation.gov.uk URI starting with 'http://www.legislation.gov.uk/'
     * @param year              as supplied in @code{ukm:Year}
     * @param number            as supplied in @code{ukm:Number}
     */
    public TopLevelUri(String representationUri, int year, int number) {
        this.year=year;
        this.number=number;
        Matcher m = uriMatcher.matcher(representationUri);
        if (m.find()) {
            type = m.group(2);

            relativeUrl = TopLevelUri.getRelativeIdFromURI(representationUri);
            idUri = "http://www.legislation.gov.uk/" + relativeUrl;
            dataXml = "http://www.legislation.gov.uk/" + relativeUrl + "/data.xml";
            feedURL = "http://www.legislation.gov.uk/" + type + "/" + year + "/" + number + "-" + number + "/data.feed";
            fullUri = getFullIdFromRelative(type + "/" + year + "/" + number);
        } else {
            throw new IllegalArgumentException("Could not parse URI: " + representationUri);
        }
    }

    public static String getFullIdFromRelative(String relativeId) {
        return "http://www.legislation.gov.uk/id/" + relativeId;
    }

    public static String getRelativeIdFromURI(String uri) {
        if (!uri.matches("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?.*")) {
            throw new IllegalArgumentException("URI was not a legislation.gov.uk ID URI: " + uri);
        }
        uri = uri.trim();
        uri = uri.replaceFirst("http[s]?://www\\.legislation\\.gov\\.uk/(id/)?", "");
        return uri;
    }
}
