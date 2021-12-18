package AidanAzkafaroDesonJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Account;
import AidanAzkafaroDesonJmartFH.jmart_android.model.Payment;
import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;
import AidanAzkafaroDesonJmartFH.jmart_android.request.PaymentRequest;

/**
 * Class untuk menampilkan history belanja dari account (unfinished)
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class InvoiceHistoryActivityAccount extends AppCompatActivity {

    //inisialisasi instance variable
    private static final Gson gson = new Gson();
    private static ArrayList<Payment> paymentList = new ArrayList<>();
    public static Payment paymentClicked = null;

    private static String status = "";
    private static String productId = "";
    TextView lastHistory;
    ListView listView;
    Account account = LoginActivity.getLoggedAccount();
    Button cancelBtn;
    EditText etId;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history_account);

        listView = findViewById(R.id.list_view_history);
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JsonObject jsonObject = new JsonObject();
                    if(jsonArray != null){
                        paymentList = gson.fromJson(jsonArray.toString(), new TypeToken<Collection<Payment>>(){}.getType());
                        System.out.println("LIST PAYMENT: " +  paymentList);
                        ArrayAdapter<Payment> listViewAdapter = new ArrayAdapter<Payment>(
                                InvoiceHistoryActivityAccount.this,
                                android.R.layout.simple_list_item_1,
                                paymentList
                        );
                        listView.setAdapter(listViewAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                paymentClicked = (Payment) listView.getItemAtPosition(i);
                                Toast.makeText(getApplicationContext(),"See Payment", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InvoiceHistoryActivityAccount.this, InvoiceDetailActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = error -> {
            Toast.makeText(InvoiceHistoryActivityAccount.this, "System error.",Toast.LENGTH_SHORT).show();
        };

        //Mengirimkan request API untuk getPaymentByUser
        RequestQueue queue = Volley.newRequestQueue(InvoiceHistoryActivityAccount.this);
        queue.add(PaymentRequest.getPaymentByUser(account.id,listener,errorListener));

    }
}