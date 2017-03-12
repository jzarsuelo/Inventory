package com.jzarsuelo.android.inventory.mvp.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.jzarsuelo.android.inventory.R;
import com.jzarsuelo.android.inventory.pojo.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jzarsuelo.android.inventory.mvp.detail.DetailMvp.DetailView;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.name_text)
    TextView mNameText;

    @BindView(R.id.price_text)
    TextView mPriceText;

    @BindView(R.id.quantity_text)
    TextView mQuantityText;

    @BindView(R.id.sales_text)
    TextView mSalesText;

    @BindView(R.id.transaction_qty_edit)
    EditText mTransactionQtyEdit;

    @BindView(R.id.save_button)
    Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void initializeView(Item item) {

    }
}
