package com.apathon.ebills.screens;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.adapters.SearchSuggestionAdapter;

/**
 * Created by Suraj on 09-05-2015.
 */
public class SearchActivity extends ActionBarActivity {
    private SimpleCursorAdapter mSuggestionAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor = App.getDb().search("");
        mSuggestionAdapter =new SearchSuggestionAdapter(this,cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
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
        return true;
    }

    private void onSearch(String query) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchRequested();
                return true;
            default:
                return false;
        }
    }

}
