package com.jzarsuelo.android.inventory.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import javax.xml.transform.URIResolver;

import static com.jzarsuelo.android.inventory.data.InventoryContract.*;

/**
 * {@link ContentProvider} for the app
 */
public class InventoryProvider extends ContentProvider {

    private InventoryDbHelper mDbHelper;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int INVENTORY_ALL = 1;

    private static final int INVENTORY_ID = 2;

    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, 
                INVENTORY_ALL);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, 
                InventoryContract.PATH_INVENTORY + "/#", INVENTORY_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        final int code = sUriMatcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case INVENTORY_ALL:
                cursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + code);
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int code = sUriMatcher.match(uri);
        switch (code) {
            case INVENTORY_ALL :
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID :
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + code);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long id = db.insert(InventoryEntry.TABLE_NAME, null, contentValues);

        if (id > -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int code = sUriMatcher.match(uri);
        int affectedRows;
        switch (code) {
            case INVENTORY_ALL:
                affectedRows = delete(selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                affectedRows = delete(selection, selectionArgs);
                break;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + code);
        }

        if (affectedRows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return affectedRows;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int code = sUriMatcher.match(uri);
        int affectedRows = 0;

        switch (code) {
            case INVENTORY_ALL:
                affectedRows = update(contentValues, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry.TABLE_NAME + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                affectedRows = update(contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + code);
        }

        if (affectedRows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return affectedRows;
    }


    /**
     * Delete a record in the DB
     */
    private int delete(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * Return a cursor from the query result
     */
    private Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        return db.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, sortOrder);
    }

    /**
     * Returns the number of the affected rows
     */
    private int update(ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.update(InventoryEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }


}
