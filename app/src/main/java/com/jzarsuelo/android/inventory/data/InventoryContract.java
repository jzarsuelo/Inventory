package com.jzarsuelo.android.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines the schema for the application
 */
public final class InventoryContract {


    private InventoryContract() { }

    public static final String CONTENT_AUTHORITY = "com.jzarsuelo.android.inventory";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY = InventoryEntry.TABLE_NAME;

    /**
     * Defines the table structure of inventory table
     */
    public static final class InventoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        private InventoryEntry() { }

        public static final String TABLE_NAME = "inventory";

        public static final String COLUMN_PRODUCT_NAME = "product_name";

        public static final String COLUMN_QUANTITY = "quantity";

        public static final String COLUMN_PRICE = "price";
    }
}
