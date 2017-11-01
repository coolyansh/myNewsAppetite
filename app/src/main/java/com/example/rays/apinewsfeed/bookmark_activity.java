package com.example.rays.apinewsfeed;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class bookmark_activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    NewsCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_activity);

        AdView adView=(AdView)findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView=(ListView)findViewById(R.id.bookmark_list);
        listView.setEmptyView(findViewById(R.id.empty_image));
        mAdapter=new NewsCursorAdapter(this,null);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(bookmark_activity.this,WebViewPage.class);
                Cursor cursor=(Cursor)mAdapter.getItem(position);
                String url=cursor.getString(cursor.getColumnIndex(NewsContract.COLUMN_URL));
                String url_img=cursor.getString(cursor.getColumnIndex(NewsContract.COLUMN_IMG_URL));
                String title=cursor.getString(cursor.getColumnIndex(NewsContract.COLUMN_TITLE));
                String desc="Bookmark";

                intent.putExtra("URL",url);
                intent.putExtra("URL_IMG",url_img);
                intent.putExtra("TITLE",title);
                intent.putExtra("DESCRIPTION",desc);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(2,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection={NewsContract._ID,NewsContract.COLUMN_TITLE,NewsContract.COLUMN_DESCRIPTION,NewsContract.COLUMN_IMG_URL,NewsContract.COLUMN_URL};
        return new CursorLoader(this,NewsContract.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bookmark_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                int r=getContentResolver().delete(NewsContract.CONTENT_URI,null,null);
                if(r!=0)
                Toast.makeText(this, r+" News Items Deleted", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(this,"Nothing to delete ",Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);

            default:
                return true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
