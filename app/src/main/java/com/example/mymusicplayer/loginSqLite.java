package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginSqLite extends AppCompatActivity {

    EditText username,password,repassword;
    Button btnSignUp,btnSignIn;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sq_lite);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")  || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(loginSqLite.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                       Boolean usercheckResult =  myDB.checkusername(user);
                       if(usercheckResult == false)
                       {
                         Boolean regResult =   myDB.insertData(user,pass);
                         if(regResult == true)
                         {
                             Toast.makeText(loginSqLite.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                             startActivity(intent);
                         }
                         else
                         {
                             Toast.makeText(loginSqLite.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                         }
                       }
                       else
                       {
                           Toast.makeText(loginSqLite.this, "User already Exists \n Please Sign In", Toast.LENGTH_SHORT).show();
                       }
                    }
                    else
                    {
                        Toast.makeText(loginSqLite.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}