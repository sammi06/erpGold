package com.cloud09.internship.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.adapter.ProductInvoiceAdapter;
import com.cloud09.internship.activity.model.InvoiceProduct_Class;

import java.util.ArrayList;

public class NewInvoiceActivity extends AppCompatActivity {
    String pname, pdescription;
    String pqty;
    String punitprice;
    String productamount;
    String ptaxrate;

    double taxRate, productAmount, unitPrice, value, qty;
    int invoiceId;

    private ArrayList<InvoiceProduct_Class> invoiceProductClassArrayList;
    private ProductInvoiceAdapter productInvoiceAdapter;
    private TextView tvAddProduct;
    private TextView tvAddCustomers;
    private RecyclerView rvInvoiceProducts;
    private products_sqlite productsSqlite;
    private TextView tv_productname, tv_productamount;
    private EditText edt_productquantity, edt_productunitprice, edt_producttaxrate, edt_productdescription;
    private TextView tv_ok_btn, tv_cancel_btn, tv_remove_btn;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newinvoice);

        tvAddProduct = findViewById(R.id.tv_add_Products);
        rvInvoiceProducts = findViewById(R.id.rv_Invoice_Products);
        //----------------------------------------
        Bundle updateBundle = getIntent().getExtras();
        if (updateBundle != null){
            //get Data From INTENT
        }


        //------------------------------------
        productsSqlite = new products_sqlite(NewInvoiceActivity.this);
        invoiceProductClassArrayList = productsSqlite.getAllInvoiceProducts();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewInvoiceActivity.this, LinearLayoutManager.VERTICAL, false);
        rvInvoiceProducts.setLayoutManager(linearLayoutManager);
        productInvoiceAdapter = new ProductInvoiceAdapter(NewInvoiceActivity.this, invoiceProductClassArrayList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NewInvoiceActivity.this, "Clicked : " + position, Toast.LENGTH_SHORT).show();

                invoiceId = invoiceProductClassArrayList.get(position).id;
                String name = invoiceProductClassArrayList.get(position).ProductInvoiceName;
                String amount = invoiceProductClassArrayList.get(position).ProductInvoiceAmount;
                String quantity = invoiceProductClassArrayList.get(position).ProductInvoiceQuantity;

                String rate = invoiceProductClassArrayList.get(position).ProductInvoiceRate;
                String description = invoiceProductClassArrayList.get(position).ProductInvoiceDesc;
                String tax = invoiceProductClassArrayList.get(position).ProductInvoiceTax;

                showProductUpdateDialog(invoiceId, name, amount, quantity, rate, tax, description);
            }
        });
        rvInvoiceProducts.setAdapter(productInvoiceAdapter);

        tvAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(NewInvoiceActivity.this, ProductActivity.class);
                startActivity(productIntent);
            }
        });
    }

    public void showProductUpdateDialog(final int invoiceId, final String name, String amount, String quantity, String rate, final String taxrate, String description) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(NewInvoiceActivity.this);

        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.cust_add_productdetailupdate_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);

        //findviewbyid-----------------------
        tv_productname = dialogView.findViewById(R.id.tv_cuAdd_DetailsProductUpdate_heading0);
        edt_productquantity = dialogView.findViewById(R.id.edt_pdu_dialog_qty);
        edt_productunitprice = dialogView.findViewById(R.id.edt_pdu_dialog_unitprice);
        tv_productamount = dialogView.findViewById(R.id.edt_pdu_dialog_amount);
        edt_producttaxrate = dialogView.findViewById(R.id.edt_pdu_dialog_textrate);
        edt_productdescription = dialogView.findViewById(R.id.edt_pdu_dialog_description);

        // = dialogView.findViewById(R.id.);
        //assigning values
        tv_productname.setText(name);
        edt_productquantity.setText(quantity);
        edt_productunitprice.setText(rate);
        tv_productamount.setText(amount);
        edt_producttaxrate.setText(taxrate);
        edt_productdescription.setText(description);


        tv_ok_btn = dialogView.findViewById(R.id.btn_cuD_save);
        tv_cancel_btn = dialogView.findViewById(R.id.btn_cuD_cancel);
        tv_remove_btn = dialogView.findViewById(R.id.btn_cuD_Remove);
        alertDialog = dialogBuilder.create();


        pname = tv_productname.getText().toString();
        pdescription = edt_productdescription.getText().toString();

        pqty = edt_productquantity.getText().toString();
        qty = Double.parseDouble(pqty);
        punitprice = edt_productunitprice.getText().toString();
        unitPrice = Double.parseDouble(punitprice);

        productamount = tv_productamount.getText().toString();
        productAmount = Double.parseDouble(productamount);

        ptaxrate = edt_producttaxrate.getText().toString();
        taxRate = Double.parseDouble(ptaxrate);


        edt_producttaxrate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ptaxrate = edt_producttaxrate.getText().toString();
                    taxRate = Double.parseDouble(ptaxrate);
                    productAmount = Double.parseDouble(productamount);

                    Log.i("cvv", "taxRate is : " + taxRate);
                    Log.i("cvv", "amount : " + productAmount);

                    double res = (productAmount / 100.0f) * taxRate;
                    Log.i("cvv", "%res :" + res);
                    productAmount = productAmount - res;

                    tv_productamount.setText(String.valueOf(productAmount));
                    Log.i("cvv", "%result :" + productAmount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        edt_productunitprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    punitprice = edt_productunitprice.getText().toString();
                    unitPrice = Double.parseDouble(punitprice);
                    productAmount = Double.parseDouble(productamount);
                    pqty = edt_productquantity.getText().toString();
                    qty = Double.parseDouble(pqty);

                    ptaxrate = edt_producttaxrate.getText().toString();
                    taxRate = Double.parseDouble(ptaxrate);

                    productAmount = unitPrice * qty;
                    double res = (productAmount / 100.0f) * taxRate;

                    Log.i("cvv", "%res :" + res);
                    productAmount = productAmount - res;

                    Log.i("cvv", "amount : " + productAmount);
                    tv_productamount.setText(String.valueOf(productAmount));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        edt_productquantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    punitprice = edt_productunitprice.getText().toString();
                    unitPrice = Double.parseDouble(punitprice);
                    productAmount = Double.parseDouble(productamount);
                    pqty = edt_productquantity.getText().toString();
                    qty = Double.parseDouble(pqty);

                    ptaxrate = edt_producttaxrate.getText().toString();
                    taxRate = Double.parseDouble(ptaxrate);

                    productAmount = unitPrice * qty;
                    double res = (productAmount / 100.0f) * taxRate;
                    productAmount = productAmount - res;

                    Log.i("cvv", "amount : " + productAmount);
                    tv_productamount.setText(String.valueOf(productAmount));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        edt_productdescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pdescription = edt_productdescription.getText().toString();
            }
        });

        tv_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cvv", "\n updated-values: \n" + pname + "\n" + pdescription + "\n" + taxRate + "\n" + unitPrice + "\n" + qty + "\n" + productAmount);

                String q = String.valueOf(qty);
                String up = String.valueOf(unitPrice);
                String pa = String.valueOf(productAmount);
                String tr = String.valueOf(taxRate);

                productsSqlite.updateInvoiceProducts(invoiceId,pname,q,pdescription,up,pa,tr);
                productInvoiceAdapter.notifyAll();
                alertDialog.dismiss();
            }
        });
        tv_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        tv_remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsSqlite.deleteInvoiceProducts(invoiceId);
                alertDialog.dismiss();
                productInvoiceAdapter.notifyDataSetChanged();
            }
        });
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewInvoiceActivity.this);
        builder.setTitle("Unsaved Changes")
                .setMessage("Are you shure you want to cancel?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
