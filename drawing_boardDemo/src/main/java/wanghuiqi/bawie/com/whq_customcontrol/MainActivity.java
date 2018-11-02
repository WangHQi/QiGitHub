package wanghuiqi.bawie.com.whq_customcontrol;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Canvas mCanvas;
    private Paint mPaint;
    private ImageView mImage;
    //按下
    private int mStartY;
    private int mStartX;
    //移动
    private int mMoveY;
    private int mMoveX;
    //抬起
    private int mEndY;
    private int mEndX;
    private FileOutputStream mFos;
    private Bitmap mBmCopy;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = findViewById(R.id.image);
        //加载原图
         Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //创建白纸,设置宽高图片
        mBmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        //创建画板
        mCanvas = new Canvas(mBmCopy);
        //创建画笔
        mPaint = new Paint();
        //在纸上画画,白 板在纸上
        mCanvas.drawBitmap(bmSrc, new Matrix(), mPaint);
        //手势识别器和画笔的结合
        mImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ((event.getAction())) {
                    case MotionEvent.ACTION_DOWN://按下执行
                        //获取用户按下时的坐标
                        mStartX = (int) event.getX();
                        mStartY = (int) event.getY();

                        break;
                    case MotionEvent.ACTION_MOVE://移动执行
                        //获取用户移动的坐标
                        mMoveX = (int) event.getX();
                        mMoveY = (int) event.getY();

                        mCanvas.drawLine(mStartX, mStartY, mMoveX, mMoveY, mPaint);
                        mStartX = mMoveX;
                        mStartY = mMoveY;
                        mImage.setImageBitmap(mBmCopy);
                        break;
                    case MotionEvent.ACTION_UP://抬起执行
                        break;
                }
                return true;
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.red:
                mPaint.setColor(Color.RED);
                break;
            case R.id.green:
                mPaint.setColor(Color.GREEN);
                break;
            case R.id.brush:
                mPaint.setStrokeWidth(20);
                break;
            case R.id.save://保存
                //创建文件夹,需要写入内存的权限
                File file = new File("/sdcard/whq");
                //判断文件是否为空
                if (!file.exists()){
                    //为空创建
                    file.mkdir();
                    //创建输出流,将文件的路径和照片的命名
                    try {
                        mFos = new FileOutputStream(file.getPath() + "/1.png");
                        mBmCopy.compress(Bitmap.CompressFormat.PNG,100,mFos);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        //关闭流
                        try {
                            mFos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

}
