package com.example.numberpadcaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button plus;
    Button minus;
    Button Multi;
    Button divi;
    Button clear;
    String scanum1;
    String scanum2;
    EditText scan1;
    EditText scan2;
    TextView result;
    Button[] numButtons = new Button[10];
    Integer Result;
    Integer[] numBtnIDs = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5,
            R.id.num6, R.id.num7, R.id.num8, R.id.num9};
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(MainActivity.this, "데이터 불러오기 성공!", Toast.LENGTH_SHORT).show();

        scan1 = (EditText) findViewById(R.id.scan1);
        scan2 = (EditText) findViewById(R.id.scan2);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        Multi = (Button) findViewById(R.id.Multi);
        divi = (Button) findViewById(R.id.divi);
        result = (TextView) findViewById(R.id.result);
        clear = (Button) findViewById(R.id.clear);

        scan1.setInputType(0);
        scan2.setInputType(0);
        scan1.setText(scanum1);
        scan2.setText(scanum2);
        plus.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                scanum1 = scan1.getText().toString();
                scanum2 = scan2.getText().toString();
                Result = Integer.parseInt(scanum1) + Integer.parseInt(scanum2);
                result.setText("계산 결과 : " + Result.toString());
                return false;
            }
        });

        minus.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                scanum1 = scan1.getText().toString();
                scanum2 = scan2.getText().toString();
                Result = Integer.parseInt(scanum1) - Integer.parseInt(scanum2);
                result.setText("계산 결과 : " + Result.toString());
                return false;
            }
        });

        Multi.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                scanum1 = scan1.getText().toString();
                scanum2 = scan2.getText().toString();
                Result = Integer.parseInt(scanum1) * Integer.parseInt(scanum2);
                result.setText("계산 결과 : " + Result.toString());
                return false;
            }
        });

        divi.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                scanum1 = scan1.getText().toString();
                scanum2 = scan2.getText().toString();
                Result = Integer.parseInt(scanum1) / Integer.parseInt(scanum2);
                result.setText("계산 결과 : " + Result.toString());
                return false;
            }
        });

        clear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                scan1.setText("");
                scan2.getText().clear();
                return false;
            }
        });

        for (i = 0; i < numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (scan1.isFocused() == true) {
                        scanum1 = scan1.getText().toString()
                                + numButtons[index].getText().toString();
                        scan1.setText(scanum1);
                    } else if (scan2.isFocused() == true) {
                        scanum2 = scan2.getText().toString()
                                + numButtons[index].getText().toString();
                        scan2.setText(scanum2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("save", 0);
        scanum1 = pref.getString("edit1", String.valueOf(0));
        scanum2 = pref.getString("edit2", String.valueOf(0));
        scan1.setText(scanum1);
        scan2.setText(scanum2);
    }

    @Override
        protected void onStop(){


            // 저장

            super.onStop();
            SharedPreferences sharedPreferences = getSharedPreferences("save", 0);
            SharedPreferences.Editor pref = sharedPreferences.edit();
            String editText1 = scan1.getText().toString();
            String editText2 = scan2.getText().toString();
            pref.putString("edit1", editText1);
            pref.putString("edit2", editText2);
            pref.commit();
        }

}