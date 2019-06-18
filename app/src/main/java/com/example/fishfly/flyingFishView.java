package com.example.fishfly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingFishView extends View {

    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishspeed;
    private int canvasWith, canvasHeight;
    private boolean touch = false;
    private int score, lifeCounterofFish;
    Intent in;



    private int yellowX, yellowY, yellowspeed=15;
    private Paint yellowpaint = new Paint();


    private int greenX, greenY, greenspeed=15;
    private Paint greenpaint = new Paint();

    private int redX, redY, redspeed=15;
    private Paint redpaint= new Paint();



   // int sscore;



    private Bitmap background;
    private Paint scorepaint = new Paint();
    private Bitmap life[] = new Bitmap[2];




    public flyingFishView(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);


        background = BitmapFactory.decodeResource(getResources(), R.drawable.fishb);
        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(true);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(true);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.white);

        fishY = 550;
        score=0;
        lifeCounterofFish=3;




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWith = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(background, 0, 0, null);

        int minfishY = fish[0].getHeight();
        int maxfishY = canvasHeight - fish[0].getHeight() * 2;
        fishY = fishY + fishspeed;

        if (fishY < minfishY) {
            fishY = minfishY;

        }
        if (fishY > maxfishY) {
            fishY = maxfishY;
        }
        fishspeed = fishspeed + 2;


        if (touch) {

            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;

        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowspeed;


        if (hitBallchecker(yellowX, yellowY)) {
            score = score + 10;
            yellowX = yellowX - 100;
        }
        if (yellowX < 0) {
            yellowX = canvasWith + 21;
            yellowY = (int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowpaint);


        // GREEEN
        greenX = greenX - greenspeed;


        if (hitBallchecker(greenX, greenY)) {
            score = score + 10;
            greenX = -100;
        }
        if (greenX < 0) {
            greenX = canvasWith + 21;
            greenY = (int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenpaint);

        // RED
        // GAME OVER
        redX = redX - redspeed;

        if (hitBallchecker(redX, redY))
        {
            redX= -100 ;

            score = score - 10;

            lifeCounterofFish --;

            if(lifeCounterofFish==0)
            {
                Toast.makeText(getContext(),"GAME OVER" ,Toast.LENGTH_LONG).show();
                Intent gameover = new Intent(getContext(),GameOver.class);
                gameover.putExtra("score",score);
                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                getContext().startActivity(gameover);

            }

        }
        if (redX < 0)
        {
            redX=canvasWith + 21;
            redY=(int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;


        }
        canvas.drawText("Score:"+score, 20, 60, scorepaint);

        for(int i=0; i<3; i++)
        {
            int x= (int)(450 + life[0].getWidth() * 1.5 * i );
            int y=30;

            if(i < lifeCounterofFish)
            {
                canvas.drawBitmap(life[0], x, y,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);

            }
        }



        canvas.drawCircle(redX, redY,25, redpaint);







//
//        canvas.drawBitmap(life[0], 450, 15, null);
//
//        canvas.drawBitmap(life[0], 520, 15, null);
//
//        canvas.drawBitmap(life[0], 590, 15, null);
    }

    public boolean hitBallchecker(int x, int y)
    {

        if(fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
                return true;

        }
        return false;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishspeed = -22;

        }
        return true;


    }
}


