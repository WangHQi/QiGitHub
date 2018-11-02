package wanghuiqi.bawie.com.dalls_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * date:2018-11-02
 * author:王慧琦(琦小妹i)
 * function:
 */
public class Dalls extends View {

    private int mWidth;
    private int mHeight;
    private int mMoveX;
    private int mMoveY;
    private int mDownX;
    private int mDownY;

    public Dalls(Context context) {
        this(context,null);
    }

    public Dalls(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public Dalls(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到小球的宽高
        mWidth = this.getWidth();
        mHeight = this.getHeight();
        //设置小球的宽高
        mMoveX = mWidth / 2;
        mMoveY = mHeight / 2;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        //设置画笔的颜色
        paint.setColor(Color.YELLOW);
        //画出小球
        canvas.drawCircle(mMoveX,mMoveY,20,paint);
    }


    //手势
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = (int) event.getX();
                mMoveY = (int) event.getY();
                //重置小球
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
