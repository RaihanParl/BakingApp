package com.bidjidev.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bidjidev.bakingapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by You on 8/21/17.
 */

public class IngreAdapter extends RecyclerView.Adapter<IngreAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> ingreData;
    private Context mContext;

    public IngreAdapter(ArrayList<HashMap<String, String>> ingreData, Context mContext) {
        this.ingreData = ingreData;
        this.mContext = mContext;
    }
    @Override
    public IngreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ingre,parent, false);
        return new IngreAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(IngreAdapter.ViewHolder holder, int position) {
        String ingreName = ingreData.get(position).get("ingredientsIngredient");
        String ingreQuantity = ingreData.get(position).get("ingredientsQuantity");
        String ingreMeasure = ingreData.get(position).get("ingredientsMeasure");
        holder.txtIngreName.setText(ingreName);
        holder.txtIngreName.setText(ingreQuantity+ingreMeasure);
    }

    @Override
    public int getItemCount() {
        return ingreData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIngreName,txtIngreQuantity;
        public ViewHolder(View view) {
            super(view);
            txtIngreName = (TextView) view.findViewById(R.id.txtIngreName);
            txtIngreQuantity = (TextView) view.findViewById(R.id.txtIngreQuantity);

        }
    }


}
