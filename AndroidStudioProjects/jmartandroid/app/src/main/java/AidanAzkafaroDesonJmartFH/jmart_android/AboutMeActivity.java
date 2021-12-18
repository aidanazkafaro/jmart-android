package AidanAzkafaroDesonJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Store;
import AidanAzkafaroDesonJmartFH.jmart_android.request.RegisterStoreRequest;
import AidanAzkafaroDesonJmartFH.jmart_android.request.TopUpRequest;

public class AboutMeActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Store storeAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        TextView name = (TextView) findViewById(R.id.accountName);
        name.setText("" + LoginActivity.getLoggedAccount().name);

        TextView email = (TextView) findViewById(R.id.accountEmail);
        email.setText("" + LoginActivity.getLoggedAccount().email);

        TextView balance = (TextView) findViewById(R.id.accountBalance);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        TextInputLayout topUpInput = (TextInputLayout) findViewById(R.id.topUpAmount);
        Button buttonTopUp = (Button) findViewById(R.id.btnTopUp);

        CardView cardRegisterStore = (CardView) findViewById(R.id.cardRegisterStore);

        //input untuk form register
        TextInputLayout nameRegisterStore = (TextInputLayout) findViewById(R.id.storeNameRegister);
        TextInputLayout addressRegisterStore = (TextInputLayout) findViewById(R.id.nameRegister);
        TextInputLayout phoneNumberRegisterStore = (TextInputLayout) findViewById(R.id.phoneRegister);

//        store details
        TextView dataNameCard = (TextView) findViewById(R.id.dataNameTextAbout);
        TextView dataAddressCard = (TextView) findViewById(R.id.dataAddressTextAbout);
        TextView dataPhoneNumber = (TextView) findViewById(R.id.dataPhoneTextAbout);

        //bikin store baru
        Button registerStore  = findViewById(R.id.btnRegisterStore);
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        Button cancelRegister = (Button) findViewById(R.id.btnCancelRegister);

        ConstraintLayout formRegisterStore = findViewById(R.id.formRegisterStore);
        ConstraintLayout accountStoreDetail = findViewById(R.id.accountStoreDetail);

        //if user has a registered store, show the details. Else, show register button.
        if(LoginActivity.getLoggedAccount().store != null){
            registerStore.setVisibility(registerStore.INVISIBLE);
            dataNameCard.setText(LoginActivity.getLoggedAccount().store.name);
            dataAddressCard.setText(LoginActivity.getLoggedAccount().store.address);
            dataPhoneNumber.setText(LoginActivity.getLoggedAccount().store.phoneNumber);
            accountStoreDetail.setVisibility(accountStoreDetail.VISIBLE);

        } else {
            registerStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerStore.setVisibility(view.INVISIBLE);
                    formRegisterStore.setVisibility(view.VISIBLE);
                    cancelRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            registerStore.setVisibility(v.VISIBLE);
                            formRegisterStore.setVisibility(v.INVISIBLE);

                        }
                    });
                }
            });
        }



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(AboutMeActivity.this, "Register Store Successful", Toast.LENGTH_SHORT).show();

                            LoginActivity.loggedAccount.store = gson.fromJson(object.toString(),Store.class);
                            System.out.println("PRINTING STORE: ");
                            System.out.println(LoginActivity.loggedAccount.store);
                            finish();
                            startActivity(getIntent());
                        }catch (JSONException e){
                            Toast.makeText(AboutMeActivity.this, "Register Store Failed", Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }
                    }
                };
                RegisterStoreRequest registerStoreRequest = new RegisterStoreRequest(LoginActivity.getLoggedAccount().id,  nameRegisterStore.getEditText().getText().toString(), addressRegisterStore.getEditText().getText().toString(), phoneNumberRegisterStore.getEditText().getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(registerStoreRequest);
            }
        });

        topUpInput.getEditText().setText("0");

        //button top up clicked action
        buttonTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(AboutMeActivity.this, "Topup Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AboutMeActivity.this, "Topup Failed!", Toast.LENGTH_SHORT).show();
                        }
                        LoginActivity.loggedAccount.balance += Double.parseDouble(topUpInput.getEditText().getText().toString());
                        finish();
                        startActivity(getIntent());
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(topUpInput.getEditText().getText().toString()), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(topUpRequest);
            }
        });
    }
}