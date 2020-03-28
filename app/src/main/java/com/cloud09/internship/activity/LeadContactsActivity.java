package com.cloud09.internship.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
    String FirstName, LastName, City, Country, StreetAddress, ZipCode, State, ContactTitle, ContactRole, PContact, SContact, PMobile, SMobile, PEmail, SEmail;
    String lead = "17833";
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

                saveContactsData(lead, FirstName, LastName, City, Country, StreetAddress, ZipCode, State, ContactTitle, ContactRole, PContact, SContact, PMobile, SMobile, PEmail, SEmail);
                getContactsData();
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
                Log.i("cvv", String.valueOf(response));
                try {
                    contactsArrayList = new ArrayList<>();
                    for (int c = 0; c < response.length(); c++) {
                        JSONObject contactsObject = response.getJSONObject(c);

                        DisplayContacts contacts = new DisplayContacts();
                        contacts.ContactId = contactsObject.getInt("ID");
                        contacts.LeadID = contactsObject.getInt("LeadID");

                        contacts.FirstName = contactsObject.getString("Contact_FirstName");
                        contacts.LastName = contactsObject.getString("Contact_LastName");
                        contacts.Address= contactsObject.getString("StreetAddress1");
                        contacts.City= contactsObject.getString("City");
                        contacts.Email= contactsObject.getString("Contact_Email");
                        contacts.Country= contactsObject.getString("CountryName");
                        contacts.PostalCode= contactsObject.getString("ZipCode");
                        contacts.Role= contactsObject.getString("Contact_Role");
                        contacts.ContactTitle = contactsObject.getString("Contact_Title");
                        contacts.Phone = contactsObject.getString("Contact_Phone");

                        contactsArrayList.add(contacts);
                        //Log.i("cvv", String.valueOf(contactsArrayList));
                    }
                    //RecyclerView
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LeadContactsActivity.this, LinearLayoutManager.VERTICAL, false);
                    rvDisplayLeadContacts.setLayoutManager(linearLayoutManager);

                    //Adapter
                    contactsAdapter = new ContactsAdapter(LeadContactsActivity.this, contactsArrayList, new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            updateContact();
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

    private void updateContact() {
        Toast.makeText(this, "Update Method Called", Toast.LENGTH_SHORT).show();
    }

    private void saveContactsData(final String LeadId, final String FirstName, final String LastName, final String City,
                                  final String Country, final String StreetAddress, final String ZipCode,
                                  final String State, final String ContactTitle, final String ContactRole,
                                  final String PContact, final String SContact, final String PMobile,
                                  final String SMobile, final String PEmail, final String SEmail) {
        final ProgressDialog pdialog = new ProgressDialog(LeadContactsActivity.this);
        pdialog.setCancelable(false);
        pdialog.setMessage("Order In Process");
        pdialog.show();

        contactsurl = ApiConfiguration.PostContacts_URL + "?LeadID=" + lead;
        Log.i("cvv", contactsurl);

            HashMap<String, String> params = new HashMap<String, String>();
            //FirstName, LastName, City, Country, StreetAddress, ZipCode, State, ContactTitle, ContactRole, PContact, SContact, PMobile, SMobile, PEmail, SEmail;
            try {
                params.put("LeadID", LeadId);

                params.put("Contact_FirstName", FirstName);
                params.put("Contact_LastName", LastName);
                params.put("City", City);
                params.put("CountryName", Country);
                params.put("StreetAddress1", StreetAddress);
                params.put("ZipCode", ZipCode);
                params.put("StateID", State);
                params.put("Contact_Title", ContactTitle);
                params.put("Contact_Role", ContactRole);
                params.put("Contact_Phone", PContact);
                params.put("Contact_Phone2", SContact);
                params.put("Contact_Mobile", PMobile);
                params.put("Contact_Mobile2", SMobile);
                params.put("Contact_Email", PEmail);
                params.put("Contact_Email2", SEmail);
            } catch (Exception e) {
                Log.i("cvv", "ParamsException" + String.valueOf(e));
            }


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest saveContactRequest = new JsonObjectRequest(contactsurl, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pdialog.dismiss();
                try {
//                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                    JSONArray jsonArray = new JSONArray(response);
//                    Log.i("cvv", "JsonObject : "+String.valueOf(jsonObject));
//                    Log.i("cvv", "JsonArray : "+String.valueOf(jsonArray));
//
//                    int status = jsonObject.getInt("status");
//                    String message = jsonObject.getString("Message");
//                    Log.i("cvv", "Message is :" + message);

//
//                    if (status == 0) {
//                        Toast.makeText(LeadContactsActivity.this, message, Toast.LENGTH_LONG).show();
//                        Log.i("cvv", "Message is :" + message);
//                    } else {
//                        Log.i("cvv", "Message is :" + message);
//                        Toast.makeText(LeadContactsActivity.this, message, Toast.LENGTH_LONG).show();
//                    }


                } catch (Exception e) {
                    pdialog.dismiss();
                    e.printStackTrace();
                    Log.i("cvv", "Exception :" + String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pdialog.dismiss();
                Log.i("cvv", "Error : "+String.valueOf(error));
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
        saveContactRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(saveContactRequest);
    }

}
