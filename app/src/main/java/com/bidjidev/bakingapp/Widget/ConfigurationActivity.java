package com.bidjidev.bakingapp.Widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.Helper.MockData;
import com.bidjidev.bakingapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by You on 8/27/17.
 */

public class BakingAppWidgetConfigurationActivity extends AppCompatActivity {

    private int mAppWidgetId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        setContentView(R.layout.content_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rcMain);
        List<GsonRecipe.Recipes> recipes = new GsonBuilder().create().fromJson(MockData.DATA_1
                , new TypeToken<List<GsonRecipe.Recipes>>(){}.getType());
        rv.setAdapter(new RecyclerViewWidgetAdapter(recipes));
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void execute(GsonRecipe.Recipes recipe){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews views = initViews(this, appWidgetManager, mAppWidgetId, recipe);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);

        Intent resultValue = new Intent(this, BakingAppWidgetService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);

        setResult(RESULT_OK, resultValue);

        finish();
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId, GsonRecipe.Recipes recipe) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.test_layout);

        Intent intent = new Intent(context, BakingAppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        String sRecipe = new GsonBuilder().create().toJson(recipe);

        intent.putExtra(RemoteView.SELECTED_RECIPE, sRecipe);
        mView.setRemoteAdapter(widgetId, R.id.list, intent);

        return mView;
    }


    class RecyclerViewWidgetAdapter extends RecyclerView.Adapter<RecyclerViewWidgetAdapter.WidgetAdapter>{

        private Context context;
        List<GsonRecipe.Recipes> recipes = new ArrayList<>();

        public RecyclerViewWidgetAdapter(List<GsonRecipe.Recipes> recipes){
            this.recipes = recipes;
        }

        @Override
        public WidgetAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            WidgetAdapter widgetAdapter = new WidgetAdapter(view);
            return widgetAdapter ;
        }

        @Override
        public void onBindViewHolder(WidgetAdapter holder, int position) {
            final GsonRecipe.Recipes recipe = recipes.get(position);
            holder.tv.setText(recipe.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    execute(recipe);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (recipes != null)
            return recipes.size();
            else return 0;
        }

        class WidgetAdapter extends RecyclerView.ViewHolder{
            @InjectView(android.R.id.text1)
            TextView tv;
            public WidgetAdapter(View itemView) {
                super(itemView);
                ButterKnife.inject(this, itemView);
            }
        }

    }

}
