package com.mcc.buildit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mcc.buildit.R;
import com.mcc.buildit.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().
                add(R.id.view_container, new MainFragment()).commit();
    }



}
