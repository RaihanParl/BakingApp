package com.bidjidev.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by You on 8/19/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> stepsData;
    private Context mContext;

    public StepAdapter(ArrayList<HashMap<String, String>> stepsData, Context mContext) {
        this.stepsData = stepsData;
        this.mContext = mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_steps,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(StepAdapter.ViewHolder holder, int position) {
        String stepdesk = stepsData.get(position).get("stepsShortDescription");
        holder.txtStep.setText(stepdesk);
        if (stepdesk.length() > 28){
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            holder.cvSteps.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return stepsData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStep;
        CardView cvSteps;
        public ViewHolder(View view) {
            super(view);
            txtStep = (TextView) view.findViewById(R.id.txtStepDesk);
            cvSteps = (CardView) view.findViewById(R.id.cvSteps);

        }
    }


}
