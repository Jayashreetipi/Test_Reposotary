package com.example.userone.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String name = "";
    String[] demoArray = {"a","b","c","d"};
    String[] testArray ;
    String[] testArry2 ;
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);

        SingletonSession.Instance().setUsername("hey");
        SingletonSession.Instance().setDemoArray(demoArray);

        name=SingletonSession.Instance().getUsername();

        testArray = new String[SingletonSession.Instance().getDemoArray().length];
        testArry2 = new String[testArray.length];
        
        testArray = Arrays.copyOf(SingletonSession.Instance().getDemoArray(),SingletonSession.Instance().getDemoArray().length);
        testArry2 = Arrays.copyOf(testArray,testArry2.length);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editText.getText().toString();
                testArry2[a++]=editText.getText().toString();

                Log.e("Name:",name);
                Log.e("Singleton",SingletonSession.Instance().getUsername());

                Log.e("test Arry:",testArray+"");
                Log.e("test Arry2:",testArry2+"");
                Log.e("Singleton",SingletonSession.Instance().getDemoArray()+"");
                if(a>3)
                    a=0;
            }
        });
    }
}
