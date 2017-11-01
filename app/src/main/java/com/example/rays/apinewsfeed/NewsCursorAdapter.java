package com.example.rays.apinewsfeed;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by rays on 10/27/2017.
 */

public class NewsCursorAdapter extends CursorAdapter {

    public NewsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_bookmark,parent,false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView des=(TextView)view.findViewById(R.id.description);
        ImageView img=(ImageView)view.findViewById(R.id.image_view);
        ImageView del=(ImageView)view.findViewById(R.id.del_bookmark);

        int titleIndex=cursor.getColumnIndex(NewsContract.COLUMN_TITLE);
        int desIndex=cursor.getColumnIndex(NewsContract.COLUMN_DESCRIPTION);
        int imgIndex=cursor.getColumnIndex(NewsContract.COLUMN_IMG_URL);
        final long id=cursor.getLong(cursor.getColumnIndex(NewsContract._ID));

        title.setText(cursor.getString(titleIndex));
        des.setText("Read more...");
        if(cursor.getString(imgIndex)!=null)
        Picasso.with(context).load(cursor.getString(imgIndex)).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(img);
        else
            img.setImageResource(R.drawable.placeholder);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri abc=Uri.withAppendedPath(NewsContract.CONTENT_URI,Long.toString(id));
                int r=context.getContentResolver().delete(abc,null,null);
                Toast.makeText(context,r+ " News item deleted ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
