package com.jzarsuelo.android.inventory.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.jzarsuelo.android.inventory.data.InventoryContract.InventoryEntry;

/**
 * Created by cloudemployee on 19/02/2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventory.db";

    private static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + "("
            + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT,"
            + InventoryEntry.COLUMN_PRICE + " REAL,"
            + InventoryEntry.COLUMN_QUANTITY + " INTEGER"
            + ");";

    private static final String DROP_INVENTORY_TABLE = "DROP TABLE " + InventoryEntry.TABLE_NAME;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_INVENTORY_TABLE);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "There's a problem in creating " + InventoryEntry.TABLE_NAME + " table.\n"
                    + Log.getStackTraceString(e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_INVENTORY_TABLE);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "There's a problem in dropping " + InventoryEntry.TABLE_NAME + " table. \n"
                    + Log.getStackTraceString(e));
        }

    }
}
