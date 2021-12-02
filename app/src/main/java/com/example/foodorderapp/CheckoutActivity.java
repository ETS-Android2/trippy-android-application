package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.foodorderapp.Models.OrdersModel;
import com.example.foodorderapp.databinding.ActivityCheckoutBinding;
import com.example.foodorderapp.databinding.ActivityDetailBinding;
import com.google.zxing.WriterException;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class CheckoutActivity extends AppCompatActivity {

    // FORM variables
    ActivityCheckoutBinding binding;
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        int sum = helper.addAllValues();
        TextView DaySalary = (TextView) findViewById(R.id.totalPayAmount);
        DaySalary.setText(sum+"");

        // Form
        CardForm cardForm = findViewById(R.id.card_form);
        Button buy = findViewById(R.id.btnBuy);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CheckoutActivity.this);

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cardForm.isValid()) {
                            alertBuilder = new AlertDialog.Builder(CheckoutActivity.this);
                            alertBuilder.setTitle("Confirm before purchase");
                            alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                                    "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                    "Card CVV: " + cardForm.getCvv() + "\n" +
                                    "Postal code: " + cardForm.getPostalCode() + "\n" +
                                    "Phone number: " + cardForm.getMobileNumber());
                            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Toast.makeText(CheckoutActivity.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                                }
                            });
                            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();

                        } else {
                            Toast.makeText(CheckoutActivity.this, "Please complete the form", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // QR CODE activity

        binding.idBtnUseQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, QrcodeActivity.class));
            }
        });

        // CHECKOUT BUTTON
        /*binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(CheckoutActivity.this)
                        .setTitle("Thank You For Shopping")
                        .setMessage("Your order is confirmed")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });*/
    }

}