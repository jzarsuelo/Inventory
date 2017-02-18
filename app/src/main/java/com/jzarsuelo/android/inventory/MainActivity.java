package com.jzarsuelo.android.inventory;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab;

    private View.OnClickListener mFabClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(i);
        }
    };
    private Animation mScaleFabIn;
    private Animation mScaleFabOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScaleFabIn = AnimationUtils.loadAnimation(this, R.anim.scale_fab_in);
        mScaleFabOut = AnimationUtils.loadAnimation(this, R.anim.scale_fab_out);
        

        mFab = (FloatingActionButton) findViewById(R.id.add_fab);
        mFab.setOnClickListener(mFabClickListner);
    }

    @Override
    protected void onStart() {
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
}
