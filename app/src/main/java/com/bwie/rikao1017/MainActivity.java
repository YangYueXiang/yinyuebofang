package com.bwie.rikao1017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.service.VoidService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start;
    private Button btn_stop;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(MainActivity.this,VoidService.class);
        stopService(intent);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                intent = new Intent(MainActivity.this,VoidService.class);
                startService(intent);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
        }
    }
}
