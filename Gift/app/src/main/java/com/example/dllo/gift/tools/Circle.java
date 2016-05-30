package com.example.dllo.gift.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.squareup.picasso.Transformation;

/**
 * Created by dllo on 16/5/26.
 */
public class Circle implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(),source.getHeight());

        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),source.getHeight(),source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        canvas.drawCircle(source.getWidth()/2,source.getHeight()/2,size/2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source,0,0,paint);

        source.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
