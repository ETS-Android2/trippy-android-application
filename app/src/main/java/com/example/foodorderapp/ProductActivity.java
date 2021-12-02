package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderapp.Adapters.ProductAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityProductBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.ritz, "Ritz-Carlton Montréal", "544", "The Ritz-Carlton, Montréal lives up to the brand's reputation by providing sophisticated accommodations and superb service."));
        list.add(new MainModel(R.drawable.fogoisland, "Fogo Island Inn", "513", "Fogo Island Inn isn't your average hotel. The inn features a contemporary, almost futuristic architectural design that seems at odds with the centuries-old fishing village it occupies."));
        list.add(new MainModel(R.drawable.fourseasons, "Four Seasons Hotel", "755", "Located in the heart of Montreal's Golden Square Mile, the Four Seasons Hotel Montreal welcomes guests with its sleek furnishings, gourmet dining and top-notch service."));
        list.add(new MainModel(R.drawable.marriot, "Jw Marriott Parq", "713", "One of two hotels to open at the Parq Vancouver – a business and entertainment complex in downtown Vancouver (next to the BC Place stadium)."));

        ProductAdapter adapter = new ProductAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.floatingHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, ProductActivity.class));
            }
        });

        binding.floatingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, OrderActivity.class));
            }
        });

        binding.floatingCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, CheckoutActivity.class));
            }
        });

        binding.floatingCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, CheckoutActivity.class));
            }
        });

        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



        // GOOGLE Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProductActivity.this);

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProductActivity.this,"Successfully signed out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProductActivity.this, MainActivity.class));
                        finish();
                    }
                });

    }

}


    ///// Top Action Bar Fab icon for Viewing Checkout items
    /*public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.cart:
                startActivity(new Intent(ProductActivity.this, OrderActivity.class));
                break;
            case R.id.home:
                startActivity(new Intent(ProductActivity.this, ProductActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/


//  startActivity(new Intent(ProductActivity.this, OrderActivity.class));