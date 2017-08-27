package com.bidjidev.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bidjidev.bakingapp.Adapter.IngreAdapter;
import com.bidjidev.bakingapp.Adapter.StepAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> stepsData, ingreData;
    @InjectView(R.id.rcStep)
    RecyclerView rcStep;
    @InjectView(R.id.rcIngredients)
    RecyclerView rcIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        getListSteps();
        setRcStep();
        setRcIngredients();
//        Toast.makeText(this, listSteps.toString(), Toast.LENGTH_SHORT).show();
    }

    private void getListSteps() {
        stepsData = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("listSteps");
    }

    private void getListIng() {
        ingreData = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("listIngredients");
    }


    private void setRcStep() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        StepAdapter stepsAdapter = new StepAdapter(stepsData, DetailActivity.this);
        rcStep.setLayoutManager(linearLayoutManager);
        rcStep.setAdapter(stepsAdapter);
    }
    private void setRcIngredients() {
        getListIng();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        IngreAdapter ingreAdapter = new IngreAdapter(ingreData, DetailActivity.this);
        rcIngredients.setLayoutManager(linearLayoutManager);
        rcIngredients.setAdapter(ingreAdapter);
    }
}
