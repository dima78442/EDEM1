package com.dima.edem1.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dima on 24.06.18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public static boolean signal =false;
    public boolean f  = true;

    public DrawThread drawThread;

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(surfaceHolder);
        drawThread.running = true;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean flag = true;
        drawThread.setRunning(false);

        while(flag){
            try {
                drawThread.join();
                flag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class DrawThread extends Thread {

        public Path path;
        public Paint paint;
        private boolean running = false;
        private SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            paint = new Paint();
            path = new Path();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
           // paint.setAlpha(0);
            paint.setStrokeJoin(Paint.Join.BEVEL);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5f);
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {

            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;
                    //canvas.drawPath(path,paint);
                    //if (f) {
                       // canvas.drawColor(Color.WHITE);
                     //   f = false;
                    //}else{
                    if (f) {
                        canvas.drawPath(path,paint);
                    }else{
                        canvas.drawColor(Color.WHITE);
                        //f = true;
                        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
                    }
                    //  }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dx = event.getX();
        float dy = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                f = true;
                drawThread.path.moveTo(dx,dy);
                break;
            case MotionEvent.ACTION_UP:
                signal = true;
                f = false;
                drawThread.path = new Path();
                break;
            case MotionEvent.ACTION_MOVE:
                f = true;
                drawThread.path.lineTo(dx,dy);
                break;
            default:
                return false;
        }
        return true;
    }

}
