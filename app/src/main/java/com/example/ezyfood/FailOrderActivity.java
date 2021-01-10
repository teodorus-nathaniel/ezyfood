package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FailOrderActivity extends AppCompatActivity {

    Button btnBackCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_order);

        ArrayList<String> messages = getIntent().getStringArrayListExtra("messages");
        Log.d("DATA_TEST", "onCreate: " + messages);
        if(messages != null) {
            String finalMessage = "";
            for (String message : messages) {
                finalMessage += message + "\n";
            }

            TextView tvMessage = findViewById(R.id.tv_message);
            tvMessage.setText(finalMessage);
        }

        btnBackCart = findViewById(R.id.btn_back_cart);
        btnBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FailOrderActivity.this, CartActivity.class));
            }
        });
    }

}
