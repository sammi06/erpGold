package com.cloud09.internship.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cloud09.internship.R;
import com.cloud09.internship.activity.adapter.ProductAdapter;
import com.cloud09.internship.activity.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;

public class ProductActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ArrayList<Product> product_model_class;
    ProductAdapter adapter;
    private FloatingActionButton fabAddProducts;
    private products_sqlite productsSqlite;
    private RecyclerView rvProducts;
    private SwipeRefreshLayout swipeRefreshLayout;


    private TextView tv_showSelectedProductName, tv_productAmount, ok_btn, cancel_btn;
    private EditText edt_productDescription, edt_unitPrice, edt_quantity, edt_salesTax;

    private String intent_unitprice, pdesc, q;
    private String tax = "";
    private Double qty, uprice, Uprice, value, qup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        rvProducts = findViewById(R.id.rv_Products);
        fabAddProducts = findViewById(R.id.fab_add_products);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh_ProductActivity);
        swipeRefreshLayout.setOnRefreshListener(this);
        productsSqlite = new products_sqlite(ProductActivity.this);
        product_model_class = productsSqlite.getAllProducts();
        fetchProducts();

        fabAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductDialog();
            }
        });


    }

    private void fetchProducts() {
        product_model_class = productsSqlite.getAllProducts();
        StringRequest products_stringRequest = new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //------------------------For Data Saving
                //get list from jSON




                /////----------------For Data Display
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false);
                rvProducts.setLayoutManager(linearLayoutManager);

                adapter = new ProductAdapter(ProductActivity.this, product_model_class, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String name = product_model_class.get(position).getProductName();
//                Double rate = product_model_class.get(position).getProductRate();
//                String description = product_model_class.get(position).getProductDesc();
//
//                showAddInvoiceProductDialog(name, rate, description);
                    }
                });
                rvProducts.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }


    private void showAddProductDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProductActivity.this);

        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.cust_add_products_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);

        final TextView addProductName = dialogView.findViewById(R.id.tied_cuAdd_Product_name);
        final TextView addProductRate = dialogView.findViewById(R.id.tied_cuAdd_Product_rate);
        final TextView addProductDescription = dialogView.findViewById(R.id.tied_cuAdd_Product_Description);
        final Spinner spinnerCategories = dialogView.findViewById(R.id.sp_cu_categoryincome);

        final TextView ok_btn = dialogView.findViewById(R.id.btn_cuD_save);
        final TextView cancel_btn = dialogView.findViewById(R.id.btn_cuD_cancel);
        final AlertDialog alertDialog = dialogBuilder.create();

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
                String name = addProductName.getText().toString();
                String rate = addProductRate.getText().toString();

                String description = addProductDescription.getText().toString();
                String categories = spinnerCategories.getSelectedItem().toString();

                if (name.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Name is empty", Toast.LENGTH_SHORT)
                            .show();
                } else if (rate.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Amount is empty", Toast.LENGTH_SHORT)
                            .show();
                } else if (description.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Description is empty", Toast.LENGTH_SHORT)
                            .show();
                } else if (categories.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Category is empty", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    double Rate = Double.parseDouble(rate);
//                    productsSqlite.addProducts(name, categories, description, Rate);
                    alertDialog.dismiss();
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showAddInvoiceProductDialog(final String name, Double UnitPrice, String desc) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProductActivity.this);
        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.cust_add_productdetail_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        //product_model_class >>>(data file)
        tv_showSelectedProductName = dialogView.findViewById(R.id.tv_cuAdd_DetailsProduct_heading0);
        tv_showSelectedProductName.setText(name);

        edt_productDescription = dialogView.findViewById(R.id.edt_pd_dialog_description);
        edt_productDescription.setText(desc);
        pdesc = edt_productDescription.getText().toString();

        edt_unitPrice = dialogView.findViewById(R.id.edt_pd_dialog_unitprice);
        intent_unitprice = String.valueOf(UnitPrice);
        edt_unitPrice.setText(intent_unitprice);

        edt_quantity = dialogView.findViewById(R.id.edt_pd_dialog_qty);
        tv_productAmount = dialogView.findViewById(R.id.edt_pd_dialog_amount);
        uprice = Double.parseDouble(intent_unitprice);

        q = edt_quantity.getText().toString();

        edt_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                edt_quantity.setSelection(edt_quantity.length());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                q = edt_quantity.getText().toString();

                qty = Double.parseDouble(q);
                Log.i("cvv", "qty: " + qty);

                value = uprice * qty;
                tv_productAmount.setText(String.valueOf(value));
                Log.i("cvv", "value: " + value);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_unitPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String up = edt_unitPrice.getText().toString();

                Uprice = Double.valueOf(up);
                Log.i("cvv", "unit price: " + Uprice);
                Log.i("cvv", "qty: " + qty);
                qup = Double.valueOf(q);
                Log.i("cvv", "qup: " + qup);


                value = Uprice * qup;
                tv_productAmount.setText(String.valueOf(value));
                Log.i("cvv", String.valueOf(value));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_salesTax = dialogView.findViewById(R.id.edt_pd_dialog_textrate);
        edt_salesTax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    tax = edt_salesTax.getText().toString();
                    String amount = tv_productAmount.getText().toString();
                    double am = Double.parseDouble(amount);

                    Log.i("cvv", String.valueOf(tax));
                    Log.i("cvv", String.valueOf(am));

                    if (am > 0) {
                        double res = (am / 100.0f) * Double.parseDouble(tax);
                        Log.i("cvv", "% :" + res);

                        double result = am - res;

                        tv_productAmount.setText(String.valueOf(result));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.i("cvv", String.valueOf(ex));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ok_btn = dialogView.findViewById(R.id.btn_cuD_save);
        cancel_btn = dialogView.findViewById(R.id.btn_cuD_cancel);

        final AlertDialog alertDialog = dialogBuilder.create();
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ProductUnitPrice = edt_unitPrice.getText().toString();
                String ProductQuantity = edt_quantity.getText().toString();
                String ProductAmount = tv_productAmount.getText().toString();
                String ProductSalesTax = edt_salesTax.getText().toString();

                productsSqlite.addInvoiceProducts(name, ProductQuantity, pdesc, ProductUnitPrice, ProductAmount, ProductSalesTax);
                alertDialog.dismiss();

                Intent newinvoiceActivity = new Intent(ProductActivity.this, NewInvoiceActivity.class);
                startActivity(newinvoiceActivity);

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        fetchProducts();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}