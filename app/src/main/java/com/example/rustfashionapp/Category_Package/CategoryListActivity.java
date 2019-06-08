package com.example.rustfashionapp.Category_Package;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.rustfashionapp.AppSingleton;
import com.example.rustfashionapp.MainActivity;
import com.example.rustfashionapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;

public class CategoryListActivity extends AppCompatActivity {

    Category categories[];
    ListView listView;
    String url = "http://10.0.3.2:8080/category/public/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        listView = findViewById(R.id.cat_list);


        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        categories = gson.fromJson(response.toString(), Category[].class);
                        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(CategoryListActivity.this, R.layout.category_card, categories);

                        listView.setAdapter(categoryListAdapter);
                        categoryListAdapter.notifyDataSetChanged();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Category category = (Category) listView.getItemAtPosition(position);
                                Intent intent = new Intent(CategoryListActivity.this, MainActivity.class);
                                intent.putExtra("cat_id", category.CatId);
                                startActivity(intent);
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error while fetching products "+error);

                    }
                }
        );



        AppSingleton.getInstance(CategoryListActivity.this).addToRequestQueue(objectRequest);



    }
}