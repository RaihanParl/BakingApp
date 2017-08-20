package com.bidjidev.bakingapp.Helper;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by You on 8/18/17.
 */

public class RotationHelper {
    public static String responseCheck(Bundle savedInstanceState,String sStorage) {
        if (savedInstanceState != null) {
            String response = savedInstanceState.getString("response");
            sStorage = response;
        }
        return sStorage;
    }
}
