package com.mcc.buildit;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mcc.buildit.activity.MainActivity;
import com.mcc.buildit.utility.TestListener;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JokeGceTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void checkJoke() {

        mActivityTestRule.getActivity().setTestListener(new TestListener() {
            @Override
            public void onData(String data) {
                if(data == null || data.isEmpty()) {
                    fail("No response from server");
                }
            }
        });

        onView(withId(R.id.btn_gce_joke)).perform(click());

    }

}
