package com.cloud09.internship.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.model.InvoiceProduct_Class;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductInvoiceAdapter extends RecyclerView.Adapter<ProductInvoiceAdapter.ProductInvoiceHolder> {
    Context context;
    ArrayList<InvoiceProduct_Class>invoiceProductClasses;
    AdapterView.OnItemClickListener onItemClickListener;

    public ProductInvoiceAdapter(Context context, ArrayList<InvoiceProduct_Class> invoiceProductClasses, AdapterView.OnItemClickListener monItemClickListener) {
        this.context = context;
        this.invoiceProductClasses = invoiceProductClasses;
        this.onItemClickListener = monItemClickListener;
    }

    @NonNull
    @Override
    public ProductInvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_invoiceproducts_item, parent, false);

        return new ProductInvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductInvoiceHolder holder, final int position) {
        holder.Pname.setText(invoiceProductClasses.get(position).ProductInvoiceName);
        holder.Pqty.setText(invoiceProductClasses.get(position).ProductInvoiceQuantity);
        holder.Prate.setText(invoiceProductClasses.get(position).ProductInvoiceRate);
        holder.PAmount.setText("RPs"+invoiceProductClasses.get(position).ProductInvoiceAmount);
        holder.Pdesc.setText(invoiceProductClasses.get(position).ProductInvoiceDesc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null,holder.itemView,position,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoiceProductClasses.size();
    }

    public class ProductInvoiceHolder extends RecyclerView.ViewHolder {
        TextView Pname, Pqty, Prate, Pdesc, PAmount;

        public ProductInvoiceHolder(@NonNull View itemView) {
            super(itemView);

            Pname = itemView.findViewById(R.id.tv_IP_Name);
            Pqty = itemView.findViewById(R.id.tv_IP_Qty);
            Prate = itemView.findViewById(R.id.tv_IP_Rate);
            Pdesc = itemView.findViewById(R.id.tv_IP_Desc);
            PAmount = itemView.findViewById(R.id.tv_IP_Amount);
        }
    }
}
