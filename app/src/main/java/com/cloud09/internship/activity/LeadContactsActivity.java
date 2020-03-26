package com.cloud09.internship.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cloud09.internship.R;
import com.cloud09.internship.activity.adapter.ContactsAdapter;
import com.cloud09.internship.activity.model.DisplayContacts;
import com.cloud09.internship.activity.serverApi.ApiConfiguration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeadContactsActivity extends AppCompatActivity {
    private RecyclerView rvDisplayLeadContacts;
    private FloatingActionButton fabAddLeadContacts;
    private AlertDialog alertDialog;
    private ContactsAdapter contactsAdapter;
    //----------Get--Contacts------------------------
    String contactsurl;

    //---------ADD--CONTACTS--Dialog-------------------
    private String FirstName, LastName, City, Country, StreetAddress, ZipCode, State, ContactTitle, ContactRole, PContact, SContact, PMobile, SMobile, PEmail, SEmail;
    int lead = 17833;
    private ArrayList<DisplayContacts> contactsArrayList;

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

    private void getContactsData() {
        contactsurl = ApiConfiguration.GetContacts_URL + "?LeadID=" + lead;
        Log.i("cvv", contactsurl);
        JsonArrayRequest getContactsRequest = new JsonArrayRequest(contactsurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    contactsArrayList = new ArrayList<>();
                    for (int c = 0; c < response.length(); c++) {
                        JSONObject contactsObject = response.getJSONObject(c);

                        DisplayContacts contacts = new DisplayContacts();
                        contacts.ContactId = contactsObject.getInt("ID");
                        contacts.LeadID = contactsObject.getInt("LeadID");

                        contactsArrayList.add(contacts);
                        Log.i("cvv", String.valueOf(contactsArrayList));
                    }
                    //RecyclerView
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LeadContactsActivity.this, LinearLayoutManager.VERTICAL, false);
                    rvDisplayLeadContacts.setLayoutManager(linearLayoutManager);

                    //Adapter
                    contactsAdapter = new ContactsAdapter(LeadContactsActivity.this, contactsArrayList, new AdapterView.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    rvDisplayLeadContacts.setAdapter(contactsAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("cvv", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("cvv", String.valueOf(error));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Token", "8c50bf69-e974-4384-9850-215782ff2ad1");
                headers.put("Username", "abdeveloper00@gmail.com");

                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        getContactsRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getContactsRequest);
    }

    private void saveContactsData() {
        JsonArrayRequest saveContactRequest = new JsonArrayRequest(ApiConfiguration.GetContacts_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Token", "8c50bf69-e974-4384-9850-215782ff2ad1");
                headers.put("Username", "abdeveloper00@gmail.com");
                return headers;
            }
        };
    }

}
