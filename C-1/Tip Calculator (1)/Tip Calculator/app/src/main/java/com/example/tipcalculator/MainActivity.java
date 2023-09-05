package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
                  implements TextView.OnEditorActionListener, View.OnClickListener{

    private String billAmountString="";
    private float tipPercent=.15f;
    // define variables for UI we wanna interact with
    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the UI controls
        billAmountEditText=findViewById(R.id.billAmountEditText);
        percentTextView=findViewById(R.id.percentTextView);
        percentUpButton=findViewById(R.id.percentageUpBtn);
        percentDownButton=findViewById(R.id.percentageDownBtn);
        tipTextView=findViewById(R.id.tipTextView);
        totalTextView=findViewById(R.id.totalTextView);

        //set up the listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentDownButton.setOnClickListener(this);
        percentUpButton.setOnClickListener(this);


    }

    public void calculateAndDisplay()
    {
        billAmountString=billAmountEditText.getText().toString();
        float billAmt;
        if(billAmountString.equals(""))
        {
            billAmt=0;
        }
        else{
            billAmt=Float.parseFloat(billAmountString);

        }

        float tipAmt=billAmt*tipPercent;
        float totalAmt=billAmt+tipAmt;

        NumberFormat currency=NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmt));
        totalTextView.setText(currency.format(totalAmt));

        NumberFormat percent=NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));

    }
    @Override
    public void onClick(View v) {
    switch(v.getId()){
        case R.id.percentageDownBtn:
            tipPercent=tipPercent-0.01f;
            calculateAndDisplay();
            break;
        case R.id.percentageUpBtn:
            tipPercent=tipPercent+0.01f;
            calculateAndDisplay();
            break;
    }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return true;
    }
}