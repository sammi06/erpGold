package com.cloud09.internship.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.model.DisplayInvoices;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.InvoicesHolder> {

    ArrayList<DisplayInvoices>dataset;
    AdapterView.OnItemClickListener onItemClickListener;

    public InvoicesAdapter(ArrayList<DisplayInvoices> dataset, AdapterView.OnItemClickListener onItemClickListener) {
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public InvoicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_display_invoices,parent,false);
        return new InvoicesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoicesHolder holder, int position) {
        DisplayInvoices invoices = dataset.get(position);

        holder.CustomerName.setText(invoices.getCustomerName());
        holder.InvoiceNumber.setText(invoices.getInvoiceNumber());
        holder.InvoiceAmount.setText(invoices.getInvoiceAmount());

        holder.InvoiceDueDate.setText(invoices.getDueDate());
        holder.InvoiceDueDays.setText(invoices.getDueDays());
        holder.InvoiceStatus.setText(invoices.getInvoiceStatus());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class InvoicesHolder extends RecyclerView.ViewHolder {

       private TextView CustomerName, InvoiceNumber, InvoiceAmount, InvoiceDueDate, InvoiceDueDays, InvoiceStatus;
        public InvoicesHolder(@NonNull View itemView) {
            super(itemView);

            CustomerName = itemView.findViewById(R.id.tv_name_invoices);
            InvoiceNumber = itemView.findViewById(R.id.tv_invoicenumber_invoices);
            InvoiceAmount = itemView.findViewById(R.id.tv_invoiceamount_invoices);
            InvoiceDueDate = itemView.findViewById(R.id.tv_duedate_invoices);
            InvoiceDueDays = itemView.findViewById(R.id.tv_duedays_invoices);
            InvoiceStatus = itemView.findViewById(R.id.tv_invoicestatus_invoices);

        }
    }
}
