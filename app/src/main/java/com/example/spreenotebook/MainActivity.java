package com.example.spreenotebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    int progress;
    private FadingTextView fadingTextView;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();

        progressBar = findViewById(R.id.progreesbarID);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();



    }



    private void startApp() {

        Intent intent= new Intent(MainActivity.this,CreateNoteActivity.class);
        startActivity(intent);
        finish();
    }

    private void doWork() {
        for (progress=20;progress<=100;progress=progress+20){
            try {
                Thread.sleep(1800);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public static int getPx(Context context, int dimensionDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }




}