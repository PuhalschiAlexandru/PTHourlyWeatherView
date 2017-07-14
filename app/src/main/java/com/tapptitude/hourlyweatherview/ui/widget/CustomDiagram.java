package com.tapptitude.hourlyweatherview.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tapptitude.hourlyweatherview.R;



/**
 * Created by alexpuhalschi on 11/07/2017.
 */

public class CustomDiagram extends View {
    private static final int DEFAULT_RADIUS = 5;
    private static final int LINE_STROKE_WIDTH = 3;
    private static final int TEXT_SIZE = 14;
    private static final int WIDTH_COORDINATE = 0;
    private static final int HEIGHT_COORDINATE = 1;
    private static final int WIDTH_MARGIN = 20;
    private static final int HEIGHT_MARGIN = 30;
    public static final int TEXT_HEIGHT_POSITIVE_OFFSET = 15;
    public static final int TEXT_HEIGHT_NEGATIVE_OFFSET = -25;

    private Paint mCirclePaint;
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mGridPaint;
    private int mTextHeightOffset;

    private int[] mDataSet;
    private int mDataMinValue;
    private int mDataMaxValue;
    private boolean mIsGridDrawn;

    public CustomDiagram(Context context) {
        super(context);
        init();
    }

    public CustomDiagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        initGridPaint();
        initCirclePaint();
        initLineCirclePaint();
        initTextPaint();
        mDataSet = new int[0];
        mIsGridDrawn = false;
    }

    private void initCirclePaint() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.BLACK);
    }

    private void initLineCirclePaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(pxToDp(LINE_STROKE_WIDTH));
    }

    private void initTextPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(spToPx(TEXT_SIZE));
    }

    private void initGridPaint() {
        mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGridPaint.setColor(ContextCompat.getColor(getContext(), R.color.light_gray));
        mGridPaint.setAlpha(40);
    }

    private float pxToDp(float px) {
        Resources r = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, r.getDisplayMetrics());
    }

    private float spToPx(float px) {
        Resources r = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, r.getDisplayMetrics());
    }

    private float[] getRowCoordinates(int i) {
        float[] lineCoordinates = new float[2];
        int height = getHeight();

        lineCoordinates[HEIGHT_COORDINATE] = height - (height / mDataMaxValue) * i;
        return lineCoordinates;
    }

    /**
     * array[0] = xPos
     * array[1] = yPos
     *
     * @param i dataSet index
     * @return
     */
    private float[] calculateCirclePosition(int i) {
        int value = mDataSet[i];
        float[] coordinates = new float[2];

        int width = getWidth() - ((int) pxToDp(WIDTH_MARGIN) * 2);
        int height = getHeight() - ((int) pxToDp(HEIGHT_MARGIN) * 2);
        int step = width / mDataSet.length;

        coordinates[WIDTH_COORDINATE] = step * i + WIDTH_MARGIN;
        coordinates[HEIGHT_COORDINATE] = height - (height / mDataMaxValue) * (value - mDataMinValue) + HEIGHT_MARGIN;

        return coordinates;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mIsGridDrawn) {
            for (int i = 0; i < mDataSet.length; i++) {
                float[] coordinates = calculateCirclePosition(i);

                canvas.drawLine(coordinates[WIDTH_COORDINATE], 0,
                        coordinates[WIDTH_COORDINATE], getHeight(), mGridPaint);
                for (int j = 0; j < mDataMaxValue; j++) {
                    float[] gridCoordinates = getRowCoordinates(j);
                    canvas.drawLine(0, gridCoordinates[HEIGHT_COORDINATE],
                            getWidth(), gridCoordinates[HEIGHT_COORDINATE], mGridPaint);
                    mIsGridDrawn = true;
                }
            }
            if (mIsGridDrawn) {
                for (int i = 0; i < mDataSet.length; i++) {
                    float[] nextCoordinate;
                    float[] coordinates = calculateCirclePosition(i);

                    canvas.drawCircle(coordinates[WIDTH_COORDINATE], coordinates[HEIGHT_COORDINATE],
                            DEFAULT_RADIUS, mCirclePaint);
                    setTextParameters(i);
                    canvas.drawText(mDataSet[i] + "", coordinates[WIDTH_COORDINATE],
                            coordinates[HEIGHT_COORDINATE] - mTextHeightOffset, mTextPaint);

                    if (i < mDataSet.length - 1) {
                        nextCoordinate = calculateCirclePosition(i + 1);
                        canvas.drawLine(coordinates[WIDTH_COORDINATE], coordinates[HEIGHT_COORDINATE],
                                nextCoordinate[WIDTH_COORDINATE], nextCoordinate[HEIGHT_COORDINATE], mLinePaint);
                    }
                }
            }
        }
    }

    public void setData(int[] data) {
        mDataMinValue = 0;
        mDataMaxValue = 0;
        this.mDataSet = data;

        for (int current : data) {
            if (current > mDataMaxValue) {
                mDataMaxValue = current;
            }

            if (current < mDataMinValue) {
                mDataMinValue = current;
            }
        }
    }

    private void setTextParameters(int i) {
        if (i < mDataSet.length - 1) {
            if (mDataSet[i] < mDataSet[i + 1]) {
                mTextHeightOffset = TEXT_HEIGHT_NEGATIVE_OFFSET;
                mTextPaint.setTextAlign(Paint.Align.RIGHT);
                mTextPaint.setColor(getColor());
            } else {
                mTextHeightOffset = TEXT_HEIGHT_POSITIVE_OFFSET;
                mTextPaint.setTextAlign(Paint.Align.LEFT);
                mTextPaint.setColor(getColor());
            }
        }
    }

    private int getColor() {
        int textColor = 0;
        int[] arrayOfColors = new int[6];
        arrayOfColors[0] = ContextCompat.getColor(getContext(), R.color.light_blue);
        arrayOfColors[1] = ContextCompat.getColor(getContext(), R.color.blue);
        arrayOfColors[2] = ContextCompat.getColor(getContext(), R.color.pink);
        arrayOfColors[3] = ContextCompat.getColor(getContext(), R.color.red);
        arrayOfColors[4] = ContextCompat.getColor(getContext(), R.color.green);
        arrayOfColors[5] = ContextCompat.getColor(getContext(), R.color.yellow);
        for (int i = 0; i < arrayOfColors.length; i++) {
            textColor = (int) (Math.random() * 6);
        }
        return arrayOfColors[textColor];
    }
}
