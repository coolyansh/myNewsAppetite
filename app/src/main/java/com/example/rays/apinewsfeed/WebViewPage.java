package com.example.rays.apinewsfeed;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by rays on 8/27/2017.
 */

public class WebViewPage extends AppCompatActivity {

    WebView wbView;
    Intent shareIntent = new Intent();
    String url,url_img,tt,des;
    boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getExtras().getString("URL");
        url_img=getIntent().getExtras().getString("URL_IMG");
        tt= getIntent().getExtras().getString("TITLE");
        des = getIntent().getExtras().getString("DESCRIPTION");
        flag=true;
        if("Bookmark".equals(des))
            flag=false;


        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,url);
        shareIntent.setType("text/plain");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.webviewpage);
        final ProgressDialog pd =ProgressDialog.show(this,"","Loading..... Please Wait");
        pd.setCancelable(true);
        wbView = (WebView) findViewById(R.id.web_view);
        wbView.getSettings().setJavaScriptEnabled(true);

        wbView.setWebChromeClient(new WebChromeClient());

        wbView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd!=null&&pd.isShowing()){
                    pd.dismiss();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

        });
        wbView.loadUrl(url);
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wbView.canGoBack()) {
            wbView.goBack(); // Go to previous page
            return true;
        }
        // Use this as else part
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.share:
                startActivity(shareIntent);
                return true;

            case R.id.bookmark:
                if(flag==true){
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(NewsContract.COLUMN_TITLE,tt);
                    contentValues.put(NewsContract.COLUMN_DESCRIPTION,des);
                    contentValues.put(NewsContract.COLUMN_URL,url);
                    contentValues.put(NewsContract.COLUMN_IMG_URL,url_img);
                    Uri newUri=getContentResolver().insert(NewsContract.CONTENT_URI,contentValues);
                    flag=false;
                    Toast.makeText(this,"News Item added to Bookmarks ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"News Item has already been bookmarked ",Toast.LENGTH_SHORT).show();
                }
                return true;

            case android.R.id.home:
                Intent intent=null;
                if("Bookmark".equals(des))
                    intent=new Intent(this,bookmark_activity.class);
                else
                    intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
