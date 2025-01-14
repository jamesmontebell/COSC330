package com.example.multiply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Button multiplyButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        multiplyButton = findViewById(R.id.multiplyButton);
        resultText = findViewById(R.id.resultText);

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiplyNumber();
            }
        });
    }

    private void multiplyNumber() {
        String inputText = numberInput.getText().toString();
        if (!inputText.isEmpty()) {
            double number = Double.parseDouble(inputText);
            double result = number * number;
            resultText.setText("Result: " + result);
        } else {
            resultText.setText("Please enter a number.");
        }
    }
}
