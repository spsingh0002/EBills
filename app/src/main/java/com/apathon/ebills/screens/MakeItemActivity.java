package com.apathon.ebills.screens;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.models.Item;

public class MakeItemActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_insert_item);

        findViewById(R.id.submit).setOnClickListener(this);
    }

    private EditText getName(){
        return (EditText) findViewById(R.id.name);
    }

    private EditText getDescription(){
        return (EditText) findViewById(R.id.description);
    }

    private EditText getInvoice(){
        return (EditText) findViewById(R.id.invoice);
    }

    private EditText getOrderNo(){
        return (EditText) findViewById(R.id.orderNo);
    }

    private EditText getAmount(){
        return (EditText) findViewById(R.id.amount);
    }

    private EditText getTags(){
        return (EditText) findViewById(R.id.tags);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                Item item = new Item();
                item.setColumn_item_name(getName().getText().toString());
                item.setColumn_item_desc(getDescription().getText().toString());
                item.setColumn_invoice_no(getInvoice().getText().toString());
                item.setColumn_order_no(getOrderNo().getText().toString());
                item.setColumn_item_amount(getAmount().getText().toString());
                item.setColumn_item_tag(getTags().getText().toString());
                long l = App.getDb().insertItem(item);
                if (l!=-1)
                {
                    Toast.makeText(getApplicationContext(), "Item details Inserted successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),BrowseActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Oops something went wrong!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
