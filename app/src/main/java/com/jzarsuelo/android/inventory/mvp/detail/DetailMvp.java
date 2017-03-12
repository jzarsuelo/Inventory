package com.jzarsuelo.android.inventory.mvp.detail;

import android.net.Uri;

import com.jzarsuelo.android.inventory.pojo.Item;

/**
 * Created by cloudemployee on 27/02/2017.
 */

public interface DetailMvp {

    interface DetailView{
        void initializeView(Item item);
    }

    interface DetailPresenter {
        Item loadData(Uri uri);
        boolean save(Item item);
    }

    interface DetailModel {
        Item loadData(Uri uri);
        boolean save(Item item);
    }
}
