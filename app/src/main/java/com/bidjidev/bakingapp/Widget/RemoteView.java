package com.bidjidev.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.R;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by You on 8/27/17.
 */

public class BakingAppDataProvider implements RemoteViewsService.RemoteViewsFactory {

    public static final String SELECTED_RECIPE = "com.bidjidev.bakingapp.Widget.BakingAppDataProvider.SELECTED_RECIPE";

    private Context context;
    private Intent intent;
    private List<GsonRecipe.Recipes.Ingredients> ingredientList = new ArrayList<>();

    public BakingAppDataProvider(Context context, Intent intent){
        this.context = context;
        this.intent = intent;
    }

    void initData(){
        String sRecipe = intent.getStringExtra(SELECTED_RECIPE);
        GsonRecipe.Recipes recipe = new GsonBuilder().create().fromJson(sRecipe, GsonRecipe.Recipes.class);
        ingredientList.addAll(recipe.getIngredients());
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        GsonRecipe.Recipes.Ingredients ingredient = ingredientList.get(position);
        RemoteViews views = new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
        views.setTextViewText(android.R.id.text1, String.format(context.getString(R.string.ingredients_detail)
                , ingredient.getQuantity(), ingredient.getMeasure(), ingredient.getIngredient()));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
