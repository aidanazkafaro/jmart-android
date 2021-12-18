package AidanAzkafaroDesonJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Account;
import AidanAzkafaroDesonJmartFH.jmart_android.request.LoginRequest;

/**
 * Class untuk Login Session
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class LoginActivity extends AppCompatActivity {

    //inisialisasi instance variable
    private static final Gson gson = new Gson();
    public static Account loggedAccount = null;
    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inisialisasi field yang ada di xml
        TextInputLayout textEmail = (TextInputLayout) findViewById(R.id.loginEmail);
        TextInputLayout textPassword = (TextInputLayout) findViewById(R.id.loginPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        //action saat button login di klik
        buttonLogin.setOnClickListener(v -> {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if(object != null){
                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            };

            //membuat request dengan isi object dari LoginRequest
            LoginRequest loginRequest = new LoginRequest(textEmail.getEditText().getText().toString(), textPassword.getEditText().getText().toString(), listener, null);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(loginRequest);
        });

        TextView register = findViewById(R.id.registerNow);

        //action saat button register diklik
        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}