package com.example.calculator.logindesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView register;
    Button login;
    EditText loguser, logpassword;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loguser=(EditText)findViewById(R.id.enterEmail);
        logpassword=(EditText)findViewById(R.id.enterPassword);
        login=(Button)findViewById(R.id.btnlogin);

        firebaseAuth= FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user !=null){
            finish();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(loguser.getText().toString(), logpassword.getText().toString());
            }
        });



    register=(TextView)findViewById(R.id.txtregister);
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        }
    });



    loguser.addTextChangedListener(textwatch);
    logpassword.addTextChangedListener(textwatch);

    }

    private TextWatcher textwatch  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String userInput = loguser.getText().toString().trim();
            String userPass = logpassword.getText().toString().trim();

          login.setEnabled(!userInput.isEmpty() && !userPass.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    private void validate(String loguser, String logpassword){

        firebaseAuth.signInWithEmailAndPassword(loguser,logpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }else
                    Toast.makeText(MainActivity.this,"Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
