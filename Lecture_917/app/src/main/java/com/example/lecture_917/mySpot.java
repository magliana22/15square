package com.example.lecture_917;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class mySpot {
private int r;
private int g;
private int b;
private int cx;
private int cy;

private Paint random;

public mySpot(int x, int y) {
cx=x;
cy=y;
r=30;
random=new Paint();
random.setColor(Color.RED);
}

public void Draw(Canvas canvas){
    canvas.drawCircle(cx, cy, r, random);

}
//make random color
    public Paint randomGen(){

    Random rd=new Random();
    r= rd.nextInt(255);
    g=rd.nextInt(255);
    b=rd.nextInt(255);
    Paint ran=new Paint();
    int c=Color.rgb(r,g,b);
    ran.setColor(c);
    return ran;
    }
//Paint random=new Paint();

}


