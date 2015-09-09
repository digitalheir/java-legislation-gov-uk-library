package org.leibnizcenter.uk.legislation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;


/**
 * <p>
 * Create request to fetch UK law, for example http://www.legislation.gov.uk/all/data.feed?page=2
 * <p>
 * Created by Maarten on 24-1-2015.
 */
public class FeedRequestBuilder {
    private HttpUrl.Builder mBuilder;
    private int mPage = 1;
    private String type;

    public FeedRequestBuilder() {
        mBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("www.legislation.gov.uk")
                .addPathSegment("all")
                .addPathSegment("data.feed");
    }

    public FeedRequestBuilder addQueryParameter(String key, String value) {
        mBuilder.addQueryParameter(key, value);
        return this;
    }

    public FeedRequestBuilder setPage(int page) {
        mPage = page;
        return this;
    }

    public HttpUrl buildUrl() {
        mBuilder.addQueryParameter("page", mPage + "");
        //System.out.println(mBuilder.build());
        return mBuilder.build();
    }

    public Request build() {
        return new Request.Builder()
                .url(buildUrl())
                .build();
    }
}
