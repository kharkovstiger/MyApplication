package com.example.loginpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordEnterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etPassword;
    Button btnOKPassword, btnBackPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_enter);

        etPassword= (EditText) findViewById(R.id.etPassword);
        btnBackPassword= (Button) findViewById(R.id.btnBackPassword);
        btnOKPassword= (Button) findViewById(R.id.btnOKPassword);

        btnOKPassword.setOnClickListener(this);
        btnBackPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOKPassword:
                if (etPassword.getText()==null || etPassword.getText().toString().equals("")){
                    Toast.makeText(this, "Please, enter your password and press \"OK\" or press \"BACK\"", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=this.getIntent();
                intent.putExtra("Password", etPassword.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnBackPassword:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please, enter your password and press \"OK\" or press \"BACK\"", Toast.LENGTH_SHORT).show();
    }
}
