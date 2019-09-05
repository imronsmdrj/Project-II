package com.ron.barcodepos.getData;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ron.barcodepos.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contacts> contacts;
    private Adapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerViewData);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("barang", "");

        btnAdd = (Button) findViewById(R.id.btnAddData);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }

    public void fetchContact(String type, String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Contacts>> call = apiInterface.getContacts(type, key);
        call.enqueue(new Callback<List<Contacts>>() {
            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                progressBar.setVisibility(View.GONE);
                contacts = response.body();
                adapter = new Adapter(MainActivity.this, contacts);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact("barang", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                fetchContact("barang", newText);
                return false;
            }
        });
        return true;
    }
}
