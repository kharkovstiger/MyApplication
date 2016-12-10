package com.example.loginpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailEnterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    Button btnBackEmail, btnNextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_enter);

        etEmail= (EditText) findViewById(R.id.etEmail);
        btnBackEmail= (Button) findViewById(R.id.btnBackEmail);
        btnNextEmail= (Button) findViewById(R.id.btnNextEmail);

        btnBackEmail.setOnClickListener(this);
        btnNextEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNextEmail:
                if (etEmail.getText()==null || !etEmail.getText().toString().contains("@")){
                    Toast.makeText(this, "Please, enter real e-mail and press \"NEXT\" or press \"BACK\"", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(this, PasswordEnterActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.btnBackEmail:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            data.putExtra("Email", etEmail.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please, enter your e-mail and press \"NEXT\" or press \"BACK\"", Toast.LENGTH_SHORT).show();
    }
}
