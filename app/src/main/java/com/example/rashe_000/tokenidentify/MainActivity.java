package com.example.rashe_000.tokenidentify;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText et_text;
    Button bt_identify,bt_clear,bt_exit;
    TextView tv_identify,tv_keywords,tv_constants,tv_symbols,tv_operators;

    String[] keyWord = {"void", "main", "char", "int", "float", "double", "short", "long", "if",
            "else", "else if", "continue", "for", "while", "do", "return", "switch", "default", "break", "case",
            "const", "auto", "goto"};
    String[] oparetor = {"+", "-", "*", "|", "=", "<=",">=", "<", "<", "%","&","^", "&&", "||", "!=", "++", "--",":","?","<<",">>"};
    String[] symbols ={"(",")","{","}","[","]",",","()",";"};

    String[] variable ={"a","b","c","d","e","f","g","h","I","I'm","i","j","k","A","B","C","D","E","F","G","H","%d","%f","%c","%s","add","sum","mult","multi","avg","div","mod","modulus"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lockScreenOrientation(this);
       // unlockScreenOrientation(this);

        et_text = (EditText) findViewById(R.id.editText);
        et_text.setSelected(true);

        bt_identify = (Button) findViewById(R.id.bt_identify);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        bt_exit = (Button) findViewById(R.id.bt_exit);
        tv_identify = (TextView) findViewById(R.id.tv_identify);
        tv_identify.setSelected(true);
        tv_keywords = (TextView) findViewById(R.id.tv_keywords);
        tv_keywords.setSelected(true);
        tv_constants = (TextView) findViewById(R.id.tv_constants);
        tv_constants.setSelected(true);
        tv_symbols = (TextView) findViewById(R.id.tv_symbols);
        tv_symbols.setSelected(true);
        tv_operators = (TextView) findViewById(R.id.tv_operators);
        tv_operators.setSelected(true);


        bt_identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kws = 0;
                int os = 0;
                int sym =0;
                int cons =0;
                int identify=0;
                int store_length_keyWord = 0;
                int store_length_oparetor = 0;
                int store_length_special_symbols=0;
                int store_cons_length=0;
                int store_identify_length=0;


                try{
                    String text =et_text.getText().toString();
                    String[] result=text.split("\\s");

                    if(text.isEmpty()){
                        tv_keywords.setText("");
                        tv_operators.setText("");
                    }
                for (String x : result) {
                    for (String i : keyWord) {
                        if (x.equals(i)) {
                            store_length_keyWord++;

                        }
                    }
                    for (String i : oparetor) {
                        if (x.equals(i)) {
                            store_length_oparetor++;

                        }
                    }
                    for (String i : symbols) {
                        if (x.equals(i)) {
                            store_length_special_symbols++;

                        }
                    }
                    for (String i : variable) {
                        if (x.equals(i)) {
                            store_identify_length++;

                        }
                    }



                    try{
                        float constants_variables =Float.parseFloat(x);
                        store_cons_length++;

                    }catch (Exception e){

                  }

                }
                String[] keyWord_Store = new String[store_length_keyWord];
                String[] oparetor_Store = new String[store_length_oparetor];
                String[] symbols_Store = new String[store_length_special_symbols];
                String[] constants_Store = new String[store_cons_length];
                String[] identify_Store = new String [store_identify_length];

                for (String x : result) {
                    for (String i : keyWord) {
                        if (x.equals(i)) {
                            keyWord_Store[kws++] = x;

                        }
                    }

                    for (String i : oparetor) {
                        if (x.equals(i)) {
                            oparetor_Store[os++] = x;

                        }
                    }
                    for (String i : symbols) {
                        if (x.equals(i)) {
                                symbols_Store[sym++] = x;

                        }
                    }

                    for (String i : variable) {
                        if (x.equals(i)) {
                            identify_Store[identify++] = x;

                        }
                    }
                    try{
                        float constants_variables =Float.parseFloat(x);
                        String convart =String.valueOf( constants_variables);
                        constants_Store[cons++]=convart;


                    }catch (Exception e){

                    }


                }
                    StringBuilder keyword_String = new StringBuilder(100);
                    StringBuilder operator_String = new StringBuilder(100);
                    StringBuilder symbols_String = new StringBuilder(100);
                    StringBuilder constance_String = new StringBuilder(100);
                    StringBuilder identify_String = new StringBuilder(100);

                    int count =0;
                    int newKeywordLength = removeDublicateValues(keyWord_Store,keyWord_Store.length);
                    for(int  i =0 ; i < newKeywordLength;i++){
                    count++;
                    tv_keywords.setText(keyword_String.append(keyWord_Store[i].toString()));
                    if(newKeywordLength != count)
                    tv_keywords.setText(keyword_String.append(" , "));
                }
                count =0;
                int new_Oparetor_length =removeDublicateValues(oparetor_Store,oparetor_Store.length);
                for(int  i =0 ; i < new_Oparetor_length;i++){
                    count++;
                    tv_operators.setText(operator_String.append(oparetor_Store[i].toString()));
                    if(new_Oparetor_length != count){
                        tv_operators.setText(operator_String.append(" , "));
                    }
                }
                    count =0;
                    int newSymbolsLength =removeDublicateValues(symbols_Store,symbols_Store.length);
                    for(int  i =0 ; i < newSymbolsLength;i++){
                        count++;
                        tv_symbols.setText(symbols_String.append(symbols_Store[i].toString()));
                        if(newSymbolsLength != count){
                            tv_symbols.setText(symbols_String.append(" , "));
                        }
                    }
                    count =0;
                    int newConstantsStore =removeDublicateValues(constants_Store,constants_Store.length);
                    for(int  i =0 ; i < newConstantsStore;i++){
                        count++;
                        tv_constants.setText(constance_String.append(constants_Store[i].toString()));
                        if(newConstantsStore != count){
                            tv_constants.setText(constance_String.append(" , "));
                        }
                    }

                    count =0;
                    int newIdentifyLength =removeDublicateValues(identify_Store,identify_Store.length);
                    for(int  i =0 ; i < newIdentifyLength;i++){
                        count++;
                        tv_identify.setText(identify_String.append(identify_Store[i].toString()));
                        if(newIdentifyLength != count){
                            tv_identify.setText(identify_String.append(" , "));
                        }
                    }




                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Please give your input...",Toast.LENGTH_SHORT).show();
                    et_text.getText().clear();
                    tv_identify.setText("");
                    tv_operators.setText("");
                    tv_constants.setText("");
                    tv_symbols.setText("");
                    tv_keywords.setText("");
                }


            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Clear")
                        .setMessage("Are you Sure ?")
                        .setPositiveButton(" Yes ",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                et_text.getText().clear();
                                tv_identify.setText("");
                                tv_operators.setText("");
                                tv_constants.setText("");
                                tv_symbols.setText("");
                                tv_keywords.setText("");
                            }
                        })

                        .setNegativeButton(" No ", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Exit")
                        .setMessage("Are you Sure ?")
                        .setPositiveButton(" Yes ",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })

                        .setNegativeButton(" No ", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });




    }

    private  int removeDublicateValues(String[]a , int currentSize){

        int start =0;
        while(start < currentSize){

            int remove = removeFomTail(a,currentSize,start+1,a[start]);
            currentSize = currentSize - remove;
            start++;
        }

        return currentSize;
    }
    private  int removeFomTail(String[] a ,int currentSize,int start , String toBeRemove){

        int source = start;
        int target = start;
        int remove =0;
        while(source < currentSize){

            if(a[source].equals(toBeRemove)){
                remove++;
            }else{
                a[target]=a[source];
                target++;
            }
            source++;
        }

        return remove;
    }


    public static void lockScreenOrientation(Activity activity)
    {
        WindowManager windowManager =  (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Configuration configuration = activity.getResources().getConfiguration();
        int rotation = windowManager.getDefaultDisplay().getRotation();

        // Search for the natural position of the device
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE &&
                (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) ||
                configuration.orientation == Configuration.ORIENTATION_PORTRAIT &&
                        (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270))
        {
            // Natural position is Landscape
            switch (rotation)
            {
                case Surface.ROTATION_0:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case Surface.ROTATION_90:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    break;
                case Surface.ROTATION_180:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    break;
                case Surface.ROTATION_270:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
            }
        }
        else
        {
            // Natural position is Portrait
            switch (rotation)
            {
                case Surface.ROTATION_0:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case Surface.ROTATION_90:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case Surface.ROTATION_180:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    break;
                case Surface.ROTATION_270:
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    break;
            }
        }
    }

    public static void unlockScreenOrientation(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

}




