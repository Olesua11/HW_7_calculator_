package com.example.hw_7_calculator_;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.slider.RangeSlider;

public class SecondActivity extends AppCompatActivity {
    private Button nextButton;
    private TextView categoryTextView;
    private boolean isButtonVisible = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nextButton = findViewById(R.id.button_next);
        categoryTextView = findViewById(R.id.text_view_category);
        Spinner spinner = findViewById(R.id.spinner);

        TextView textViewResult = findViewById(R.id.text_view_result);
        textViewResult.setText(getIntent().getStringExtra(MainActivity.KEY2));

        RangeSlider rangeSlider1 = findViewById(R.id.range_slider1);
        RangeSlider rangeSlider2 = findViewById(R.id.range_slider2);
        rangeSlider1.setValues(30.0f,57.0f);
        rangeSlider2.setValues(30.0f,57.0f);
        String[] items = {"Type", "Age", "Name"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(SecondActivity.this, R.xml.spinner_text, items);
        adapter.setDropDownViewResource(R.xml.spinner_drop);
        spinner.setAdapter(adapter);
//        nextButton.setVisibility(View.GONE);
    }


    public void onLikeButtonClick(View view) {
        ImageButton heartButton = findViewById(R.id.heart);

        if (heartButton.isSelected()) {
            heartButton.setBackgroundResource(R.drawable.ic_icon_unlike);
        } else {
            heartButton.setBackgroundResource(R.drawable.ic_icon_like);
        }

        heartButton.setSelected(!heartButton.isSelected());
    }

    public void onNextButtonClick(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(MainActivity.KEY1, true);
        startActivity(intent);
        finish();
    }
}
