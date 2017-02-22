package com.jzarsuelo.android.inventory.mvp.home;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jzarsuelo.android.inventory.DetailActivity;
import com.jzarsuelo.android.inventory.R;
import com.jzarsuelo.android.inventory.adapter.InventoryCursorAdapter;
import com.jzarsuelo.android.inventory.data.InventoryContract.InventoryEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity implements IMainView,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.add_fab)
    FloatingActionButton mFab;

    @BindView(R.id.inventory_list)
    ListView mInventoryListView;

    @BindView(R.id.empty_list_view)
    LinearLayout mEmptyListView;

    private Animation mScaleFabIn;
    private Animation mScaleFabOut;

    private IMainPresenter mPresenter;

    private InventoryCursorAdapter mCursorAdapter;

    public static final int INVENTORY_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);

        mScaleFabIn = AnimationUtils.loadAnimation(this, R.anim.scale_fab_in);
        mScaleFabOut = AnimationUtils.loadAnimation(this, R.anim.scale_fab_out);

        mCursorAdapter = new InventoryCursorAdapter(this, null);
        mInventoryListView.setAdapter(mCursorAdapter);
        mInventoryListView.setEmptyView(mEmptyListView);

        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onPostResume() {
        super.onStart();
        mFab.startAnimation(mScaleFabIn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFab.startAnimation(mScaleFabOut);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.action_insert_dummy:
                mPresenter.insertDummyData();
                return true;
            case R.id.action_delete_all:
                mPresenter.deleteAll();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showListView() {

    }

    @OnClick(R.id.add_fab)
    @Override
    public void navigateToDetailView() {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(i);
    }

    @OnItemClick(R.id.inventory_list)
    @Override
    public void navigateToDetailView(int position) {
        long id = mCursorAdapter.getItemId(position);
        Uri uri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

        Intent i = new Intent(this, DetailActivity.class);
        i.setData(uri);

        startActivity(i);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, InventoryEntry.CONTENT_URI, null, null,
                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
