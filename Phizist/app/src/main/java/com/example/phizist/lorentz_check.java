package com.example.phizist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class lorentz_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorentz_check);
    }

    public void get(View view) {
        EditText editText = findViewById(R.id.input);
        String  x =  editText.getText().toString();
        if(x.isEmpty()){
            Toast.makeText(this,"Please Enter Velocity",Toast.LENGTH_SHORT).show();
            return;
        }
        double vel= Double.parseDouble(x);
        double c = 3*100000000;
        TextView textView = findViewById(R.id.result);
        if(vel >= c ){
            textView.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"It's a Invalid input",Toast.LENGTH_LONG).show();
        }
        else {
            double div = vel * vel;
            div /= c * c;
            div = 1 - div;
            div = Math.sqrt(div);
            div = 1/div;
            textView.setText("Lorentz Factor for given input is:" + div);

            div  = BigDecimal.valueOf(div).setScale(6, RoundingMode.HALF_UP).doubleValue();
            EditText ans = findViewById(R.id.answer);

            if(ans.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter the answer",Toast.LENGTH_LONG).show();
                return;
            }

            double y = Double.parseDouble(ans.getText().toString());
            System.out.println(div);

            ConstraintLayout layout = findViewById(R.id.lorentz);
            if(div == y){
                layout.setBackgroundColor(Color.GREEN);
                textView.setVisibility(View.VISIBLE);
                refColor();
            }
            else{
                layout.setBackgroundColor(Color.RED);
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                }
                textView.setVisibility(View.VISIBLE);

                refColor();
            }

        }
    }

    void refColor(){
        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ConstraintLayout layout = findViewById(R.id.lorentz);
                layout.setBackgroundColor(Color.BLACK);
            }
        };

        handler.postDelayed(runnable,2000);
    }

}