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

public class RegisterActivity extends AppCompatActivity {
     private EditText name, password, email, phone;
     private Button btnReg;
     private TextView login;
     private FirebaseAuth firelogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firelogin = FirebaseAuth.getInstance();

        name = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.userPassword);
        email = (EditText)findViewById(R.id.userEmail);
        phone = (EditText)findViewById(R.id.userPhone);
        btnReg = (Button)findViewById(R.id.userRegister);
        login = (TextView)findViewById(R.id.backtoLogin);

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(password.getText().length()<6){
                    password.setError("Minimum 6 characters");
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload to database
                    String user_email = email.getText().toString().trim();
                    String user_pass = password.getText().toString().trim();

                    firelogin.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }else
                                Toast.makeText(RegisterActivity.this,"Registration Fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });


        name.addTextChangedListener(textwatch);
        email.addTextChangedListener(textwatch);
        password.addTextChangedListener(textwatch);
        phone.addTextChangedListener(textwatch);

    }
    private TextWatcher textwatch  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String username= name.getText().toString().trim();
            String useremail = email.getText().toString().trim();
            String userpass = password.getText().toString().trim();
            String userphone = phone.getText().toString().trim();

            btnReg.setEnabled(!username.isEmpty() && !useremail.isEmpty() && !userpass.isEmpty() && !userphone.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private Boolean validate(){
        Boolean result = false;

        String uname = name.getText().toString();
        String upass = password.getText().toString();
        String uemail = email.getText().toString();

        if(uname.isEmpty() || upass.isEmpty() || uemail.isEmpty()){
            Toast.makeText(this, "Please Enter All Your Details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}
