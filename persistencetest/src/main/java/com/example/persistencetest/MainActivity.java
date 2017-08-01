/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.persistencetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.inputContent);
        textView = (TextView)findViewById(R.id.showContent);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.saveContent:
                saveToFile();
                break;
            case R.id.getContent:
                showFileContent();
            default:
                break;
        }
    }

    private void saveToFile(){
        String text = editText.getText().toString();
        FileOutputStream outs = null;
        BufferedWriter bufferWriter = null;
        if(!TextUtils.isEmpty(text)){
            try {
                outs = openFileOutput("data",MODE_PRIVATE);
                bufferWriter = new BufferedWriter(new OutputStreamWriter(outs));
                bufferWriter.write(text);
                editText.setText("");
            }catch (FileNotFoundException ex){

            }catch (IOException e){

            } finally{
                if(bufferWriter!= null){
                    try {
                        bufferWriter.close();
                    }catch (IOException e){

                    }
                }
            }

        }
    }

    public void showFileContent(){
        FileInputStream fileInputSteam;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputSteam = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fileInputSteam));
            String line = "";
            do {
                line = reader.readLine();
                if(!TextUtils.isEmpty(line)) {
                    stringBuilder.append(line);
                }
            }while(!TextUtils.isEmpty(line));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException ex){

        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){

                }

            }
        }
        textView.setText(stringBuilder.toString());
    }

}
