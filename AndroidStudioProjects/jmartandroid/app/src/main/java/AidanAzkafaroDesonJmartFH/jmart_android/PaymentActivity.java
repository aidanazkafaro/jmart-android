package AidanAzkafaroDesonJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Payment;

import AidanAzkafaroDesonJmartFH.jmart_android.request.PaymentRequest;


/**
 * Class untuk melakukan pembayaran
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class PaymentActivity extends AppCompatActivity {

    //instance variable
    private static final Gson gson = new Gson();
    private static Payment payment = null;
    int count = 0;

    //variabel total harga sebuah produk termasuk discount
    private double totalPrice = (ProductFragment.productClicked.price*(100.0-ProductFragment.productClicked.discount)/100.0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //inisialisasi widget dari layout xml
        TextInputLayout inputAddress = (TextInputLayout) findViewById(R.id.buyerAddress);
        TextView dataSummaryName = (TextView) findViewById(R.id.summaryName);
        TextView dataSummaryWeight = (TextView) findViewById(R.id.summaryWeight);
        TextView dataSummaryShipment = (TextView) findViewById(R.id.summaryShipmentPlanCost);
        TextView dataSummaryPrice = (TextView) findViewById(R.id.summaryPrice);
        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        Button increase = (Button) findViewById(R.id.btnIncrease);
        Button decrease = (Button) findViewById(R.id.btnDecrease);
        EditText fieldCount = (EditText) findViewById(R.id.productCount);
        TextView dataSummaryCount = (TextView) findViewById(R.id.summaryProductCount);



        fieldCount.setText("1");

        //action saat button increase diklik, increment product count
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                count++;
                fieldCount.setText(String.valueOf(count));
                dataSummaryCount.setText(String.valueOf(count));
            }
        });

        //action saat button decrease diklik, decrement product count (min product count: 1)
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(count > 0){
                    count--;
                }else {
                    count = 0;
                }

                fieldCount.setText(String.valueOf(count));
                dataSummaryCount.setText(String.valueOf(count));
            }
        });

        //menampilkan data summary berdarakan data produk yang diklik
        dataSummaryPrice.setText("Rp " + totalPrice);
        dataSummaryName.setText(ProductFragment.productClicked.name);
        dataSummaryWeight.setText(String.valueOf(ProductFragment.productClicked.weight));
        int shipmentPlansValue = ProductFragment.productClicked.shipmentPlans;
        if (shipmentPlansValue == 0){
            dataSummaryShipment.setText("INSTANT");
        } else if(shipmentPlansValue == 1){
            dataSummaryShipment.setText("SAME DAY");
        } else if (shipmentPlansValue == 2){
            dataSummaryShipment.setText("NEXT DAY");
        } else if (shipmentPlansValue == 3){
            dataSummaryShipment.setText("REGULER");
        } else if (shipmentPlansValue == 4){
            dataSummaryShipment.setText("KARGO");
        } else {
            dataSummaryShipment.setText("UNKNOWN");
        }

        //action saat button submit diklik
        //melakukan pembayaran
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            payment = gson.fromJson(object.toString(), Payment.class);
                            LoginActivity.getLoggedAccount().balance -= ProductFragment.productClicked.price;
                            Toast.makeText(PaymentActivity.this,"Submit Payment",Toast.LENGTH_SHORT).show();

                        }catch (JSONException e){
                            Toast.makeText(PaymentActivity.this,"Failed to Submit",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        System.out.println(payment);
                        finish();
                        startActivity(getIntent());
                    }
                };

                //Mengirimkan request API untuk PaymentRequest
                PaymentRequest paymentRequest = new PaymentRequest(Integer.valueOf(LoginActivity.getLoggedAccount().id), Integer.valueOf(ProductFragment.productClicked.id), Integer.valueOf(fieldCount.getText().toString()) ,inputAddress.getEditText().getText().toString(), ProductFragment.productClicked.shipmentPlans, listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(paymentRequest);
            }
        });
    }
}