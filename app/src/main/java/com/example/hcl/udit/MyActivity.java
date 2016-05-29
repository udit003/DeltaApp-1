package com.example.hcl.udit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    private final List<Integer> colors = new ArrayList<Integer>(){
        {
            add(R.color.color1);
            add(R.color.color2);
            add(R.color.color3);
            add(R.color.color4);
            add(R.color.color5);
            add(R.color.color6);

        }
    };
    int index=0;
    RelativeLayout Llayout;
    int counter = 0;

    TextView counterDisplay;
    final String fileName = "myfile";
    final String key = "COUNTER_VALUE";
    final String  key2 = "index_value";
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        counterDisplay = (TextView) findViewById(R.id.counterTextView_ID);
        Llayout = (RelativeLayout) findViewById(R.id.background_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myPrefs = getSharedPreferences(fileName,MODE_PRIVATE);
        counter = myPrefs.getInt(key,0);

        counterDisplay.setText(String.valueOf(counter));
        index = myPrefs.getInt(key2,0);
        if(counter>0)
            Llayout.setBackgroundColor(ContextCompat.getColor(this, colors.get(index)));

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt(key,counter);
        editor.putInt(key2,index);
        editor.apply();
    }

    public void OnButtonClick(View v){

        counter++;

        counterDisplay.setText(String.valueOf(counter));
        index =  index +1 >=colors.size()?0:index+1;
        Llayout.setBackgroundColor(ContextCompat.getColor(this, colors.get(index)));
    }

    public void reset(View v)
    {
        counter=0;
        counterDisplay.setText(String.valueOf(counter));
        Llayout.setBackgroundResource(0);
        Toast.makeText(MyActivity.this, "counter reset\nSUCCESSFUL", Toast.LENGTH_SHORT).show();

    }
}
