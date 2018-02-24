package com.mcc.libjokeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class JokeActivity extends AppCompatActivity {

    private TextView jokeView;
    private String data;

    private static final String STATE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        if(savedInstanceState == null) {
            showJavaJoke();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_joke);
        jokeView = (TextView) findViewById(R.id.joke_view);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showJavaJoke() {
        Intent intent = getIntent();
        if (intent.hasExtra("joke")) {
            data = intent.getStringExtra("joke");
            jokeView.setText(data);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(STATE_KEY, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state != null) {
            String storedData = state.getString(STATE_KEY);
            jokeView.setText(storedData);
        }
    }

}
