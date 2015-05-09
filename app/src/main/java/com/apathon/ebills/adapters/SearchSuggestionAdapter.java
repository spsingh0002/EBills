package com.apathon.ebills.adapters;

import android.content.Context;
import android.database.Cursor;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.db.DataBaseHelper;

/**
 * Created by Suraj on 09-05-2015.
 */
public class SearchSuggestionAdapter extends SimpleCursorAdapter {
    public SearchSuggestionAdapter(Context context, Cursor c) {
        super(context, R.layout.layout_item_row, c, new String[]{DataBaseHelper.Column_tag_name}, new int[]{R.id.search}, FLAG_REGISTER_CONTENT_OBSERVER);
        setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return App.getDb().search(constraint.toString());
            }
        });
    }
}
