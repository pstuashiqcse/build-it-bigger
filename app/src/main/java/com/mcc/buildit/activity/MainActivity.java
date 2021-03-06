package com.mcc.buildit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mcc.buildit.R;
import com.mcc.buildit.MainFragment;
import com.mcc.buildit.utility.ActivityUtils;
import com.mcc.buildit.utility.RemoteJokeTask;
import com.mcc.buildit.utility.TestListener;
import com.mcc.buildit.utility.Utils;

public class MainActivity extends AppCompatActivity {

    private Button btnGceJoke;
    private ProgressBar gceProgress;

    private TestListener testListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnGceJoke = (Button) findViewById(R.id.btn_gce_joke);
        gceProgress = (ProgressBar) findViewById(R.id.gce_progress);

        getSupportFragmentManager().beginTransaction().
                add(R.id.view_container, new MainFragment()).commit();

        btnGceJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    private void loadData() {

        gceProgress.setVisibility(View.VISIBLE);
        btnGceJoke.setEnabled(false);
        RemoteJokeTask remoteJokeTask = new RemoteJokeTask();
        remoteJokeTask.setResponseListener(new RemoteJokeTask.ResponseListener() {
            @Override
            public void onResponse(String response) {
                gceProgress.setVisibility(View.GONE);
                btnGceJoke.setEnabled(true);

                if(testListener != null) {
                    testListener.onData(response);
                }

                if (response != null) {
                    ActivityUtils.getInstance().invokeAndroidJoke(MainActivity.this, response);
                } else {
                    Utils.showToast(MainActivity.this, getString(R.string.error));
                }
            }
        });
        remoteJokeTask.execute();
    }

    public void setTestListener(TestListener testListener) {
        this.testListener = testListener;
    }



}
