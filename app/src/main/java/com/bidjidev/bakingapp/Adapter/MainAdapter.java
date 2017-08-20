package com.bidjidev.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by You on 8/16/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<GsonRecipe.Recipes> recipesData;
    private Context mContext;

    public MainAdapter(List<GsonRecipe.Recipes> recipesData, Context mContext) {
        this.recipesData = recipesData;
        this.mContext = mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtNameMain.setText(recipesData.get(position).name);
        holder.txtStepMain.setText(recipesData.get(position).steps.size() + " Steps");
        holder.txtIngreMain.setText(recipesData.get(position).ingredients.size() + " Ingredients Needed");
    }

    @Override
    public int getItemCount() {
        return recipesData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameMain;
        TextView txtStepMain;
        TextView txtIngreMain;
        CardView cvMain;

        public ViewHolder(View view) {
            super(view);
            txtNameMain = (TextView) view.findViewById(R.id.txtNameMain);
            txtStepMain = (TextView) view.findViewById(R.id.txtStepMain);
            txtIngreMain = (TextView) view.findViewById(R.id.txtIngreMain);
        }
    }


}
