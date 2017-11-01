package com.example.rays.apinewsfeed;

//import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Loads a list of news items by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class SlideLoader extends AsyncTaskLoader<news_item> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;
    private int position;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public SlideLoader(Context context, String url,int pos) {
        super(context);
        mUrl = url;
        position=pos;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public news_item loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        news_item xyz = QueryUtils.newsDataSlide(mUrl,position);

        return xyz;
    }
}
