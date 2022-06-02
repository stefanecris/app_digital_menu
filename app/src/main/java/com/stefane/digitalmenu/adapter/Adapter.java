package com.stefane.digitalmenu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stefane.digitalmenu.R;
import com.stefane.digitalmenu.model.Item;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Item> itemMenu;

    public Adapter(List<Item> itemMenu) {
        this.itemMenu = itemMenu;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemMenu.get(position);

        holder.viewImage.setImageResource(item.getImage());
        holder.viewName.setText(item.getName());
        holder.viewId.setText("Id: " + item.getId());
        holder.viewPrice.setText("R$ " + String.format("%.2f", item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return this.itemMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView viewImage;
        TextView viewName, viewId, viewPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viewImage = itemView.findViewById(R.id.imageFood);
            viewName = itemView.findViewById(R.id.textName);
            viewId = itemView.findViewById(R.id.textId);
            viewPrice = itemView.findViewById(R.id.textPrice);

        }
    }

}
