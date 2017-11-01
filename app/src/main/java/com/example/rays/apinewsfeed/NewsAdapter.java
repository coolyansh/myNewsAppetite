package com.example.rays.apinewsfeed;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<news_item>{

    public NewsAdapter(@NonNull Context context, ArrayList<news_item> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        news_item news=getItem(position);

        TextView tt=(TextView) view.findViewById(R.id.title);
        tt.setText(news.getTitle());

        TextView dd=(TextView)view.findViewById(R.id.description);
        dd.setText(news.getDescription());

        ImageView i=(ImageView)view.findViewById(R.id.image_view);

        ConnectivityManager connMgr = (ConnectivityManager)
                getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            Picasso picasso=Picasso.with(this.getContext());
            //picasso.setIndicatorsEnabled(true);
            picasso.load(news.getImage_url()).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).resize(70,70).into(i);
        }



        return view;
    }
}
