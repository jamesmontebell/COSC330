package com.example.turkey;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;


import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Coffee coffee = new Coffee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkCream(View view) {
        CheckBox cream = findViewById(R.id.cream);

        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    coffee.setIsCream(true);
                } else {
                    coffee.setIsCream(false);
                }
            }
        });
    }

    public void checkChocolate(View view) {
        CheckBox chocolate = findViewById(R.id.chocolate);

        chocolate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    coffee.setIsChocolate(true);
                } else {
                    coffee.setIsChocolate(false);
                }
            }
        });

    }

    public void minus(View view) {
        TextView q = findViewById(R.id.qCount);
        coffee.updateQuantity(0);
        q.setText(String.valueOf(coffee.getQuantity()));
    }
    public void plus(View view) {
        TextView q = findViewById(R.id.qCount);
        coffee.updateQuantity(1);
        q.setText(String.valueOf(coffee.getQuantity()));
    }

    public void order(View view) {
        TextView smry = findViewById(R.id.summary);
        smry.setText("Add whipped cream? " + coffee.getIsCream() + "\n Add Chocolate? " +
                coffee.getIsChocolate() + "\n Quantity: " + coffee.getQuantity() + "\n\n Price: $" +
                coffee.getCost() + "\n THANK YOU!");
    }
}