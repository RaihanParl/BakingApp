package com.bidjidev.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.Helper.MockData;
import com.bidjidev.bakingapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by You on 8/27/17.
 */

public class BakingAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            RemoteViews mView = initViews(context, appWidgetManager, widgetId);
            appWidgetManager.updateAppWidget(widgetId, mView);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.test_layout);

        Intent intent = new Intent(context, BakingAppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        Type type = new TypeToken<List<GsonRecipe.Recipes>>(){}.getType();
        List<GsonRecipe.Recipes> recipes = new GsonBuilder().create().fromJson(MockData.DATA_1, type);

        GsonRecipe.Recipes recipe = recipes.get(0);
        String sRecipe = new GsonBuilder().create().toJson(recipe);

        intent.putExtra(RemoteView.SELECTED_RECIPE, sRecipe);
        mView.setRemoteAdapter(widgetId, R.id.list, intent);

        return mView;
    }
}
