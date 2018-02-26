package vn.com.mattana.dms.order;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.com.mattana.adapter.CompleteOrderAdapter;
import vn.com.mattana.dms.BaseActivity;
import vn.com.mattana.dms.R;
import vn.com.mattana.model.api.order.ProductInfo;
import vn.com.mattana.util.MRes;
import vn.com.mattana.view.DividerItemDecoration;

public class CompleteOrderActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    CompleteOrderAdapter adapter;

    @BindView(R.id.txtmoney)
    TextView txtMoney;

    @BindView(R.id.agency)
    TextView agency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        ButterKnife.bind(this);
        createToolbar();

        adapter = new CompleteOrderAdapter(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        resetMoneyAll();

        agency.setText(MRes.getInstance().agency.getStore() +" - " + MRes.getInstance().agency.getCode());
    }


    public void orderClick(View view) {

    }


    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }


    public void resetMoneyAll() {
        double price = 0;

        for(ProductInfo order: MRes.getInstance().getProductOrder()) {
            price+= order.getPrice() * order.getQuantityBuy();
        }
        txtMoney.setText(MRes.getInstance().formatMoneyToText(price));

    }

    public void changeQuantity(final int position) {

        final ProductInfo info = MRes.getInstance().getProductOrder().get(position);

        View viewDialog = CompleteOrderActivity.this.getLayoutInflater().inflate(R.layout.dialog_change_quantity_order, null);
        final EditText eQantity = (EditText) viewDialog.findViewById(R.id.euantity);
        eQantity.setText("" + info.getQuantityBuy());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thao tác");
        builder.setMessage("Thay đổi số lượng mua");
        builder.setIcon(R.mipmap.ic_logo);
        builder.setView(viewDialog);
        builder.setNegativeButton("Thôi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("Nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!TextUtils.isEmpty(eQantity.getText().toString())) {
                    try{
                        int quantity  = Integer.parseInt(eQantity.getText().toString());
                        MRes.getInstance().getProductOrder().get(position).setQuantityBuy(quantity);
                        adapter.notifyDataSetChanged();
                    }catch (Exception e) {

                    }
                }else {
                    commons.makeToast(CompleteOrderActivity.this, "Nhập số lượng").show();
                }

            }
        });

        builder.show();
    }

}
