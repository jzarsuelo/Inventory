package com.jzarsuelo.android.inventory.mvp.home;

import android.content.ContentValues;
import android.content.Context;

import com.jzarsuelo.android.inventory.InventoryApp;
import com.jzarsuelo.android.inventory.data.InventoryContract.InventoryEntry;

/**
 * Created by cloudemployee on 21/02/2017.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mView;
    private Context mContext;

    public MainPresenter(IMainView view) {
        mView = view;
        mContext = InventoryApp.getContext();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void deleteAll() {
        mContext.getContentResolver()
                .delete(InventoryEntry.CONTENT_URI, null, null);
    }

    @Override
    public void insertDummyData() {
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Box");
        values.put(InventoryEntry.COLUMN_PRICE, "3");
        values.put(InventoryEntry.COLUMN_QUANTITY, 10);

        mContext.getContentResolver()
                .insert(InventoryEntry.CONTENT_URI, values);
    }
}
