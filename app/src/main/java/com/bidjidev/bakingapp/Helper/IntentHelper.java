package com.bidjidev.bakingapp.Helper;

import android.content.Intent;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.ParcelableRecipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by You on 8/19/17.
 */

public class IntentHelper {
    public static void stepsPutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {
        ArrayList<HashMap<String, String>> listSteps;
        listSteps = new ArrayList<>();

        for (int i = 0; i < gsonRecipe.recipes.get(position).steps.size(); i++) {
            HashMap<String, String> stepsMap = new HashMap<>();
            stepsMap.put("stepsId", gsonRecipe.recipes.get(position).steps.get(i).id);
            stepsMap.put("stepsShortDescription", gsonRecipe.recipes.get(position).steps.get(i).shortDescription);
            stepsMap.put("stepsDescription", gsonRecipe.recipes.get(position).steps.get(i).description);
            stepsMap.put("stepsVideoURL", gsonRecipe.recipes.get(position).steps.get(i).videoURL);
            stepsMap.put("stepsThumbnailURL", gsonRecipe.recipes.get(position).steps.get(i).thumbnailURL);
            listSteps.add(stepsMap);
            detailIntent.putExtra("listSteps", listSteps);
        }


    }

    public static void ingredientsPutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {

        ArrayList<HashMap<String, String>> listIngredients;
        listIngredients = new ArrayList<>();

        for (int i = 0; i < gsonRecipe.recipes.get(position).ingredients.size(); i++) {
            HashMap<String, String> ingredientsMap = new HashMap<>();
            ingredientsMap.put("ingredientsQuantity", gsonRecipe.recipes.get(position).ingredients.get(i).quantity);
            ingredientsMap.put("ingredientsMeasure", gsonRecipe.recipes.get(position).ingredients.get(i).measure);
            ingredientsMap.put("ingredientsName", gsonRecipe.recipes.get(position).ingredients.get(i).ingredient);
            listIngredients.add(ingredientsMap);
            detailIntent.putExtra("listIngredients", listIngredients);
        }
    }

    public static void recipePutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {

        ParcelableRecipe parcelableRecipe = new ParcelableRecipe(
                gsonRecipe.recipes.get(position).id,
                gsonRecipe.recipes.get(position).name,
                gsonRecipe.recipes.get(position).servings
        );

        detailIntent.putExtra("parcelableRecipe", parcelableRecipe);

    }
}
