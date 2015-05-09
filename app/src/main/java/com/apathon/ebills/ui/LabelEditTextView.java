package com.apathon.ebills.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.apathon.ebills.R;

/**
 * Created by Suraj on 09-05-2015.
 */
public class LabelEditTextView extends LinearLayout {
    public LabelEditTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setOrientation(VERTICAL);

        if (attrs==null)return;
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.LabelEditTextView);

        //Use a
        String hint=a.getString(R.styleable.LabelEditTextView_hint);
        int id=a.getInteger(R.styleable.LabelEditTextView_editTextId, 0);
        String label=a.getString(R.styleable.LabelEditTextView_label);


        //Don't forget this
        a.recycle();
    }

    public LabelEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LabelEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    public LabelEditTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


}
