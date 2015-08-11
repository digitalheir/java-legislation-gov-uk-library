package org.leibnizcenter.uk.legislation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;


/**
 * Create request to fetch UK law, for example http://www.legislation.gov.uk/all/data.feed?page=2
 * <p/>
 * Created by Maarten on 24-1-2015.
 */
public class UkLawSearchRequestBuilder {
    private HttpUrl.Builder mBuilder;
    private int mPage = 1;

    public UkLawSearchRequestBuilder() {
        mBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("www.legislation.gov.uk")
                .addPathSegment("all")
                .addPathSegment("data.feed");
    }

    public UkLawSearchRequestBuilder setTitle(String title) {
        return addQueryParameter("title", title);
    }

    public UkLawSearchRequestBuilder addQueryParameter(String key, String value) {
        mBuilder.addQueryParameter(key, value);
        return this;
    }

    public UkLawSearchRequestBuilder setType(String type) {
        return addQueryParameter("type", type);
    }

    public UkLawSearchRequestBuilder setStartYear(String startYear) {
        return addQueryParameter("start-year", startYear);
    }

    public UkLawSearchRequestBuilder setEndYear(String endYear) {
        return addQueryParameter("end-year", endYear);
    }

    public UkLawSearchRequestBuilder setStartNumber(String startNumber) {
        return addQueryParameter("start-number", startNumber);
    }

    public UkLawSearchRequestBuilder setEndNumber(String endNumber) {
        return addQueryParameter("end-number", endNumber);
    }

    public UkLawSearchRequestBuilder setVersion(String version) {
        return addQueryParameter("version", version);
    }

    public UkLawSearchRequestBuilder setPage(int page) {
        mPage = page;
        return this;
    }

    public HttpUrl buildUrl() {
        mBuilder.addQueryParameter("page", mPage + "");
        return mBuilder.build();
    }

    public Request build() {
        return new Request.Builder()
                .url(buildUrl())
                .build();
    }
}
