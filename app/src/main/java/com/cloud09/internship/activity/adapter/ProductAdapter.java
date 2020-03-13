package com.cloud09.internship.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.cloud09.internship.R;
import com.cloud09.internship.activity.model.Product;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    Context context;
    ArrayList<Product> product_ArrayList;
    AdapterView.OnItemClickListener onItemClickListener;

    public ProductAdapter(Context context, ArrayList<Product> product_ArrayList, AdapterView.OnItemClickListener monItemClickListener) {
        this.context = context;
        this.product_ArrayList = product_ArrayList;
        this.onItemClickListener = monItemClickListener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_products_item, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductHolder holder, final int position) {
        holder.tvPName.setText(product_ArrayList.get(position).getProductName());
        holder.tvPRate.setText("" + product_ArrayList.get(position).getProductRate());
        holder.tvPDesc.setText(product_ArrayList.get(position).getProductDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null, holder.itemView, position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_ArrayList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        private TextView tvPName, tvPDesc, tvPRate;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            tvPName = itemView.findViewById(R.id.tv_PS_Name);
            tvPDesc = itemView.findViewById(R.id.tv_PS_Desc);
            tvPRate = itemView.findViewById(R.id.tv_PS_Rate);
        }
    }
}
