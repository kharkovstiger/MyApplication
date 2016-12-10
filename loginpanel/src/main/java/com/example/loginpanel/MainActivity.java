package com.example.loginpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLogin, tvFailed;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin= (TextView) findViewById(R.id.tvLogin);
        tvFailed= (TextView) findViewById(R.id.tvFailed);
        btnLogin= (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, EmailEnterActivity.class);
        startActivityForResult(intent,1);
        tvFailed.setText("");
        tvLogin.setText("");
        tvFailed.setVisibility(View.INVISIBLE);
        tvLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            tvLogin.setText("Your Email: "+data.getStringExtra("Email")+"\nYour Password:"+data.getStringExtra("Password"));
            tvLogin.setVisibility(View.VISIBLE);
        }
        else if (resultCode==RESULT_CANCELED){
            tvFailed.setText("Failed to login.");
            tvFailed.setVisibility(View.VISIBLE);
        }
    }
}
