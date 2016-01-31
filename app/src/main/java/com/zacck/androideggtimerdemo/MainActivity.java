package com.zacck.androideggtimerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lets set up the seekbar
        SeekBar mTimerSeekBar = (SeekBar)findViewById(R.id.mEggTimerSeekBar);
        final TextView mTimerText = (TextView)findViewById(R.id.tvEggTimer);

        mTimerSeekBar.setMax(600);
        mTimerSeekBar.setProgress(30);



        mTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //convert time to minutes and seconds
                //get the number of whole minutes left over
                int minutes = (int)progress/60;
                //get the number of seconds leftover after the minutes by removing the seconds included in the minutes
                int seconds = progress-minutes*60;

                //put the values on the timertextview
                mTimerText.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
