package AidanAzkafaroDesonJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView userMain = (TextView) findViewById(R.id.greetingText);
        userMain.setText("Hello " + LoginActivity.getLoggedAccount().name + "!");
    }
}