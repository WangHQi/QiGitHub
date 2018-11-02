package wanghuiqi.bawie.com.drag_balls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * date:2018-11-01
 * author:王慧琦(琦小妹i)
 * function:
 */
public class DrawerView extends View {

    private int mWidth;
    private int mHeight;
    private int mMoveX;
    private int mMoveY;
    private Boolean mOnBall;
    private int mRadius=20;

    public DrawerView(Context context) {
        this(context, null);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到小球的宽高,默认全屏
        mWidth = this.getWidth();
        mHeight = this.getHeight();
        //设置小球的位置
        mMoveX = mWidth / 2;
        mMoveY = mHeight / 2;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.RED);
        //画出小球,参数(X,Y坐标,半径)
        canvas.drawCircle(mMoveX, mMoveY, mRadius, paint);
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //得到当前的坐标
                float downX = event.getX();
                float downY = event.getY();
                //判断鼠标是不是在球上,点击任意位置,小球就会过去
                mOnBall = isOnBall(downX, downY);
                Toast.makeText(getContext(), "用户的手指是否点到园内" + mOnBall,
                        Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                //用户点到园内时,圆才能动
                if (mOnBall) {
                    mMoveX = (int) event.getX();
                    mMoveY = (int) event.getY();
                }
                //重新调用onDraw
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    //判断用户的手指是否在园内
    private Boolean isOnBall(float downX, float downY) {
        //勾股定理的绝对值
        double sqrt = Math.sqrt((downX - mMoveX) * (downX - mMoveX)
                + ((downY - mMoveY) * (downY - mMoveY)));
        //判断绝对值是否小于等于半径
        if (sqrt <= mRadius) {
            return true;
        }
        return false;
    }


}
