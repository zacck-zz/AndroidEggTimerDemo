package com.zacck.androideggtimerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar mTimerSeekBar;
    TextView mTimerText;
    Boolean counterisActive = false;
    Button btController;
    CountDownTimer mCountDownTimer;

    //This takes in the number of seconds left
    public void UpdateTimer(int SecondsLeft)
    {
        //convert time to minutes and seconds
        //get the number of whole minutes left over
        int minutes = (int)SecondsLeft/60;
        //get the number of seconds leftover after the minutes by removing the seconds included in the minutes
        int seconds = SecondsLeft-minutes*60;

        //for more accurate zeroes
        String secondString = Integer.toString(seconds);
        if (seconds <= 9)
        {
            secondString ="0"+secondString;
        }
        //put the values on the timertextview
        mTimerText.setText(Integer.toString(minutes)+":"+secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lets set up the seekbar
        mTimerSeekBar = (SeekBar)findViewById(R.id.mEggTimerSeekBar);
        mTimerText = (TextView)findViewById(R.id.tvEggTimer);
        btController = (Button)findViewById(R.id.btEggTimerController);

        mTimerSeekBar.setMax(600);
        mTimerSeekBar.setProgress(30);



        mTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                UpdateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void controlTimer(View view)
    {
        //flag the counter as active and disable seekbar
        if(counterisActive == false) {
            counterisActive = true;
            mTimerSeekBar.setEnabled(false);
            btController.setText("Stop!");


            //lets use the countdowntimer to count the stages
            //add a tenth to the delay to standardise
           mCountDownTimer = new CountDownTimer(mTimerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    //Convert Milliseconds back to seconds
                    UpdateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    mTimerText.setText("0:00");
                    MediaPlayer mMediaPlayer = MediaPlayer.create(getApplication(), R.raw.airhorn);
                    mMediaPlayer.start();
                }
            }.start();
        }
        else
        {
            mTimerText.setText("00:30");
            mTimerSeekBar.setProgress(30);
            mCountDownTimer.cancel();
            btController.setText("Go");
            mTimerSeekBar.setEnabled(true);
            counterisActive = false;

        }

    }
}
