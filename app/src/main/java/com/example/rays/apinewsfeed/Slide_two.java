package com.example.rays.apinewsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

//import android.app.LoaderManager;
//import android.content.Loader;

/**
 * Created by rays on 8/27/2017.
 */

public class Slide_two extends Fragment implements LoaderManager.LoaderCallbacks<news_item> {


    String mURL="https://newsapi.org/v1/articles?source=the-hindu&sortBy=top&apiKey=89aa79ae8ccb4fabbc83f1a572eaa445";
    int position=1;
    RelativeLayout rl;
    TextView textView;
    ImageView imageView;
    news_item newsItem=new news_item("","","","");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.slide_layout, container, false);
        imageView=(ImageView)rootView.findViewById(R.id.slide_image);
        rl=(RelativeLayout) rootView.findViewById(R.id.rel_layout);

        textView=(TextView) rootView.findViewById(R.id.description);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WebViewPage.class);
                intent.putExtra("URL",newsItem.getSite_url());
                intent.putExtra("URL_IMG",newsItem.getImage_url());
                intent.putExtra("TITLE",newsItem.getTitle());
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
            loaderManager.initLoader(2, null, this);
        } else {
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.background);
        }


        return rootView;
    }


    @Override
    public Loader<news_item> onCreateLoader(int id, Bundle args) {
        return new SlideLoader(getContext(),mURL,position);
    }

    @Override
    public void onLoadFinished(Loader<news_item> loader,news_item data) {
        if(data==null)
        {
            rl.setVisibility(View.GONE);
        }
        else
        {
            Picasso.with(getContext()).load(data.getImage_url()).into(imageView);
            textView.setText(data.getTitle());
            newsItem=data;
        }


    }

    @Override
    public void onLoaderReset(Loader<news_item> loader) {

    }
}
