package com.example.cookiefix;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cookie(View view)
    {
        TextView text = findViewById(R.id.text);
        Button button = findViewById(R.id.button);
        ImageView image = findViewById(R.id.image);

        text.setText("I'm so full!");
        button.setText("done");
        image.setImageResource(R.drawable.image_5);
    }
}