package com.dima.edem1.Enter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dima.edem1.About.About;
import com.dima.edem1.MainActivity;
import com.dima.edem1.R;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener{
Button button1;
Button button2;
Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button1.setText("start");
        button2.setText("about game");
        button3.setText("exit");
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this, About.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                finish();
                break;
        }
    }
}
