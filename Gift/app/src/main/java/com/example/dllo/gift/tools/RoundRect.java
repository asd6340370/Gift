package com.example.dllo.gift.tools;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;

import com.squareup.picasso.Transformation;

/**
 * Created by dllo on 16/5/26.
 */
public class RoundRect implements Transformation {
    int r;
    public RoundRect(int r) {
        this.r = r;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Bitmap transform(Bitmap source) {

        //切完之后的图片
        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        canvas.drawRoundRect(0, 0,source.getWidth(),source.getHeight(), r, r, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);

        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "circle";
    }
}
