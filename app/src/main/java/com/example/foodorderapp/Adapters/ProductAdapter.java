package com.example.foodorderapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHolder> {


    // Constructor of arraylist and context
    public ProductAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    ArrayList<MainModel> list;
    Context context;

    // Inflating product activity layout
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false);
        return new viewHolder(view);
    }

    // Connecting view and data from the model for the Database we will create
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final MainModel model = list.get(position);
        holder.foodImage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        // model will get all data which will be used by INTENT
        // Using holder to send data from product to detail activity using Intent

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("name", model.getName());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("desc", model.getDescription());
                // when we pass type 1 we can select product using detailActivity
                intent.putExtra("type", 1);
                context.startActivity(intent);
            }
        });
    }

    // getItemCount to return size of arraylist
    @Override
    public int getItemCount() {
        return list.size();
    }


    // Constructor for holding xml widgets
    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView mainName, price, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.mainName);
            price = itemView.findViewById(R.id.ordersPrice);
            description = itemView.findViewById(R.id.description);
        }
    }
}
