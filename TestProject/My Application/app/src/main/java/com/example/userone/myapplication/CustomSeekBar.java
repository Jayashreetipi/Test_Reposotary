package com.example.userone.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by pavan on 12/6/2017.
 */

public class CustomSeekBar extends AppCompatSeekBar {
    int noGrids = 3;
    private String TAG = "CustomSeekBar";

    public CustomSeekBar(Context context) {
        super(context);
        initBackground();
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBackground();
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBackground();
    }

    public void setNoGrids(int noGrids) {
        this.noGrids = noGrids;
        initBackground();
    }

    private void initBackground() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = RangeBarActivity.dpToPx(getContext(),50);//displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);
        // Draw a solid color on the canvas as background
        canvas.drawColor(Color.TRANSPARENT);

        // Initialize a new Paint instance to draw the rounded rectangle
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setAntiAlias(true);

        // Set an offset value in pixels to draw rounded rectangle on canvas
        int offset = RangeBarActivity.dpToPx(getContext(),5);
        int heightOffset = RangeBarActivity.dpToPx(getContext(),15);

       /* RectF rectF = new RectF(
                offset, // left
                offset, // top
                canvas.getWidth() - offset, // right
                canvas.getHeight() - offset // bottom
        );*/
        RectF rectF = new RectF(
                offset, // left
                heightOffset, // top
                canvas.getWidth() - offset, // right
                canvas.getHeight() - heightOffset // bottom
        );

        int cornersRadius = 25;

        canvas.drawRoundRect(
                rectF, // rect
                cornersRadius, // rx
                cornersRadius, // ry
                paint // Paint
        );

        RectF rectFill = new RectF(
                offset, // left
                heightOffset, // top
                ((float) canvas.getWidth() / getMax() * getProgress()) + offset, // right
                canvas.getHeight() - heightOffset // bottom
        );

        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        canvas.drawRoundRect(
                rectFill, // rect
                cornersRadius, // rx
                cornersRadius, // ry
                paint // Paint
        );

        Paint paintGrid = new Paint();
        paintGrid.setStyle(Paint.Style.FILL);
        paintGrid.setColor(Color.WHITE);

        Log.d(TAG, "ScreenWidth::" + width);
        Log.d(TAG, "CanvasWidth::" + canvas.getWidth());
        for (int i = 1; i <= noGrids; i++) {
            int left = (canvas.getWidth() / noGrids) * i - offset / 2;
            paintGrid.setStyle(Paint.Style.FILL);
            paintGrid.setColor(Color.WHITE);
            canvas.drawRect(new Rect(left, offset, left + offset, canvas.getHeight() - offset), paintGrid);
//            paintGrid.setFakeBoldText(true);
//            paintGrid.setColor(Color.BLUE);
//            canvas.drawText("Test",left-(paintGrid.measureText("Test")/2),offset,paintGrid);
            Log.d(TAG, "left:" + left + ":top:" + offset + ":right:" + (left + offset) + ":bottom:" + (canvas.getHeight() - offset));
        }
        setProgressDrawable(new BitmapDrawable(getContext().getResources(), bmp));
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        initBackground();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        initBackground();
    }
}

