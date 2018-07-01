package com.dima.edem1;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dima.edem1.Graphics.GameView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private GameView surfaceView;
    private CountDownTimer countDownTimer;
    TextView textView;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new GameView(this);
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(constructor());
        timer();

    }

    private LinearLayout constructor(){
        //var
        textView = new TextView(this);
        textView1 = new TextView(this);
        LinearLayout conteiner = new LinearLayout(this);
        LinearLayout line_1 = new LinearLayout(this);
        LinearLayout line_2 = new LinearLayout(this);


        //set text
        textView.setText("5");
        textView1.setText("digit " +  (int) (Math.random() * 10));

        //params

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tv_params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tv_params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /*tv_params1.gravity = Gravity.CENTER;
        tv_params2.gravity = Gravity.CENTER;*/
        tv_params1.weight = 1;
        tv_params2.weight = 1;
        tv_params1.leftMargin = 0;
        tv_params2.rightMargin = 0;
        textView.setLayoutParams(tv_params1);
        textView1.setLayoutParams(tv_params2);
        textView.setHeight(180);
        textView1.setHeight(180);
        textView.setTextSize(50);
        textView1.setTextSize(50);
        //orientation
        line_1.setOrientation(LinearLayout.HORIZONTAL);
        line_2.setOrientation(LinearLayout.HORIZONTAL);
        conteiner.setOrientation(LinearLayout.VERTICAL);
        //add to conteiner
        line_1.addView(textView);
        line_1.addView(textView1);
        line_2.addView(surfaceView);
        line_1.setLayoutParams(layoutParams2);
        line_2.setLayoutParams(layoutParams3);
        conteiner.setLayoutParams(layoutParams1);
        conteiner.addView(line_1,0);
        conteiner.addView(line_2,1);


        return conteiner;
    }

    private void timer(){
        textView.setText("" + 5);
        countDownTimer = new CountDownTimer(6*1000,1000){
            @Override
            public void onTick(long l) {
                textView.setText("" + l/1000);
                if(GameView.signal == true){
                    save();
                    GameView.signal = false;
                }
            }
            @Override
            public void onFinish() {
               // timer();
                //surfaceView.f = false;
                textView1.setText("digit " + (int) (Math.random() * 10));
               // Canvas canvas = surfaceView.getHolder().lockCanvas();
               // canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
              //  surfaceView.getHolder().unlockCanvasAndPost(canvas);

                timer();
            }
        };
        countDownTimer.start();
    }
    public void save(){

        /*surfaceView.setDrawingCacheEnabled(true);
        Bitmap cachedBitmap = surfaceView.getDrawingCache();
        Bitmap copyBitmap = cachedBitmap.copy(Bitmap.Config.RGB_565, true);
        surfaceView.destroyDrawingCache();
        FileOutputStream output = null;
        File file = null;
        Log.d("MSG","work");
        try {
            Log.d("MSG","work out exp");
            Calendar cal = Calendar.getInstance();
            file = new File(Environment.getDataDirectory(),"EDEM1" +
                    cal.get(Calendar.YEAR) + "_" + (1 + cal.get(Calendar.MONTH)) + "_"
                            + cal.get(Calendar.DAY_OF_MONTH) + "_"
                            + cal.get(Calendar.HOUR_OF_DAY) + "_"
                            + cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND)
                            + ".png");
            output = new FileOutputStream(file);
            copyBitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        } catch (FileNotFoundException e) {
            file = null;
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/

       /* surfaceView.setDrawingCacheEnabled(true);
        surfaceView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = surfaceView.getDrawingCache();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path+"/edem2.png");
        FileOutputStream ostream;
       // file.mkdirs();
        try {
            Log.d("MSG","" + file.createNewFile()) ;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("MSG","mssg err" +  e.getMessage()) ;
        }
        try {
            ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.flush();
            ostream.close();
            Toast.makeText(getApplicationContext(), "image saved", 5000).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error", 5000).show();
        }*/

        try {
            surfaceView.setDrawingCacheEnabled(true);
            Bitmap bitmap = surfaceView.getDrawingCache();
            File f = null;
            boolean flag = false;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
               /* File file = new File("/sdcard","TTImages_cache");
                if(!file.exists()){
                    flag = file.mkdirs();
                    Toast.makeText(getApplicationContext(), "" + flag, 5000).show();
                }*/
                f = new File("/sdcard/" + "edem2"+".png");
            }
            FileOutputStream ostream = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
            ostream.close();
        } catch(Exception e){
            e.printStackTrace();
            Log.d("MSG","" + e.getMessage() );
        }
    }
}
