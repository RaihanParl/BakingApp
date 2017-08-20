package com.bidjidev.bakingapp.Helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.bidjidev.bakingapp.Adapter.MainAdapter;
import com.bidjidev.bakingapp.DetailActivity;
import com.bidjidev.bakingapp.Gson.GsonRecipe;

/**
 * Created by You on 8/18/17.
 */

public class RecyclerUtils {
    public static void setRecyclerViewRecipes(GsonRecipe gsonRecipe, String response, Context c, RecyclerView rc) {
        gsonRecipe = GsonRecipe.GsonBuilder(gsonRecipe, "{recipes:" + response + "}");
        MainAdapter adapter = new MainAdapter(gsonRecipe.recipes, c);
        recyclerOnClick(rc,c,gsonRecipe);
        rc.setAdapter(adapter);
    }

    public static void setLayoutManagerStandar(RecyclerView rc, Context c) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc.setLayoutManager(linearLayoutManager);
    }
    public static void recyclerOnClick(RecyclerView rvRecipe, final Context mContext, final GsonRecipe gsonRecipe) {
        rvRecipe.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Intent detailIntent = new Intent(mContext, DetailActivity.class);

                    IntentHelper.stepsPutExtra(gsonRecipe, position, detailIntent);
                    IntentHelper.ingredientsPutExtra(gsonRecipe, position, detailIntent);
                    IntentHelper.recipePutExtra(gsonRecipe, position, detailIntent);
                    detailIntent.putExtra("string" , "string");

                    mContext.startActivity(detailIntent);


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }
}
