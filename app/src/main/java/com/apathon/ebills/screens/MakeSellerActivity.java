package com.apathon.ebills.screens;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.apathon.ebills.App;
import com.apathon.ebills.R;
import com.apathon.ebills.models.Seller;

public class MakeSellerActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl1;
    private LinearLayout rl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_seller);

        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl3 = (LinearLayout) findViewById(R.id.rl3);
        findViewById(R.id.saveButton).setOnClickListener(this);
    }

    private EditText getEditSellerName(){
        return (EditText) findViewById(R.id.editSellerName);
    }

    private EditText getEditSellerAddress(){
        return (EditText) findViewById(R.id.editSellerAddress);
    }

    private EditText getEditSellerCity(){
        return (EditText) findViewById(R.id.editSellerCity);
    }

    private EditText getEditSellerPin(){
        return (EditText) findViewById(R.id.editSellerPin);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                Seller  seller = new Seller();
                seller.setColumn_seller(getEditSellerName().getText().toString());
                seller.setColumn_seller_address(getEditSellerAddress().getText().toString());
                seller.setColumn_seller_city(getEditSellerCity().getText().toString());
                seller.setColumn_seller_pin(getEditSellerPin().getText().toString());
                long l = App.getDb().insertSeller(seller);
                if (l!=-1)
                {
                    Toast.makeText(getApplicationContext(),"Seller Inserted successfully.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MakeItemActivity.class);
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
