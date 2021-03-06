package org.leibnizcenter.uk.legislation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;


/**
 * <p>
 * Create request to fetch UK law, for example http://www.legislation.gov.uk/search/data.feed?start-year=1850&end-year=1850&type=ukpga&page=1
 * <p>
 * Created by Maarten on 24-1-2015.
 */
public class SearchRequestBuilder {
    private HttpUrl.Builder mBuilder;
    private int mPage = 1;

    public SearchRequestBuilder() {
        mBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("www.legislation.gov.uk")
                .addPathSegment("search")
                .addPathSegment("data.feed");
            //mQueryParams = new HashMap<>();
    }

    public SearchRequestBuilder setTitle(String title) {
        return addQueryParameter("title", title);
    }



    public SearchRequestBuilder addQueryParameter(String key, String value) {
        mBuilder.setQueryParameter(key, value);
        return this;
    }

    public SearchRequestBuilder setType(String type) {
        return addQueryParameter("type", type);
    }

    public SearchRequestBuilder setStartYear(String startYear) {
        return addQueryParameter("start-year", startYear);
    }

    public SearchRequestBuilder setEndYear(String endYear) {
        return addQueryParameter("end-year", endYear);
    }

    public SearchRequestBuilder setStartNumber(String startNumber) {
        return addQueryParameter("start-number", startNumber);
    }

    public SearchRequestBuilder setEndNumber(String endNumber) {
        return addQueryParameter("end-number", endNumber);
    }

    public SearchRequestBuilder setVersion(String version) {
        return addQueryParameter("version", version);
    }

    public SearchRequestBuilder setPage(int page) {
        mPage = page;
        return this;
    }

    public HttpUrl buildUrl() {
        mBuilder.setQueryParameter("page", mPage + "");
        //System.out.println(mBuilder.build());
        return mBuilder.build();
    }

    public Request build() {
        return new Request.Builder()
                .url(buildUrl())
                .build();
    }
}
