package com.mcc.buildit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mcc.buildit.utility.ActivityUtils;
import com.mcc.buildit.utility.Utils;
import com.mcc.libjokejava.MyJoke;

public class MainFragment extends Fragment {

    private Button btnJavaJoke, btnAndroidJoke;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);
        initListeners();

        return view;
    }

    private void initView(View view) {
        btnJavaJoke = (Button) view.findViewById(R.id.btn_java_joke);
        btnAndroidJoke = (Button) view.findViewById(R.id.btn_android_joke);

    }

    private void initListeners() {
        btnJavaJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJavaJoke();
            }
        });

        btnAndroidJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.getInstance().invokeAndroidJoke(getActivity(), new MyJoke().getJokeOfTheDay());
            }
        });
    }

    private void showJavaJoke() {
        MyJoke joke  = new MyJoke();
        String jokestr = joke.getJokeOfTheDay();
        Utils.showToast(getActivity(), jokestr);
    }

}
