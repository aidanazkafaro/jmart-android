package AidanAzkafaroDesonJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);


//        if(LoginActivity.getLoggedAccount().store != null){
//            registerStore.;
//        }

        TextView name = (TextView) findViewById(R.id.accountName);
        name.setText("" + LoginActivity.getLoggedAccount().name);

        TextView email = (TextView) findViewById(R.id.accountEmail);
        email.setText("" + LoginActivity.getLoggedAccount().email);

        TextView balance = (TextView) findViewById(R.id.accountBalance);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        TextInputLayout topUpInput = (TextInputLayout) findViewById(R.id.topUpAmount);
        Button buttonTopUp = (Button) findViewById(R.id.btnTopUp);
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        Button registerStore  = findViewById(R.id.btnRegisterStore);
        Button cancelRegister = (Button) findViewById(R.id.btnCancelRegister);

        CardView cardRegisterStore = (CardView) findViewById(R.id.cardRegisterStore);


        TextView dataNameCard = (TextView) findViewById(R.id.dataNameTextAbout);
        TextView dataAddressCard = (TextView) findViewById(R.id.dataAddressTextAbout);
        TextView dataPhoneNumber = (TextView) findViewById(R.id.dataPhoneTextAbout);

        dataNameCard.setText("Aidan");
        dataAddressCard.setText("tangsel");
        dataPhoneNumber.setText("0123456789");

        ConstraintLayout formRegisterStore = findViewById(R.id.constraintLayoutRegisterStore);
        ConstraintLayout accountStoreDetail = findViewById(R.id.constraintLayoutAccountStoreDetail);

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
}