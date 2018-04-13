package com.example.userone.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;


/**
 * Created by userone on 3/24/2018.
 */

public class RangeBarActivity extends Activity
{
    String[] ages={"1","2","3"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_bar_layout);

        final CrystalRangeSeekbar rangeSeekbar = findViewById(R.id.rangeSeekbar1);
        final RelativeLayout mp_location_distances_ll = findViewById(R.id.mp_location_distances_ll);
        final CustomSeekBar sb_radius = findViewById(R.id.sb_radius);
        final TextView tvminMax = findViewById(R.id.tv_min_max_range);

        rangeSeekbar.setBarColor(Color.RED);
        rangeSeekbar.setBarHighlightColor(Color.BLUE);

// set listener

        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvminMax.setText(String.valueOf(minValue)+"-"+String.valueOf(maxValue));
            }
        });

        createCSeekBar(getApplicationContext(), sb_radius, null, 3, mp_location_distances_ll, ages);

    }

    private void createCSeekBar(final Context context, final CustomSeekBar cSeekBar, ImageButton resetButton
            , Integer defaultValue,final RelativeLayout llTVLabels, String[] ages) {
        int lableCount = ages.length;
        cSeekBar.setNoGrids(lableCount - 1);
        cSeekBar.setMax(lableCount - 1);
        if (defaultValue != null)
            cSeekBar.setProgress(defaultValue - 1);
        cSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (cSeekBar.getProgress() == 0) {
                    ( (TextView) llTVLabels.getChildAt(0) ).setTextColor(ContextCompat.getColor(context,
                            R.color.colorPrimary));
                    cSeekBar.setProgress(0);
                    //T item = labelsList.get(0);
                   // item.setPosition(0);
                    //onSeekbarChangeListener.onCBChanged(item);
                }
                return false;
            }
        });
        cSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                cSeekBar.onProgressChanged(seekBar, i, b);
                {
                    for (int i1 = 0; i1 < llTVLabels.getChildCount(); i1++) {
                        ( (TextView) llTVLabels.getChildAt(i1) ).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    }
                    ( (TextView) llTVLabels.getChildAt(i) ).setTextColor(ContextCompat.getColor(context,
                            R.color.colorPrimary));
                }
               // T item = labelsList.get(i);
               // item.setPosition(i);
               // if (b)
                   // onSeekbarChangeListener.onCBChanged(item);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        llTVLabels.removeAllViews();


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int segmentSize = ( width - 50 / ( lableCount ));


        for (int i = 1; i <= lableCount; i++) {
            RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            /*if (i == 1)
                mLayoutParams.setMargins(0, 0, 0, 0);
            else if(i == 2)
                mLayoutParams.setMargins(segmentSize *i , 0, 0, 0);
            else{*/

            if (lableCount > 4) {
                mLayoutParams.setMargins(segmentSize * ( i - 1 ), 0, 0, 0);
            } else {
                if (i == 1) {
                    mLayoutParams.setMargins(segmentSize * ( i - 1 ), 0, 0, 0);
                }
                if (i == 2) {
                    mLayoutParams.setMargins(( width - 150 ) / 2, 0, 0, 0);
                }
                if (i == 3) {
                    mLayoutParams.setMargins(( width - 150 ), 0, 0, 0);
                }
            }


            //}
            TextView mTV = (TextView) LayoutInflater.from(context).inflate(R.layout.inflator_lable_tv_layout, null);
            mTV.setText(String.valueOf(ages[i- 1]));
            if (defaultValue != null) {
                if (i == defaultValue) {
                    mTV.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                } else
                    mTV.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            } else mTV.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            mTV.setLayoutParams(mLayoutParams);
            llTVLabels.addView(mTV);
        }
    }

    private void showCustomSeekBar(int i) {

    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
