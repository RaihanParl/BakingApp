package com.bidjidev.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bidjidev.bakingapp.Gson.GsonRecipe;
import com.bidjidev.bakingapp.Helper.ErroHelper;
import com.bidjidev.bakingapp.Helper.RecyclerUtils;
import com.bidjidev.bakingapp.Helper.RotationHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest stringRequest;
    GsonRecipe gsonRecipe;
    @InjectView(R.id.rcMain)
    RecyclerView rcMain;
    String saveResponse;
    @InjectView(R.id.txtNoinet)
    TextView txtNoinet;
    @InjectView(R.id.btnRetry)
    Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        RecyclerUtils.setLayoutManagerStandar(rcMain,MainActivity.this);
        getRecipe();
        if (savedInstanceState != null) {
            RecyclerUtils.setRecyclerViewRecipes(gsonRecipe, RotationHelper.responseCheck(savedInstanceState, saveResponse), MainActivity.this, rcMain);
        }
    }

    public void getRecipe() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET, Server.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                saveResponse = response;
                RecyclerUtils.setRecyclerViewRecipes(gsonRecipe, response, MainActivity.this, rcMain);
                ErroHelper.success(rcMain, txtNoinet, btnRetry);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErroHelper.error(txtNoinet,btnRetry);
            }
        });
        requestQueue.add(stringRequest);
    }
    public void retry(View v){
        getRecipe();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("response", saveResponse);
    }


}