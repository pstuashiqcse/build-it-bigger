package com.mcc.buildit.utility;

import android.app.Activity;
import android.content.Intent;

import com.mcc.libjokeandroid.JokeActivity;


public class ActivityUtils {

    private static ActivityUtils sActivityUtils = null;
    public static ActivityUtils getInstance() {
        if(sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }


    public void invokeAndroidJoke(Activity activity, String joke) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra("joke", joke);
        activity.startActivity(intent);
    }




}
