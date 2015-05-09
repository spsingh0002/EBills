package com.apathon.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apathon.ebills.models.Tags;

/**
 * Created by pranavsharma on 20/04/15.
 */
public class CreateTagDialog extends DialogFragment implements View.OnClickListener {

    EditText editText;
    Button save, cancel;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = inflater.inflate(R.layout.create_tag,container,false);
        editText = (EditText) root.findViewById(R.id.editText1);
        save = (Button)root.findViewById(R.id.save);
        cancel = (Button)root.findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.save:
                String text = editText.getText().toString();
                Tags tags = new Tags();
                tags.setColumn_tags_name(text);
                long l = App.getDb().insertTags(tags);
                if(l!=-1)
                {
                    Toast.makeText(getActivity(),"Tag Inserted Successfully" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Opps , there's some problem!" , Toast.LENGTH_SHORT).show();
                }
                dismiss();
                break;
            case R.id.cancel:
                    dismiss();
                break;
        }

    }
}
