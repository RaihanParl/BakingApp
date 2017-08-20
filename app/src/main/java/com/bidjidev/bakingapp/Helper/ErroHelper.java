package com.bidjidev.bakingapp.Helper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by You on 8/18/17.
 */

public class ErroHelper {
    public static void error(TextView txtError , Button btnRetry) {
        txtError.setVisibility(View.VISIBLE);
        btnRetry.setVisibility(View.VISIBLE);
    }

    public static void success(RecyclerView rvRecipes, TextView txtError, Button btnRetry) {
        rvRecipes.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
    }
}
