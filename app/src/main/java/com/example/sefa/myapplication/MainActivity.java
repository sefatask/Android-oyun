package com.example.sefa.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText,scoreText;
    int sayac,time;
    ImageView iv,iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = 10;
        timeText = (TextView)findViewById(R.id.timetext);
        scoreText = (TextView)findViewById(R.id.scoretext);
        iv = (ImageView)findViewById(R.id.imageView);
        iv1 = (ImageView)findViewById(R.id.imageView1);
        iv2 = (ImageView)findViewById(R.id.imageView2);
        iv3 = (ImageView)findViewById(R.id.imageView3);
        iv4 = (ImageView)findViewById(R.id.imageView4);
        iv5 = (ImageView)findViewById(R.id.imageView5);
        iv6 = (ImageView)findViewById(R.id.imageView6);
        iv7 = (ImageView)findViewById(R.id.imageView7);
        iv8 = (ImageView)findViewById(R.id.imageView8);
        iv9 = (ImageView)findViewById(R.id.imageView9);

        imageArray = new ImageView[] {iv,iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9};
        hideImage();

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("Time : " + millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {

                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("Restart");
                dlg.setMessage("Are you sure to restart game ?");
                dlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                dlg.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"GAME OVER!!!",Toast.LENGTH_LONG).show();
                    }
                });
                dlg.show();
            }
        }.start();

        sayac=0;
    }

    public void İncreaseScore(View view){

        sayac++;
        scoreText.setText("SCORE : " + sayac);

    }

    public void hideImage(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}
