package com.example.clickcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private TextView textViewCounter;
    private Button button1;
    private Button button2;
    private Integer count = 0;
    private Boolean prime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        button1.setOnClickListener(v -> {
            count = count + 1;
            textViewCounter.setText(String.valueOf(count));

            prime = true;
            int sqrt = (int)Math.ceil(Math.sqrt(count));

            if (count < 2){
                prime = false;
            }
            else if (count != 2) {
                for (int i = 2; i <= sqrt; ++i)
                    if (count % i == 0) {
                        prime = false;
                        break;
                    }
            }

            if (prime){
                textViewCounter.setTextColor(Color.WHITE);
            } else if (count > 0){
                textViewCounter.setTextColor(Color.rgb(0,255,0));
            } else if (count < 0){
                textViewCounter.setTextColor(Color.RED);
            } else{
                textViewCounter.setTextColor(Color.parseColor("#0000FF"));
            }

        });

        button2.setOnClickListener(v -> {
            count = count - 1;
            textViewCounter.setText(String.valueOf(count));

            boolean prime = true;
            int sqrt = (int)Math.ceil(Math.sqrt(count));

            if (count < 2){
                prime = false;
            }
            else if (count != 2) {
                for (int i = 2; i <= sqrt; ++i)
                    if (count % i == 0) {
                        prime = false;
                        break;
                    }
            }

            if (prime){
                textViewCounter.setTextColor(Color.WHITE);
            } else if (count > 0){
                textViewCounter.setTextColor(Color.rgb(0,255,0));
            } else if (count < 0){
                textViewCounter.setTextColor(Color.RED);
            } else{
                textViewCounter.setTextColor(Color.parseColor("#0000FF"));
            }

        });


        textViewCounter.setOnLongClickListener(v -> {
            count = 0;
            textViewCounter.setText(String.valueOf(count));
            textViewCounter.setTextColor(Color.parseColor("#0000FF"));
            return true;
        });

    }


    public void init() {
        textViewCounter = findViewById(R.id.textViewCounter);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("save_count", count);
        outState.putInt("save_color", textViewCounter.getCurrentTextColor());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("save_count",0);
        textViewCounter.setText(String.valueOf(count));
        textViewCounter.setTextColor(Color.parseColor(String.format("#%06X",
                (0xFFFFFF & savedInstanceState.getInt("save_color", 0)))));
    }
}