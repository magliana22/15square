package com.example.lecture_917;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class MySurfaceView extends SurfaceView implements View.OnTouchListener {

    protected ArrayList<mySpot> theSpots;
    //need to implement constructor and enable drawing
    public MySurfaceView(Context context, AttributeSet attrs){

        super(context,attrs);
        setWillNotDraw(false);
        theSpots=new ArrayList<mySpot>();
        mySpot l=new mySpot(100,100);
            theSpots.add(l);
        //Listen for touch events
        setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event){
        //add spot with location of touch
        //after adding, need to tell the Surface view to redraw
        int x = (int)event.getX();
        int y = (int)event.getY();
        mySpot aSpot=new mySpot(x,y);
        theSpots.add(aSpot);
        invalidate();
        return true;
    }

    //need to add a draw function
    public void onDraw(Canvas canvas){


            for(mySpot spot : theSpots){
                spot.Draw(canvas);
            }
    }
}
