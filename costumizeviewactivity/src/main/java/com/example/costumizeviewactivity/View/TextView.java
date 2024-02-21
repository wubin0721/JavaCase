package com.example.costumizeviewactivity.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.costumizeviewactivity.R;


public class TextView extends View {

    private String mText="";
    private int mTextColor;

    private int mTextSize;
    private Paint paint = new Paint();

    //在new的时候调用
    public TextView(Context context) {
        super(context);
    }

    //在布局layout中使用
    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //在布局layout中使用，但是会有style
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        mText = array.getString(R.styleable.TextView_text);
        mTextColor = array.getColor(R.styleable.TextView_textColor,mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.TextView_textSize,spToPx(mTextSize));

        //回收
        array.recycle();
        paint.setAntiAlias(true);//抗锯齿
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);
    }

    private int spToPx( int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,
                getResources().getDisplayMetrics());

    }

    //自定义View的测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取我们宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//获取后面30位
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //EXACTLY(固定值)、AT_MOST（warp_content）
        //1、确定的值，这个时候不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        

        //2、给的是wrap_content需要计算
        if(widthMode == MeasureSpec.AT_MOST){
            //计算宽度与字体的长度有关  与字体的大小用画笔来测量
            Rect bounds = new Rect();
            //获取文本的Rect
            paint.getTextBounds(mText,0,mText.length(),bounds);
            width = bounds.width()+getPaddingLeft()+getPaddingRight();
        }
        if(heightMode == MeasureSpec.AT_MOST){
            //计算宽度与字体的长度有关  与字体的大小用画笔来测量
            Rect bounds = new Rect();
            //获取文本的Rect
            paint.getTextBounds(mText,0,mText.length(),bounds);
            height = bounds.height()+getPaddingTop()+getPaddingBottom();;
        }
        //设置控件的宽高
        setMeasuredDimension(width,height);

    }


    //用于绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF();
        paint = new Paint();
        //画文字
        //x 开始的位置
        //y 基线 baseline
        //dy代表的是高度的一半到 baseline的距离
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        //bottom是表示baseline到文字底部的距离
        int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
        int baseLine = getHeight()/2+dy;
        int x = getPaddingLeft();
        canvas.drawText(mText,x,baseLine,paint);
        //画弧
        canvas.drawArc(rectF,10,10,true,paint);
        //画圈
        canvas.drawCircle(10,10,10,paint);
    }

    //处理事件分发，手势交互，事件分发，事件拦截
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://手指按下
                break;
            case MotionEvent.ACTION_MOVE://手指移动
                break;
            case MotionEvent.ACTION_UP://手指抬起
                break;
        }


        return super.onTouchEvent(event);
    }


}
