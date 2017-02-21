package com.jzarsuelo.android.inventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jzarsuelo.android.inventory.data.InventoryContract.InventoryEntry;

/**
 * {@link InvetoryCursorAdapter} is an adapter that uses a {@link Cursor}
 * of invetory data as data source. This class creates a layout for the items
 * in the ListView.
 */
public class InvetoryCursorAdapter extends CursorAdapter {

    public InvetoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_inventory, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final int nameIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
        final int priceIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
        final int quantityIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY);

        final String name = cursor.getString(nameIndex);
        final double price = cursor.getDouble(priceIndex);
        final int quantity = cursor.getInt(quantityIndex);

        TextView nameText = (TextView) view.findViewById(R.id.name_text);
        nameText.setText(name);

        TextView priceText = (TextView) view.findViewById(R.id.price_text);
        priceText.setText( String.valueOf(price) );

        TextView quantityText = (TextView) view.findViewById(R.id.price_text);
        quantityText.setText( String.valueOf(quantity) );
    }
}
