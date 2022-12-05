package com.quotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.quotes.API.APIRequestData;
import com.quotes.API.RetroServer;
import com.quotes.Adapter.AdapterQuotes;
import com.quotes.Model.QuotesModel;
import com.quotes.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuotes;
    private ProgressBar pbQuotes;
    private List<QuotesModel> ListQuotes;
    private AdapterQuotes adQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuotes = findViewById(R.id.rv_quotes);
        pbQuotes = findViewById(R.id.pb_quotes);

        rvQuotes.setLayoutManager(new LinearLayoutManager(this));
        retrieveQuote();
    }

    private  void retrieveQuote (){
        pbQuotes.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuotesModel>> retrieveProcess = ARD.requestData();

        retrieveProcess.enqueue(new Callback<List<QuotesModel>>() {
            @Override
            public void onResponse(Call<List<QuotesModel>> call, Response<List<QuotesModel>> response) {
                List<QuotesModel> listQuotes = response.body();
                adQuotes = new AdapterQuotes(listQuotes, MainActivity.this);
                rvQuotes.setAdapter(adQuotes);
                pbQuotes.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QuotesModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Reach Server", Toast.LENGTH_SHORT).show();
                pbQuotes.setVisibility(View.GONE);
            }
        });
    }
}