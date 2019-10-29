package com.example.lecture2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

//private int radius;

public class MySurfaceView extends SurfaceView {

    //helps know what size to draw image
    private int size;

public MySurfaceView(Context context, AttributeSet attrs) {
super(context,attrs);
size=50;
setWillNotDraw(false);

}

public void onDraw(Canvas canvas) {

Paint paint=new Paint();
paint.setColor(Color.MAGENTA);
paint.setStrokeWidth(5.0f);

canvas.drawRect(10,10,300,200,paint);
Bitmap theImg= BitmapFactory.decodeResource(getResources(),R.drawable.oceanphoto);
//scale image before drawing
Bitmap scaled=Bitmap.createScaledBitmap(theImg,4*size,4*size,filter: false)
canvas.drawBitmap(scaled,100,10,null);
}

public void setBitMapSize(int progress){

    size=progress;
    invalidate();
}
}