package AidanAzkafaroDesonJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Account;
import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;

public class ProductDetailActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        TextView productName = (TextView) findViewById(R.id.productDetailName);
        TextView productPrice = (TextView) findViewById(R.id.productDetailPrice);
        TextView productDiscount = (TextView) findViewById(R.id.productDetailDiscount);

        TextView productCategory = (TextView) findViewById(R.id.productDetailCategory);
        TextView productCondition = (TextView) findViewById(R.id.productDetailCondition);
        TextView productWeight = (TextView) findViewById(R.id.productDetailWeight);
        TextView productShipment = (TextView) findViewById(R.id.productDetailShipment);
        Button buttonBeli = (Button) findViewById(R.id.btnBeli);

        productName.setText(ProductFragment.productClicked.name);

        String translatedCondition = "";
        if(ProductFragment.productClicked.conditionUsed){
            translatedCondition = "New";
        } else {
            translatedCondition = "Used";
        }

        productDiscount.setText(String.valueOf(ProductFragment.productClicked.discount) + "%");
        productPrice.setText("Rp. " + String.valueOf(ProductFragment.productClicked.price));
        productCategory.setText(String.valueOf(ProductFragment.productClicked.category));
        productCondition.setText(translatedCondition);
        productWeight.setText(String.valueOf(ProductFragment.productClicked.weight));


        int shipmentPlansValue = ProductFragment.productClicked.shipmentPlans;
        if (shipmentPlansValue == 0){
            productShipment.setText("INSTANT");
        } else if(shipmentPlansValue == 1){
            productShipment.setText("SAME DAY");
        } else if (shipmentPlansValue == 2){
            productShipment.setText("NEXT DAY");
        } else if (shipmentPlansValue == 3){
            productShipment.setText("REGULER");
        } else if (shipmentPlansValue == 4){
            productShipment.setText("KARGO");
        } else {
            productShipment.setText("UNKNOWN");
        }


        buttonBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "BUY NOW", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

    }
}

