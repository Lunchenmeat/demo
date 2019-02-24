package com.example.chenguanhua.demo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    Button addBtn;
    Button clearBtn;

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
                    Object o = create();
                    mObjectViewModel.insert(o);
                    Log.d("WEIRD", "HIIIIII: ");
                }
                else
                {

                }

            }
        });

    }

    public Object create(){
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
        for (int i = 0, len = b.length(); i < len; i++) {
            if (Character.isDigit(b.charAt(i))) {
                return false;
            }
        }
        for (int i = 0, len = c.length(); i < len; i++) {
            if (Character.isDigit(c.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
