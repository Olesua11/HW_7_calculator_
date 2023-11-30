package com.example.hw_8;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView resultTextView;
    private StringBuilder inputStringBuilder;
    private double firstOperand;
    private String pendingOperation;
    public static final String KEY1 = "key1";
    public static final String KEY2 = "key2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getBooleanExtra(KEY1, false)) finish();

        resultTextView = findViewById(R.id.result_text_view);
        inputStringBuilder = new StringBuilder();

    }

    public void onDigitClick(View view) {
        String digit = ((TextView) view).getText().toString();
        inputStringBuilder.append(digit);
        updateResultTextView();
        Button showResultButton = findViewById(R.id.buttonShowResult);
        showResultButton.setVisibility(View.INVISIBLE);
    }

    public void onOperationClick(View view) {
        String operation = ((TextView) view).getText().toString();
        if (inputStringBuilder.length() > 0) {
            firstOperand = Double.parseDouble(inputStringBuilder.toString());
            inputStringBuilder.setLength(0);
        }
        pendingOperation = operation;
        Button showResultButton = findViewById(R.id.buttonShowResult);
        showResultButton.setVisibility(View.INVISIBLE);
    }

    public void onResultClick(View view) {
        if (pendingOperation != null && inputStringBuilder.length() > 0) {
            double secondOperand = Double.parseDouble(inputStringBuilder.toString());
            inputStringBuilder.setLength(0);

            switch (pendingOperation) {
                case "+":
                    firstOperand += secondOperand;
                    break;
                case "-":
                    firstOperand -= secondOperand;
                    break;
                case "*":
                    firstOperand *= secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        firstOperand /= secondOperand;
                    } else {
                        firstOperand = 0;
                    }
                    break;
            }

            pendingOperation = null;
            inputStringBuilder.append(firstOperand);
            updateResultTextView();
            Button showResultButton = findViewById(R.id.buttonShowResult);
            showResultButton.setVisibility(View.VISIBLE);
        }
    }



    public void onGoButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(KEY2, resultTextView.getText().toString());
        startActivity(intent);
    }


    public void onAC(View view) {
        if (inputStringBuilder.length() > 0) {
            inputStringBuilder.setLength(0);
            inputStringBuilder.append("0");
            Button showResultButton = findViewById(R.id.buttonShowResult);
            showResultButton.setVisibility(View.INVISIBLE);
        }


        firstOperand = 0;
        pendingOperation = null;
        updateResultTextView();
    }


    private void updateResultTextView() {
        resultTextView.setText(inputStringBuilder.toString());
    }

    public void onShowResultButtonClick(View view) {
        String result = resultTextView.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("RESULT", result);
        startActivity(intent);
    }

}