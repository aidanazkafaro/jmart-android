package AidanAzkafaroDesonJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Account;
import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;
import AidanAzkafaroDesonJmartFH.jmart_android.request.CreateProductRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Class untuk membuat produk
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class CreateProductActivity extends AppCompatActivity {

    //instance variables
    private static final Gson gson = new Gson();
    private static Product product = null;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        //inisialisasi widgets dari layout xml
        TextInputLayout nameInput = (TextInputLayout) findViewById(R.id.createProductName);
        TextInputLayout weightInput = (TextInputLayout) findViewById(R.id.createProductWeight);
        TextInputLayout priceInput = (TextInputLayout) findViewById(R.id.createProductPrice);
        TextInputLayout discountInput = (TextInputLayout) findViewById(R.id.createProductDiscount);
        CheckBox newCheck = (CheckBox) findViewById(R.id.checkBoxNew);
        CheckBox usedCheck = (CheckBox) findViewById(R.id.checkBoxUsed);
        Spinner category = (Spinner) findViewById(R.id.productCategory);
        Spinner shipmentPlans = (Spinner) findViewById(R.id.shipmentPlan);
        Button create = (Button) findViewById(R.id.btnCreate);
        Button clear = (Button) findViewById(R.id.btnCancel);

        //action saat checkbox new diklik
        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    usedCheck.setChecked(false);
                }
            }
        });

        //action saat checkbox used diklik
        usedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    newCheck.setChecked(false);
                }
            }
        });

        //action saat button clear diklik
        //mengosongkan input yang ada menjadi default
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.getEditText().setText("");
                weightInput.getEditText().setText("");
                priceInput.getEditText().setText("");
                discountInput.getEditText().setText("");
                usedCheck.setChecked(false);
                newCheck.setChecked(false);
            }
        });

        //action saat button create diklik
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            product = gson.fromJson(object.toString(),Product.class);
                            Toast.makeText(CreateProductActivity.this,"Product Registered",Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            Toast.makeText(CreateProductActivity.this,"Failed to Register",Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }
                        System.out.println(product);
                    }

                };

                //Mengirim request ke API untuk requestProduct
                CreateProductRequest requestProduct = new CreateProductRequest(nameInput.getEditText().getText().toString(),weightInput.getEditText().getText().toString(),String.valueOf(newCheck.isChecked()),priceInput.getEditText().getText().toString(),discountInput.getEditText().getText().toString(),category.getSelectedItem().toString(),convertShipmentPlans(shipmentPlans.getSelectedItem().toString()),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
                requestQueue.add(requestProduct);
            }
        });
    }

    /**
     * Metode untuk konversi shipment plan dari string ke angka (dalam bentuk string juga)
     * @param shipment
     * @return
     */
    protected String convertShipmentPlans(String shipment){
        switch (shipment) {
            case "INSTANT":
                return "0";
            case "SAME DAY":
                return "1";
            case "NEXT DAY":
                return "2";
            case "REGULER":
                return "3";
            default:
                return "4";
        }
    }
}