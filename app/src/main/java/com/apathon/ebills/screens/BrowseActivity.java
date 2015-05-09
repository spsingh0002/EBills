package com.apathon.ebills.screens;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.adapters.ItemAdapter;
import com.apathon.ebills.models.Item;

import java.util.ArrayList;

public class BrowseActivity extends ListActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        list = (ListView) findViewById(android.R.id.list);
        ArrayList<Item> allItems = App.getDb().getAllItems();
        setListAdapter(new ItemAdapter(getApplicationContext(),allItems));

    }

}