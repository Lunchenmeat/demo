package com.example.chenguanhua.demo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    String lastText1, lastText2, lastText3;

    Button addBtn;
    Button clearBtn;

    boolean textView2isDigit = false;
    boolean textView3isDigit = false;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";

    private ObjectViewModel mObjectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mObjectViewModel = ViewModelProviders.of(this).get(ObjectViewModel.class);

        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        setLastEditedValue();

        addBtn = findViewById(R.id.addBtn);
        clearBtn = findViewById(R.id.clearBtn);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //if clear button is clicked
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isInputValid())
                {
                    Object o = createObject();
                    mObjectViewModel.insert(o);
                    saveLastEnteredData(o);
                    finish();
                }
                else
                {
                    printError();
                }

            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear preferences
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
            }
        });

    }

    public Object createObject(){
        String a = textView1.getText().toString();
        String b = textView2.getText().toString();
        String c = textView3.getText().toString();

        Object o = new Object(a,b,c);
        return o;
    }

    public boolean isInputValid(){
        String a = textView1.getText().toString();
        String b = textView2.getText().toString();
        String c = textView3.getText().toString();

        if(a==null || b==null || c==null){
            return false;
        }
        else if(a.isEmpty() || b.isEmpty() || c.isEmpty())
            return false;
        for (int i = 0, len = b.length(); i < len; i++) {
            if (Character.isDigit(b.charAt(i))) {
                textView2isDigit = true;
                return false;
            }
        }
        for (int i = 0, len = c.length(); i < len; i++) {
            if (Character.isDigit(c.charAt(i))) {
                textView3isDigit = true;
                return false;
            }
        }
        return true;
    }

    public void printError(){
        String a = textView1.getText().toString();
        String b = textView2.getText().toString();
        String c = textView3.getText().toString();

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";
        if(a==null || a.isEmpty()){
            text = "TextView 1 is empty";
        }
        else if(b==null || b.isEmpty()){
            text = "TextView 2 is empty";
        }
        else if(c==null || c.isEmpty()){
            text = "TextView 3 is empty";
        }
        else if(textView2isDigit){
            text = "TextView 2 cannot contain digit";
            textView2isDigit = false;
        }
        else if(textView3isDigit){
            text = "TextView 3 cannot contain digit";
            textView3isDigit = false;
        }
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void saveLastEnteredData(Object o){
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        preferencesEditor.putString("TextView1KEY", o.getCode());
        preferencesEditor.putString("TextView2KEY", o.getName());
        preferencesEditor.putString("TextView3KEY", o.getLine());
        preferencesEditor.apply();
    }

    public void setLastEditedValue(){
        lastText1 = mPreferences.getString("TextView1KEY","");
        lastText2 = mPreferences.getString("TextView2KEY","");
        lastText3 = mPreferences.getString("TextView3KEY","");

        textView1.setText(lastText1);
        textView2.setText(lastText2);
        textView3.setText(lastText3);
    }
}
