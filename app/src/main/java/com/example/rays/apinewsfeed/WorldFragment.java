package com.example.rays.apinewsfeed;

import android.content.Context;
//import android.app.LoaderManager;
import android.content.Intent;
//import android.content.Loader;
import android.support.v4.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rays on 8/27/2017.
 */

public class WorldFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<news_item>> {

    ListView li;TextView emptyStateText;
    String mURL="https://newsapi.org/v1/articles?source=al-jazeera-english&sortBy=latest&apiKey=89aa79ae8ccb4fabbc83f1a572eaa445";
    ArrayList<news_item> newsArray;

    NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        li=(ListView) rootView.findViewById(R.id.list_news);
        emptyStateText=(TextView) rootView.findViewById(R.id.empty_view);
        li.setEmptyView(emptyStateText);

        newsArray=new ArrayList<news_item>();
        adapter=new NewsAdapter(getActivity(),newsArray);
        li.setAdapter(adapter);

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                news_item abc=adapter.getItem(position);
                Intent intent=new Intent(getActivity(),WebViewPage.class);
                intent.putExtra("URL",abc.getSite_url());
                intent.putExtra("URL_IMG",abc.getImage_url());
                intent.putExtra("TITLE",abc.getTitle());
                intent.putExtra("DESCRIPTION"," ");
                startActivity(intent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(1, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            emptyStateText.setText("\n\nNo Internet Connection");
        }


        return rootView;
    }

    @Override
    public Loader<ArrayList<news_item>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getActivity(),mURL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<news_item>> loader, ArrayList<news_item> data) {
        View loadingIndicator = getActivity().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        adapter.clear();
        if(data!=null)
        adapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<news_item>> loader) {
        adapter.clear();
        newsArray.clear();

    }
}
