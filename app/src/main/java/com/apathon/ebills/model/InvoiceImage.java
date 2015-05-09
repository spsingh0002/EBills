package com.apathon.ebills.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView;

import com.apathon.ebills.utils.Util;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Suraj on 09-05-2015.
 */
public class InvoiceImage {
    private int id;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        if (Util.isEmptyOrNull(path)) {
            return null;
        }
        return BitmapFactory.decodeFile(path);
    }

    public Bitmap getThumbBitmap(Context mContext) {
        if (Util.isEmptyOrNull(path)) {
            return null;
        }
        Options options = new Options();
        options.outHeight = Util.convertToPx(mContext, 100);
        options.outWidth = Util.convertToPx(mContext, 100);
        return BitmapFactory.decodeFile(path, options);
    }

    public final void loadInto(ImageView mView) {
        if (Util.isEmptyOrNull(path)){
            return;
        }
        Picasso.with(mView.getContext()).load(new File(path)).into(mView);
    }


}
