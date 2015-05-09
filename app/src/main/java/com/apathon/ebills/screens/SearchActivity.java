package com.apathon.ebills.screens;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.adapters.SearchSuggestionAdapter;

/**
 * Created by Suraj on 09-05-2015.
 */
public class SearchActivity extends Activity{
    private SimpleCursorAdapter mSuggestionAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.layout_search);

        Cursor cursor = App.getDb().search("");
        mSuggestionAdapter =new SearchSuggestionAdapter(this,cursor);

        SearchView searchView= (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setSuggestionsAdapter(mSuggestionAdapter);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                onSearch(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                mSuggestionAdapter.getFilter().filter(s);
                mSuggestionAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }


    private void onSearch(String query) {

    }

}
