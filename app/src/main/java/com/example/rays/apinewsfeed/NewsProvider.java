package com.example.rays.apinewsfeed;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by rays on 10/27/2017.
 */

public class NewsProvider extends ContentProvider {

    public static final int NEWS=100;
    public static final int NEWS_ID=101;
    private NewsDbHelper mDbHelper;

    private static final UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(NewsContract.CONTENT_AUTHORITY,NewsContract.PATH,NEWS);
        uriMatcher.addURI(NewsContract.CONTENT_AUTHORITY,NewsContract.PATH+"/#",NEWS_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper=new NewsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match=uriMatcher.match(uri);
        switch(match)
        {
            case NEWS:
                cursor=database.query(NewsContract.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case NEWS_ID:
                selection=NewsContract._ID+"=?";
                selectionArgs=new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor=database.query(NewsContract.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match=uriMatcher.match(uri);
        switch(match)
        {
            case NEWS:
                return NewsContract.CONTENT_LIST_TYPE;
            case NEWS_ID:
                return NewsContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        int match=uriMatcher.match(uri);
        switch(match){
            case NEWS:
                long id=database.insert(NewsContract.TABLE_NAME,null,values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri,id);
            default:
                throw new IllegalArgumentException("Unable to insert for URI "+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        int match=uriMatcher.match(uri);
        int rows;
        switch(match)
        {
            case NEWS:
                rows=database.delete(NewsContract.TABLE_NAME,selection,selectionArgs);
                break;
            case NEWS_ID:
                selection=NewsContract._ID+"=?";
                selectionArgs=new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rows=database.delete(NewsContract.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unable to delete for URI "+uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
