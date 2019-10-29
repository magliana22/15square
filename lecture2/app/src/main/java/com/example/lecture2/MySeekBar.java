package com.example.lecture2;

import android.view.View;
import android.widget.SeekBar;

public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

    private MySurfaceView mySV;

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    //i want to change size of bitmap
        //I need to tell surface view to change the bitmap size

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //i need main activity to tell me about surface view
public TheSeekBarListener(MySurfaceView theSV) {
mySV=theSV;

}
public void onStopTrackingTouch(SeekBar seekbar){

}

}


