package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class LoginActivity extends AppCompatActivity {
    
    Button btn_login;
    EditText et_username,et_password;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        getReferences();

        setButtonClicks();

        checkAlreadyLoggedIn();
    }

    //Function for checking User is already Logged in Or not
    private void checkAlreadyLoggedIn() {
        MyPrefs.prefs = getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE);

        String isLoggedIn = MyPrefs.prefs.getString("isLoggedIn","false");
        if(isLoggedIn.equals("true")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    //Function for setting button clicks
    private void setButtonClicks() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                
//                if(checkLen(username) && checkLen(password)){
//                    Toast.makeText(LoginActivity.this, "Please fill your details", Toast.LENGTH_SHORT).show();
//                }else if(checkLen(username)){
//                    Toast.makeText(LoginActivity.this, "Please fill username", Toast.LENGTH_SHORT).show();
//                }else if(checkLen(password)){
//                    Toast.makeText(LoginActivity.this, "Please fill password", Toast.LENGTH_SHORT).show();
//                }else{

//                    if(username.equals("admin")&&password.equals("admin")) {
//                        new validateUser().execute();
//                    }else if(username.equals("user")&&password.equals("user")){
                        new validateUser().execute();
//                    }else{
                       // Toast.makeText(LoginActivity.this, "Check username and password !", Toast.LENGTH_SHORT).show();
                    //}
                //}
            }
        });
    }

    //Async class for in future to fetch login credentials from API
    class validateUser extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Please Wait");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            setSharedPrefs();

            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            dialog.dismiss();
            finish();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }

    //Function for setting Data user is already logged in or not or he's a user or admin
    private void setSharedPrefs() {
        MyPrefs.editor = getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE).edit();
        MyPrefs.editor.putString("isLoggedIn","true");

        if(et_username.getText().toString().equals("")) {
            MyPrefs.editor.putString("isAdmin", "true");
        }
        MyPrefs.editor.apply();
    }


    //Function for getting all References
    private void getReferences() {
        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        dialog = new ProgressDialog(LoginActivity.this);
    }


    //Function for check the input length for validation
    private boolean checkLen(String string) {
        return string.equals("");
    }
}