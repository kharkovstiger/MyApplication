package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double a=0;
    TextView textView, full;
    Button prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView) findViewById(R.id.textView);
        full= (TextView) findViewById(R.id.full);

        Button btnZero= (Button) findViewById(R.id.btnZero);
        Button btnOne= (Button) findViewById(R.id.btnOne);
        Button btnTwo= (Button) findViewById(R.id.btnTwo);
        Button btnThree= (Button) findViewById(R.id.btnThree);
        Button btnFour= (Button) findViewById(R.id.btnFour);
        Button btnFive= (Button) findViewById(R.id.btnFive);
        Button btnSix= (Button) findViewById(R.id.btnSix);
        Button btnSeven= (Button) findViewById(R.id.btnSeven);
        Button btnEight= (Button) findViewById(R.id.btnEight);
        Button btnNine= (Button) findViewById(R.id.btnNine);

        Button btnPlus= (Button) findViewById(R.id.btnPlus);
        Button btnMinus= (Button) findViewById(R.id.btnMinus);
        Button btnDelete= (Button) findViewById(R.id.btnDelete);
        Button btnMultiply= (Button) findViewById(R.id.btnMultiply);
        Button btnEqual= (Button) findViewById(R.id.btnEqual);

        Button btnPoint= (Button) findViewById(R.id.btnPoint);

        Button btnClear= (Button) findViewById(R.id.btnClear);
        Button btnBackspace= (Button) findViewById(R.id.btnBackspace);

        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEqual.setOnClickListener(this);

        btnPoint.setOnClickListener(this);

        btnClear.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnZero:
                if (textView.getText().toString().equals("0")) break;
            case R.id.btnOne:
            case R.id.btnTwo:
            case R.id.btnThree:
            case R.id.btnFour:
            case R.id.btnFive:
            case R.id.btnSix:
            case R.id.btnSeven:
            case R.id.btnEight:
            case R.id.btnNine:
                Button btnTmp= (Button) findViewById(v.getId());
                textView.append(btnTmp.getText());
                break;
            case R.id.btnPoint:
                if (textView.getText().toString().contains(".")) break;
                if (textView.getText()==null || textView.getText().toString().equals(""))
                    textView.append("0");
                textView.append(".");
                break;
            case R.id.btnMinus:
                if ((full==null || full.getText().toString().isEmpty()) && textView.getText().toString().isEmpty()){
                    textView.append("-");
                    break;
                }
            case R.id.btnPlus:
            case R.id.btnMultiply:
            case R.id.btnDelete:
                expression(v);
                break;
            case R.id.btnEqual:
                expression(v);
                textView.setHint(emptyZero(a));
                break;
            case R.id.btnClear:
                a=0;
                textView.setText("");
                full.setText("");
                prev=null;
                textView.setHint("");
                break;
            case R.id.btnBackspace:
                if (textView.length()>0)
                    textView.setText(textView.getText().toString().substring(0,textView.length()-1));
                break;
        }
    }

    public String emptyZero(double a){
        if (a%1==0)
            return ""+(int)a;
        else
            return ""+a;
    }

    public void expression(View v){
        if (textView.getText()!=null && !textView.getText().toString().equals("")) {
            try {
                Double n = Double.parseDouble(textView.getText().toString());

                if (prev == null) {
                    a = n;
                    if (full == null || full.getText().toString().isEmpty())
                        full.setText(emptyZero(a));
                    else if (!full.getText().toString().contains("+-*/"))
                        full.setText(emptyZero(a));
                } else {
                    switch (prev.getId()) {
                        case R.id.btnPlus:
                            a = a + n;
                            break;
                        case R.id.btnMinus:
                            a = a - n;
                            break;
                        case R.id.btnMultiply:
                            a = a * n;
                            break;
                        case R.id.btnDelete:
                            a = a / n;
                            break;
                    }
                    if (prev != null) full.append(prev.getText().toString() + emptyZero(n));
                }
            } catch (NumberFormatException e) {

            }
            if (v.getId() == R.id.btnEqual)
                prev = null;
            else prev = (Button) v;
            textView.setText("");
            Toast.makeText(this, emptyZero(a), Toast.LENGTH_SHORT).show();
        }
    }
}
