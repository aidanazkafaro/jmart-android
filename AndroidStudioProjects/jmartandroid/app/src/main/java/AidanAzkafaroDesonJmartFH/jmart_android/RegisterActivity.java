package AidanAzkafaroDesonJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;


import AidanAzkafaroDesonJmartFH.jmart_android.model.Account;
import AidanAzkafaroDesonJmartFH.jmart_android.request.RegisterRequest;

/**
 * Class untuk melakukan proses register account
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class RegisterActivity extends AppCompatActivity {

    //inisialisasi instance variabel
    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //inisialisasi widget dari layout xml
        TextInputLayout textName = (TextInputLayout) findViewById(R.id.nameRegister);
        TextInputLayout textEmail = (TextInputLayout) findViewById(R.id.registerEmail);
        TextInputLayout textPassword = (TextInputLayout) findViewById(R.id.registerPassword);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        //action saat button Register diklik
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                loggedAccount = gson.fromJson(object.toString(), Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                //mengirimkan request API registerRequest
                RegisterRequest registerRequest = new RegisterRequest(textName.getEditText().getText().toString(), textEmail.getEditText().getText().toString(), textPassword.getEditText().getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }
}