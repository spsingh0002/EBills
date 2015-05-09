package com.apathon.ebills;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.apathon.ebills.screens.BrowseActivity;
import com.apathon.ebills.screens.MakeSellerActivity;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnBrowse_Bills, btnSearch_Bills ,btnAdd_Bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBrowse_Bills = (Button)findViewById(R.id.btnBrowse_Bills);
        btnSearch_Bills = (Button)findViewById(R.id.btnSearch_Bills);
        btnAdd_Bills = (Button)findViewById(R.id.btnAdd_Bills);
        btnBrowse_Bills.setOnClickListener(this);
        btnSearch_Bills.setOnClickListener(this);
        btnAdd_Bills.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.btnAdd_Bills:
                intent = new Intent(getApplicationContext(), MakeSellerActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBrowse_Bills:
                intent = new Intent(getApplicationContext(), BrowseActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSearch_Bills:

                break;

        }

    }
}
