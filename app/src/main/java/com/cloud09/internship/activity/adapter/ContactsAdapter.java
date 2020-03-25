package com.cloud09.internship.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.model.DisplayContacts;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {
    private Context context;
    private ArrayList<DisplayContacts> dataSet;
    private AdapterView.OnClickListener onClickListener;

    public ContactsAdapter(Context context, ArrayList<DisplayContacts> dataSet, AdapterView.OnClickListener onClickListener) {
        this.context = context;
        this.dataSet = dataSet;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_products_item, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
        DisplayContacts contacts = dataSet.get(position);
        String fname = contacts.getFirstName();
        String lname = contacts.getLastName();

        String Name = fname + " " + lname;

        holder.tvName.setText(Name);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvAddress, tvEmail, tvCountry, tvContact, tvRole, tvCity;

        public ContactsHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Contact_Name);
            tvAddress = itemView.findViewById(R.id.tv_Contact_Address);
            tvEmail = itemView.findViewById(R.id.tv_Contact_Email);

            tvCountry = itemView.findViewById(R.id.tv_Contact_Country);
            tvContact = itemView.findViewById(R.id.tv_Contact_Country);
            tvCity = itemView.findViewById(R.id.tv_Contact_City);
            tvRole = itemView.findViewById(R.id.tv_Contact_Role);
        }
    }
}
