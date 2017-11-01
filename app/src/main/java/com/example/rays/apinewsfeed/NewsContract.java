package com.example.rays.apinewsfeed;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rays on 10/27/2017.
 */

public class NewsContract implements BaseColumns {
    private NewsContract(){}

    public final static String CONTENT_AUTHORITY="com.example.rays.apinewsfeed";
    public static final Uri BASE_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH="news";
    public final static Uri CONTENT_URI=Uri.withAppendedPath(BASE_URI,PATH);

    public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH;

    public final static String TABLE_NAME="news";
    public final static String _ID=BaseColumns._ID;
    public final static String COLUMN_TITLE="title";
    public final static String COLUMN_DESCRIPTION="description";
    public final static String COLUMN_URL="url";
    public final static String COLUMN_IMG_URL="img_url";
}
