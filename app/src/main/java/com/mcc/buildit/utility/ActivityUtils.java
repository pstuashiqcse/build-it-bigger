package com.mcc.buildit.utility;

import android.app.Activity;
import android.content.Intent;


public class ActivityUtils {

    private static ActivityUtils sActivityUtils = null;
    public static ActivityUtils getInstance() {
        if(sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }


    /*public void invokeActivity(Activity activity, ArrayList<IngredientsModel> ingredientsModels, ArrayList<StepsModel> stepsModels) {
        Intent intent = new Intent(activity, RecipeStepActivity.class);
        intent.putParcelableArrayListExtra(Constants.KEY_INGREDIENTS, ingredientsModels);
        intent.putParcelableArrayListExtra(Constants.KEY_STEPS, stepsModels);
        activity.startActivity(intent);
    }*/

    public void invokeAndroidJoke(Activity activity, String joke) {
        Intent intent = new Intent("com.mcc.JOKE");
        intent.putExtra("joke", joke);
        activity.startActivity(intent);
    }




}
