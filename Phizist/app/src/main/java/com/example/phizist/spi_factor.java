package com.example.phizist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class spi_factor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spi_factor);

        spi();
    }

    public void spi(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("KK");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("mm");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("ss");

        int hr = Integer.parseInt(simpleDateFormat.format(calendar.getTime()));
        int x = hr;
        int min = Integer.parseInt(simpleDateFormat2.format(calendar.getTime()));
        int y = min;
        int sec = Integer.parseInt(simpleDateFormat3.format(calendar.getTime()));
        TextView textView = findViewById(R.id.spi);
        hr = fac(hr);
        min = min*min*min;

        float spi = min + sec;
        spi = hr/spi;
        if(x>9) {
            if(y>9){
                if(sec>9){
                    textView.setText("Now the time is\n" + x + " : " + y + " : " + sec + "\n&\nSpi Factor is\n" + spi);
                }
                else{
                    textView.setText("Now the time is\n"+x+" : "+y+" : 0"+sec +"\n&\nSpi Factor is\n"+spi);
                }
            }
            else{
                if(sec>9){
                    textView.setText("Now the time is\n" + x + " : 0" + y + " : " + sec + "\n&\nSpi Factor is\n" + spi);
                }
                else{
                    textView.setText("Now the time is\n"+x+" : 0"+y+" : 0"+sec +"\n&\nSpi Factor is\n"+spi);
                }
            }
        }
        else {
            if (y > 9) {
                if (sec > 9) {
                    textView.setText("Now the time is\n0" + x + " : " + y + " : " + sec + "\n&\nSpi Factor is\n" + spi);
                } else {
                    textView.setText("Now the time is\n0" + x + " : " + y + " : 0" + sec + "\n&\nSpi Factor is\n" + spi);
                }
            }
            else {
                if (sec > 9) {
                    textView.setText("Now the time is\n0" + x + " : 0" + y + " : " + sec + "\n&\nSpi Factor is\n" + spi);
                } else {
                    textView.setText("Now the time is\n0" + x + " : 0" + y + " : 0" + sec + "\n&\nSpi Factor is\n" + spi);
                }
            }
        }
        refresh();
    }

    public void refresh(){
        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spi();
            }
        };

        handler.postDelayed(runnable,1000);
    }

    public int fac(int n){
        int ans=1;
        for (int i=2;i<=n;i++)
            ans *= i;

        return ans;
    }

}