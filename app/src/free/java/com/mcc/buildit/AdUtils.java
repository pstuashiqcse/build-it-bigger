package com.mcc.buildit;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdUtils {

    private static AdUtils adUtils;

    private InterstitialAd mInterstitialAd;

    private boolean disableBannerAd = false, disableInterstitialAd = false;

    private AdUtils(Context context) {
        MobileAds.initialize(context, context.getResources().getString(R.string.app_ad_id));
    }

    public static AdUtils getInstance(Context context) {
        if (adUtils == null) {
            adUtils = new AdUtils(context);
        }
        return adUtils;
    }

    public void showBannerAd(final AdView mAdView) {
        if (disableBannerAd) {
            mAdView.setVisibility(View.GONE);
        } else {
            //AdRequest adRequest = new AdRequest.Builder().build();
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("58CA02840058242E0D5ABFABA63453EA")
                    .build();
            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    mAdView.setVisibility(View.GONE);
                }


            });
        }
    }

    public void loadFullScreenAd(Activity activity) {
        if (!disableInterstitialAd) {
            mInterstitialAd = new InterstitialAd(activity);
            mInterstitialAd.setAdUnitId(activity.getResources().getString(R.string.interstitial_ad_unit_id));
            //AdRequest adRequest = new AdRequest.Builder().build();
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("58CA02840058242E0D5ABFABA63453EA")
                    .build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public boolean showFullScreenAd() {
        if (!disableInterstitialAd) {
            if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                return true;
            }
        }
        return false;
    }

    public InterstitialAd getInterstitialAd() {
        return mInterstitialAd;
    }

    public void disableBannerAd() {
        this.disableBannerAd = true;
    }

    public void disableInterstitialAd() {
        this.disableInterstitialAd = true;
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

}
