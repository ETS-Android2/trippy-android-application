package com.example.foodorderapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DBHelper;
import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.OrdersModel;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewHolder>{

    // Array list to contain order items data which will bind with recycler widgets in line 28
    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // onCreateViewHolder, onBindViewHolder, getItemCount
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewHolder(view);
    }

    // We will get data from database then set them to our recycler view with their position
    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.viewHolder holder, int position) {

        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber .setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());

        // Clicking order product will take to Update activity which is detail activity
        // but with update button rest functionality will remain same
        // Update and Delete will based on OrderNumber ( id in db table )
        /*holder.updateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));

                // when we pass type 2 we can update product using detailActivity
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });*/

        // DELETING order by calling deleteOrder method in DbHelper
        // we can use Dialog Box Here

        holder.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Want to remove this order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DBHelper helper = new DBHelper(context);
                                if(helper.deleteOrder(model.getOrderNumber()) > 0){
                                    Toast.makeText(context,"Order Deleted", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

            }
        });


    }

    /*DBHelper helper = new DBHelper(context);
                if(helper.deleteOrder(model.getOrderNumber()) > 0){
                    Toast.makeText(context,"Order Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show();
                }*/

    @Override
    public int getItemCount() {

        return list.size();
    }

    // View Holder : It will hold widgets from order_sample.xml to recycle
    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView soldItemName, price, orderNumber;
        Button  deleteOrder;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.orderImage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.orderPrice);
            //updateOrder = itemView.findViewById(R.id.updateOrder);
            deleteOrder = itemView.findViewById(R.id.deleteOrder);
        }
    }

}
