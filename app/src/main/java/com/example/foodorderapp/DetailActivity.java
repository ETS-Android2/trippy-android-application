package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private Button mPickDateButton;
    private TextView mShowSelectedDateText;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // DbHelper object
        final DBHelper helper = new DBHelper(this);

        // If detailAct gets type 1 from OrderActivity, then use this code
        //if(getIntent().getIntExtra("type",0) == 1) {

            // Getting data from product adapter using Intent
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            // Binding data to the Detail activity widgets
            binding.detailImage.setImageResource(image);
            binding.initPriceLbl.setText(String.format("%d", price));
            binding.nameLbl.setText(name);
            binding.detailDescription.setText(description);


////////////////////////////////////////////////////////////////////////////////////////////////
            // On clicking button insertOrder is called in DBHelper
            // which will store data in database


            binding.insertBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (binding.priceLbl.getText() != null && binding.priceLbl.getText().equals("")) {
                        Toast.makeText(DetailActivity.this, "Make Sure to select Inputs", Toast.LENGTH_SHORT).show();

                    } else {

                        boolean isInserted = helper.insertOrder(
                                //binding.nameBox.getText().toString(),
                                //binding.emailBox.getText().toString(),
                                //binding.phoneBox.getText().toString(),
                                Integer.parseInt(binding.priceLbl.getText().toString()),
                                name,
                                description,
                                image
                                //Integer.parseInt(binding.quantity.getText().toString())
                        );

                        // Alert Dialog for Data inserted
                        new AlertDialog.Builder(DetailActivity.this)
                                .setTitle("Add Order")
                                .setMessage("Confirm Your Order")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isInserted)
                                            Toast.makeText(DetailActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).show();
                    }
                }
            });


        binding.searchHotelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MapsActivity.class));
            }

        });

        // DATE PICKER

            mPickDateButton = findViewById(R.id.pick_date_button);
            mShowSelectedDateText = findViewById(R.id.show_selected_date);

            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
            final MaterialDatePicker materialDatePicker = materialDateBuilder.build();


            mPickDateButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                        }
                    });

            // now handle the positive button click from the
            // material design date picker
            materialDatePicker.addOnPositiveButtonClickListener(
                    new MaterialPickerOnPositiveButtonClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onPositiveButtonClick(Object selection) {

                            //getHeaderText will return selected date preview from the dialog
                            mShowSelectedDateText.setText(materialDatePicker.getHeaderText());
                        }
                    });
        }


            // DATE PICKER

            /*mPickDateButton = findViewById(R.id.pick_date_button);
            mShowSelectedDateText = findViewById(R.id.show_selected_date);

            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
            final MaterialDatePicker materialDatePicker = materialDateBuilder.build();


            mPickDateButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                        }
                    });

            // now handle the positive button click from the
            // material design date picker
            materialDatePicker.addOnPositiveButtonClickListener(
                    new MaterialPickerOnPositiveButtonClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onPositiveButtonClick(Object selection) {

                            //getHeaderText will return selected date preview from the dialog
                            mShowSelectedDateText.setText(materialDatePicker.getHeaderText());
                        }
                    });*/

        //}

        // Intent pass id and Cursor will get data related to that id
        /*else{
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(8);

            binding.detailImage.setImageResource(image);
            binding.initPriceLbl.setText(String.format("%d", cursor.getInt(4)));
            binding.nameLbl.setText(cursor.getString(5));
            binding.detailDescription.setText(cursor.getString(7));
            //binding.nameBox.setText(cursor.getString(1));
           // binding.emailBox.setText(cursor.getString(2));
           // binding.phoneBox.setText(cursor.getString(3));

            // Update Button
            /*binding.insertBtn.setText("UPDATE YOUR ORDER");
            binding.insertBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                           // binding.nameBox.getText().toString(),
                          //  binding.emailBox.getText().toString(),
                           // binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.initPriceLbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.nameLbl.getText().toString(),
                            1,
                            id
                    );
                    new AlertDialog.Builder(DetailActivity.this)
                            .setTitle("Update Order")
                            .setMessage("Want to Update your Order")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(isUpdated)
                                        Toast.makeText(DetailActivity.this,"Data Updated", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(DetailActivity.this,"Error", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();
                }
            });
        }*/



    // Types of Rooms
    public void checkDeluxe(View view) {
        binding.roomDescTxt.setText(getString(R.string.deluxe));
    }

    public void checkSuite(View view) {
        binding.roomDescTxt.setText(getString(R.string.suite));
    }

    public void checkStandard(View view) {
        binding.roomDescTxt.setText(getString(R.string.standard));
    }

    public void checkAccessible(View view) {
        binding.roomDescTxt.setText(getString(R.string.accessible));
    }

    public void checkConnect(View view) {
        binding.roomDescTxt.setText(getString(R.string.connect));
    }

    public void jointConnect(View view) {
        binding.roomDescTxt.setText(getString(R.string.joint));
    }

    // Google Map


     int quantity = 1;

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity>1){
            quantity = quantity - 1;
            display(quantity);
        }
    }

    // TOTAL PRICE
    public void totalPrice(View view) {
        int initialPrice = Integer.parseInt(binding.initPriceLbl.getText().toString());
        int price = quantity*(initialPrice);
        displayPrice(price);
    }

    //  This method displays the given quantity value
    private void display(int quantityNum) {
        binding.quantity.setText("" + quantityNum);
    }

    // This method displays the total price
    private void displayPrice(int total) {
        binding.priceLbl.setText(""+ total);
    }
}