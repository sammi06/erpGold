package com.cloud09.internship.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.model.DisplayContacts;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {
    private Context context;
    private ArrayList<DisplayContacts> dataSet;
    AdapterView.OnItemClickListener onItemClickListener;

    public ContactsAdapter(Context context, ArrayList<DisplayContacts> dataSet, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataSet = dataSet;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_contact_items, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsHolder holder, final int position) {
        DisplayContacts contacts = dataSet.get(position);
        String fname = contacts.getFirstName();
        String lname = contacts.getLastName();
        String Name = fname + " " + lname;
        holder.tvName.setText(Name);
        holder.tvLeadId.setText("" + contacts.getLeadID());

        holder.tvContact.setText(contacts.getPhone());
        holder.tvEmail.setText(contacts.getEmail());
        holder.tvAddress.setText(contacts.getAddress());
        holder.tvCountry.setText(contacts.getCountry());
        String city = contacts.getCity();
        String postalcode = contacts.getPostalCode();
        holder.tvRole.setText(contacts.getRole());

        String City = city + "  "+ postalcode;
        holder.tvCity.setText(City);

        holder.ivDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null, holder.ivEditContact, holder.getAdapterPosition(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ContactsHolder extends RecyclerView.ViewHolder {
        private TextView tvLeadId, tvName, tvAddress, tvEmail, tvCountry, tvContact, tvRole, tvCity;
        private ImageView ivEditContact, ivDeleteContact;

        ContactsHolder(@NonNull View itemView) {
            super(itemView);
            tvLeadId = itemView.findViewById(R.id.tv_Contact_LeadId);
            tvName = itemView.findViewById(R.id.tv_Contact_Name);
            tvAddress = itemView.findViewById(R.id.tv_Contact_Address);
            tvEmail = itemView.findViewById(R.id.tv_Contact_Email);

            tvCountry = itemView.findViewById(R.id.tv_Contact_Country);
            tvContact = itemView.findViewById(R.id.tv_Contact_Country);
            tvCity = itemView.findViewById(R.id.tv_Contact_City);
            tvRole = itemView.findViewById(R.id.tv_Contact_Role);

            ivEditContact = itemView.findViewById(R.id.iv_EditContacts);
            ivDeleteContact = itemView.findViewById(R.id.iv_DeleteContacts);
        }
    }
}
