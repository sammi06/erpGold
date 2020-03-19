package com.cloud09.internship.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cloud09.internship.R;

public class InvoicesActivity extends AppCompatActivity {
    private RecyclerView rvDisplayInvoices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);

        rvDisplayInvoices= findViewById(R.id.rv_displayInvoices);
    }
}
