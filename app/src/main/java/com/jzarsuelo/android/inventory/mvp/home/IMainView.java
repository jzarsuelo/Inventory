package com.jzarsuelo.android.inventory.mvp.home;

/**
 * Created by cloudemployee on 18/02/2017.
 */

public interface IMainView {
    void showEmptyView();
    void showListView();
    void navigateToDetailView();
    void navigateToDetailView(long id);
}
