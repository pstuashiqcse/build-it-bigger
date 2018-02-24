package com.mcc.buildit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.mcc.buildit.R;
import com.mcc.buildit.utility.ActivityUtils;
import com.mcc.buildit.utility.AdUtils;
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


        // show banner ads
        AdUtils.getInstance(getActivity()).showBannerAd((AdView) view.findViewById(R.id.adView));

        // load full screen ad
        AdUtils.getInstance(getActivity()).loadFullScreenAd(getActivity());


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
                showAdThenActivity();
            }
        });
    }

    private void showJavaJoke() {
        MyJoke joke  = new MyJoke();
        String jokestr = joke.getJokeOfTheDay();
        Utils.showToast(getActivity(), jokestr);
    }

    public void showAdThenActivity() {
        if (AdUtils.getInstance(getActivity()).showFullScreenAd()) {
            AdUtils.getInstance(getActivity()).getInterstitialAd().setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    ActivityUtils.getInstance().invokeAndroidJoke(getActivity(), new MyJoke().getJokeOfTheDay());
                }
            });
        } else {
            ActivityUtils.getInstance().invokeAndroidJoke(getActivity(), new MyJoke().getJokeOfTheDay());
        }
    }

}
