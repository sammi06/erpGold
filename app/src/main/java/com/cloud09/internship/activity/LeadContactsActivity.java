package com.cloud09.internship.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cloud09.internship.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LeadContactsActivity extends AppCompatActivity {
    private RecyclerView rvDisplayLeadContacts;
    private FloatingActionButton fabAddLeadContacts;
    private AlertDialog alertDialog;

    //---------ADD--CONTACTS--Dialog-------------------
    private String FirstName, LastName, City, Country, StreetAddress, ZipCode, State, ContactTitle, ContactRole, PContact, SContact, PMobile, SMobile, PEmail, SEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_contacts);
        rvDisplayLeadContacts = findViewById(R.id.rv_displayLeadContacts);
        fabAddLeadContacts = findViewById(R.id.fAB_addLeadContacts);


        fabAddLeadContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLeadContactsDialog();
            }
        });

        getContactsData();
    }

    private void getContactsData() {

    }

    public void showAddLeadContactsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LeadContactsActivity.this);

        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.cust_add_leadcontacts_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        TextView tvBtnCancel = dialogView.findViewById(R.id.btn_cuD_cancel);
        TextView tvBtnSave = dialogView.findViewById(R.id.btn_cuD_save);
        //-----------------------------------------
        final EditText edtFirstName = dialogView.findViewById(R.id.tiedt_cuAdd_Firstname);
        final EditText edtLastName = dialogView.findViewById(R.id.tiedt_cuAdd_Lastname);
        final EditText edtStreetAddress = dialogView.findViewById(R.id.tiedt_cuAdd_StreetAddress);
        final EditText edtCity = dialogView.findViewById(R.id.tiedt_cuAdd_City);
        final EditText edtCityZipCode = dialogView.findViewById(R.id.tiedt_cuAdd_ZipCode);
            final Spinner spCountries = dialogView.findViewById(R.id.sp_cuAdd_Countries);
        final EditText edtState = dialogView.findViewById(R.id.tiedt_cuAdd_State);
        final EditText edtContactTitle = dialogView.findViewById(R.id.tiedt_cuAdd_ContactTitle);
            final Spinner spContactRole = dialogView.findViewById(R.id.sp_cuAdd_ContactRole);
        final EditText edtPContact = dialogView.findViewById(R.id.tiedt_cuAdd_PrimaryContact);
        final EditText edtSContact = dialogView.findViewById(R.id.tiedt_cuAdd_SecondaryPhone);
        final EditText edtPMobile = dialogView.findViewById(R.id.tiedt_cuAdd_PrimaryMobile);
        final EditText edtSMobile = dialogView.findViewById(R.id.tiedt_cuAdd_SecondaryMobile);
        final EditText edtPEmail = dialogView.findViewById(R.id.tiedt_cuAdd_PrimaryEmail);
        final EditText edtSEmail = dialogView.findViewById(R.id.tiedt_cuAdd_SecondaryEmail);

        alertDialog = dialogBuilder.create();
        tvBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName = edtFirstName.getText().toString();
                LastName = edtLastName.getText().toString();
                City = edtCity.getText().toString();
                StreetAddress = edtStreetAddress.getText().toString();
                Country = spCountries.getSelectedItem().toString();
                ZipCode = edtCityZipCode.getText().toString();
                State = edtState.getText().toString();
                ContactTitle = edtContactTitle.getText().toString();
                ContactRole = spContactRole.getSelectedItem().toString();
                PContact = edtPContact.getText().toString();
                SContact = edtSContact.getText().toString();
                PMobile = edtPMobile.getText().toString();
                SMobile = edtSMobile.getText().toString();
                PEmail = edtPEmail.getText().toString();
                SEmail = edtSEmail.getText().toString();


                alertDialog.dismiss();
            }
        });
        tvBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

}
