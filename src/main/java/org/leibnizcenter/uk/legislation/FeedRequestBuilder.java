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
    private String type = "all";
    private String year=null;
    private String number=null;

    public FeedRequestBuilder() {
        mBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("www.legislation.gov.uk");
    }

    public FeedRequestBuilder addQueryParameter(String key, String value) {
        mBuilder.addQueryParameter(key, value);
        return this;
    }

    public void setYear(int year) {
        setYear(year + "");
    }

    public void setNumber(int number) {
        setNumber(number + "");
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FeedRequestBuilder setPage(int page) {
        mPage = page;
        return this;
    }

    public HttpUrl buildUrl() {
        mBuilder.addPathSegment(type);
        if (year != null) {
            mBuilder.addPathSegment(year);
            if (number != null) {
                mBuilder.addPathSegment(number);
            }
        }
        mBuilder.addPathSegment("data.feed")
                .addQueryParameter("page", mPage + "");
        //System.out.println(mBuilder.build());
        return mBuilder.build();
    }

    public Request build() {
        return new Request.Builder()
                .url(buildUrl())
                .build();
    }
}
