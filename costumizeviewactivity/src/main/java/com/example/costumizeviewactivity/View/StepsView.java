package com.example.costumizeviewactivity.View;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.costumizeviewactivity.R;

import java.util.List;


public class StepsView extends LinearLayout implements StepsViewIndicator.OnDrawListener {

    private StepsViewIndicator mStepsViewIndicator;
    private FrameLayout mLableSpeed,mLableTime;
    private String[] mSpeedLabels,mTileLabls;
    private int mProgressColorIndicator = Color.YELLOW;
    private int mSpeedTextColor = getResources().getColor(R.color.gray20);
    private int mTimeTextColor = getResources().getColor(R.color.gray60);
    private int mLabelColorIndicator = getResources().getColor(R.color.line);
    private int mBarColorIndicator = getResources().getColor(R.color.line);
    private int mCompletedPosition = 0;
    private Context mContext;

    public StepsView(Context context) {
        this(context, null);
    }

    public StepsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepsView(Context context, AttributeSet attrs,
                     int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext =context;
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_steps_view, this);
        mStepsViewIndicator = (StepsViewIndicator) rootView.findViewById(R.id.steps_indicator_view);
        mStepsViewIndicator.setDrawListener(this);
        mLableSpeed = (FrameLayout) rootView.findViewById(R.id.labels_speed);
        mLableTime = (FrameLayout) rootView.findViewById(R.id.labels_time);
    }

    public String[] getSpeedLabels() {
        return mSpeedLabels;
    }

    public StepsView setSpeedLabels(String[] labels) {
        mSpeedLabels = labels;
        mStepsViewIndicator.setStepSize(labels.length);
        return this;
    }

    public int getProgressColorIndicator() {
        return mProgressColorIndicator;
    }

    public StepsView setProgressColorIndicator(int progressColorIndicator) {
        mProgressColorIndicator = progressColorIndicator;
        mStepsViewIndicator.setProgressColor(mProgressColorIndicator);
        return this;
    }

    public int getLabelColorIndicator() {
        return mLabelColorIndicator;
    }

    public StepsView setLabelColorIndicator(int labelColorIndicator) {
        mLabelColorIndicator = labelColorIndicator;
        return this;
    }

    public int getBarColorIndicator() {
        return mBarColorIndicator;
    }

    public StepsView setBarColorIndicator(int barColorIndicator) {
        mBarColorIndicator = barColorIndicator;
        mStepsViewIndicator.setBarColor(mBarColorIndicator);
        return this;
    }

    public int getCompletedPosition() {
        return mCompletedPosition;
    }

    public StepsView setCompletedPosition(int completedPosition) {
        mCompletedPosition = completedPosition;
        mStepsViewIndicator.setCompletedPosition(mCompletedPosition);
        return this;
    }

    public void drawView() {
        if (mSpeedLabels == null) {
            throw new IllegalArgumentException("labels must not be null.");
        }

        if (mCompletedPosition < 0 || mCompletedPosition > mSpeedLabels.length - 1) {
            throw new IndexOutOfBoundsException(String.format("Index : %s, Size : %s", mCompletedPosition, mSpeedLabels.length));
        }

        mStepsViewIndicator.invalidate();
    }

    @Override
    public void onReady() {
        drawLabels();
    }

    private void drawLabels() {
        List<Float> indicatorPosition = mStepsViewIndicator.getThumbContainerXPosition();

        if (mSpeedLabels != null) {
            for (int i = 0; i < mSpeedLabels.length; i++) {
                TextView mspeedTextView = new TextView(getContext());
                TextView mTimeTextView = new TextView(getContext());
                if(mCompletedPosition<i){
                    mspeedTextView.setVisibility(View.GONE);
                    mTimeTextView.setVisibility(View.GONE);
                }else{
                    mspeedTextView.setVisibility(View.VISIBLE);
                    mTimeTextView.setVisibility(View.VISIBLE);
                }
                mspeedTextView.setText(mSpeedLabels[i]);
                mspeedTextView.setTextColor(mSpeedTextColor);
                mspeedTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                mspeedTextView.setX(indicatorPosition.get(i)+TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,6,mContext.getResources().getDisplayMetrics()));
                mspeedTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                mLableSpeed.addView(mspeedTextView);
                //时间
                mTimeTextView.setText(mTileLabls[i]);
                mTimeTextView.setTextColor(mTimeTextColor);
                mTimeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                mTimeTextView.setX(indicatorPosition.get(i)-TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,mContext.getResources().getDisplayMetrics()));
                mTimeTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                mLableTime.addView(mTimeTextView);
            }
        }
    }

    public StepsView setTimeLables(String[] timeLabels) {
        mTileLabls = timeLabels;
        return this;
    }
}
