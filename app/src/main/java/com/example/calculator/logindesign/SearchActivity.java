package com.example.calculator.logindesign;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countries));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SearchActivity.this, ListActivity.class));
            }
        });
        listView.setAdapter(adapter);
  //  populateListview();

    }

   /* private void populateListview() {
    String[] myitem ={"blue","red","green","black","white"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.layout_list,myitem);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }*/
}
