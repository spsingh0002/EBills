package com.apathon.ebills.screens;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.adapters.ItemAdapter;
import com.apathon.ebills.adapters.SearchSuggestionAdapter;
import com.apathon.ebills.db.DataBaseHelper;

/**
 * Created by Suraj on 09-05-2015.
 */
public class SearchActivity extends Activity{
    private SearchSuggestionAdapter mSuggestionAdapter;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.layout_search);
mListView= (ListView) findViewById(R.id.list);
        Cursor cursor = App.getDb().search("");
        mSuggestionAdapter =new SearchSuggestionAdapter(this,cursor);

        final SearchView searchView= (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setSuggestionsAdapter(mSuggestionAdapter);
        searchView.setOnSuggestionListener(new OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                searchView.setQuery(mSuggestionAdapter.getColumnString(position),true);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery(mSuggestionAdapter.getColumnString(position),true);
                return false;
            }
        });
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
mListView.setAdapter(new ItemAdapter(this,App.getDb().searchItem(query,DataBaseHelper.Column_item_tag)));
    }

}
