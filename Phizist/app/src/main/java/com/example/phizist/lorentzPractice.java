package com.example.phizist;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lorentzPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorentz_prac);

    }

    public void get(View view) {
        EditText editText = findViewById(R.id.input);
        String  x =  editText.getText().toString();
        if(x.isEmpty()){
            Toast.makeText(this,"Please Enter Velocity",Toast.LENGTH_SHORT).show();
            return;
        }
        long vel= Long.parseLong(x);
        long c = 3*100000000;
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
            EditText ans = findViewById(R.id.answer);
            String y = ans.getText().toString();
            if(y.isEmpty()){
                textView.setVisibility(View.VISIBLE);
            }
            else{
                x = Double.toString(div);

                ConstraintLayout layout = findViewById(R.id.lorentz);
                if(x.compareTo(y)==0){
                    layout.setBackgroundColor(Color.GREEN);
                }
                else{
                    layout.setBackgroundColor(Color.RED);
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}