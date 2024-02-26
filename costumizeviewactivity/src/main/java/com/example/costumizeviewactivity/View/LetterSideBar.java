package com.example.costumizeviewactivity.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


public class LetterSideBar extends View {

    private Paint mPaint = new Paint();
    private int itemHeight;

    //当前触摸的位置的字母
    private String CurrentLetter;

    //定义26个字母
    public static String[] alhpa = {"A","B","C","D","F","G","H","I","J","K","L","M","N","O",
            "P","Q","R","S","T","U","V","W","X","Y","Z","#"};



    public LetterSideBar(Context context) {
        super(context, null);
    }

    public LetterSideBar(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        //自定义属性：颜色 字体大小
        mPaint.setAntiAlias(true);
        //12是像素 px
        mPaint.setTextSize(sp2px(12));
        mPaint.setColor(Color.BLUE);

    }

    //sp 转 px
    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //计算指定宽度 = 左右的 padding+ 字母的宽度(取决于你的画笔)
        int textWidth = (int) mPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        //高度直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }


    //手势监测
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(mListener != null){
                    mListener.touch(CurrentLetter,false);
                }
                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算当前触摸字母，获取当前的位置
                float CurrentMoveY = event.getY();
                //当前位置 = CurrentMoveY / 字母高度
                itemHeight = (getHeight() - getPaddingTop()-getPaddingBottom()) / alhpa.length;
                int currentPosition = (int) (CurrentMoveY / itemHeight);

                if(currentPosition < 0){
                    currentPosition = 0;
                }

                if(currentPosition > alhpa.length - 1){
                    currentPosition = alhpa.length - 1;
                }

                CurrentLetter = alhpa[currentPosition];

                if(mListener != null){
                    mListener.touch(CurrentLetter,true);
                }


                //重新绘制
                invalidate();
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        itemHeight = (getHeight() - getPaddingTop()-getPaddingBottom()) / alhpa.length;

        //画26个字母
        for (int i = 0; i < alhpa.length; i++) {
            //知道每个字母的中心位置 alphaCenterY
            //1、字母高度的一半
            //2、字母高度的一半+前面字母的高度
            int alphaCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            //根据中心位置画出基线
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom);
            int baseLine = alphaCenterY + dy;

            //x的绘制在最中间 =宽度/2-文字/2
            int textWidth = (int) mPaint.measureText(alhpa[i]);
            int x = (getWidth()/2 )-  (textWidth / 2);

            //当前字母高亮
            if(alhpa[i].equals(CurrentLetter)){
                mPaint.setColor(Color.BLUE);
                canvas.drawText(alhpa[i], x , baseLine, mPaint);
            }else{
                mPaint.setColor(Color.RED);
                canvas.drawText(alhpa[i], x , baseLine, mPaint);
            }
        }
    }



    private LetterTouchListen mListener;

    public void setOnLetterTouchListen(LetterTouchListen listener){
        this.mListener = listener;
    }

    public interface LetterTouchListen{
        public void touch(CharSequence letter,boolean isTouch);
    }
}
