package com.mcc.libjokejava;

import java.util.ArrayList;
import java.util.Calendar;

public class MyJoke {

    private ArrayList<String> jokes;

    public MyJoke() {
        jokes = new ArrayList<>();
        loadJoke();
    }

    private void loadJoke() {
        // joke for seven days
        jokes.add("Q: Why did the Blonde think it was Sunday?\nA: Because the sun was out.");
        jokes.add("The first computer dates back to Adam and Eve. It was an Apple with limited memory, just one byte. And then everything crashed.");
        jokes.add("About a month before he died, my uncle had his back covered in lard. After that, he went down hill fast.");
        jokes.add("Talking to a liberal is like trying to explain social media to a 70 years old.");
        jokes.add("How is Christmas like your job? You do all the work and the fat guy in the suit gets all the credit.");
        jokes.add("Q: Why did the can crusher quit his job? \nA: Because it was soda pressing.");
        jokes.add("I'll tell you how bad it was in Los Angeles during the riots -- people were actually flooding into Tijuana. That's how bad it was.");
    }

    public String getJokeOfTheDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return jokes.get(day-1);
    }

}
