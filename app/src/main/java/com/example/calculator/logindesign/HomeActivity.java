package com.example.calculator.logindesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button search,offer,update,logout;
    private FirebaseAuth firelogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    search=(Button)findViewById(R.id.btnSearch);
    logout=(Button)findViewById(R.id.btnLogout);
    offer=(Button)findViewById(R.id.btnoffer);
    update=(Button)findViewById(R.id.btnEdit);

    firelogout = FirebaseAuth.getInstance();

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this,SearchActivity.class));
        }
    });
    offer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this,OfferActivity.class));
        }
    });
    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this,UpdateActivity.class));
        }
    });
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firelogout.signOut();
            finish();
            Toast.makeText(HomeActivity.this,"Successfully Logout",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        }
    });
    }
}
