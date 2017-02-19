package com.jzarsuelo.android.inventory.mvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jzarsuelo.android.inventory.DetailActivity;
import com.jzarsuelo.android.inventory.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.add_fab)
    FloatingActionButton mFab;

    private Animation mScaleFabIn;
    private Animation mScaleFabOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mScaleFabIn = AnimationUtils.loadAnimation(this, R.anim.scale_fab_in);
        mScaleFabOut = AnimationUtils.loadAnimation(this, R.anim.scale_fab_out);
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
            case R.id.action_delete_all:
                // TODO Delete all items
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

    @Override
    public void navigateToDetailView(long id) {

    }

    @Override
    public void deleteAllData() {

    }
}
