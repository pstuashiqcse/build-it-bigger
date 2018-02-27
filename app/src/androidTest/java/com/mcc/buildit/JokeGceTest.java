package com.mcc.buildit;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mcc.buildit.activity.MainActivity;
import com.mcc.buildit.utility.RemoteJokeTask;
import com.mcc.buildit.utility.TestListener;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JokeGceTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void checkJoke() {


        // 1st test case - server test
        RemoteJokeTask remoteJokeTask = new RemoteJokeTask();
        remoteJokeTask.setResponseListener(new RemoteJokeTask.ResponseListener() {
            @Override
            public void onResponse(String response) {
                assertTrue("Server response: "+response, (response != null && !response.isEmpty()));
            }
        });
        remoteJokeTask.execute();

        // 2nd test case - Server + UI test
        onView(withId(R.id.btn_gce_joke)).perform(click());
        mActivityTestRule.getActivity().setTestListener(new TestListener() {
            @Override
            public void onData(final String data) {
                if(data == null || data.isEmpty()) {
                    fail("No response from server");
                }
            }
        });



    }

}
