package as.bwei.com.week1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by HP on 2018/10/8.
 */

public class WaterView extends View{

    private float φ;
    private int color_01,color_02;
    private Paint mPaintTop;
    private Paint mPaintBottom;
    private Path mPathTop,mPathBottom;

    public WaterView(Context context) {
        super(context);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ty = context.obtainStyledAttributes(attrs,R.styleable.WaterView);
        color_01 = ty.getColor(R.styleable.WaterView_color_01,0);
        color_02 = ty.getColor(R.styleable.WaterView_color_02,0);
        ty.recycle();
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaintTop = new Paint();
        mPaintTop.setColor(color_01);
        mPaintTop.setAntiAlias(true);

        mPaintBottom = new Paint();
        mPaintBottom.setColor(color_02);
        mPaintBottom.setAlpha(60);
        mPaintTop.setAntiAlias(true);

        mPathTop = new Path();
        mPathBottom = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathTop.reset();
        mPathBottom.reset();

        mPathTop.moveTo(getLeft(),getBottom());
        mPathBottom.moveTo(getLeft(),getBottom());

        double mY=Math.PI*2/getWidth();

        φ+=0.1f;
         for (float x=0;x<=getWidth();x+=20){
                     float y=(float) (10*Math.cos(mY*x+φ)+10);
                     mPathTop.lineTo(x, y);
                     mPathBottom.lineTo(x, (float) (10*Math.sin(mY*x+φ)));
                     listener.animation(y);
                 }

         mPathTop.lineTo(getRight(),getBottom());
         mPathBottom.lineTo(getRight(),getBottom());
         canvas.drawPath(mPathTop,mPaintTop);
        canvas.drawPath(mPathBottom,mPaintBottom);

        postInvalidateDelayed(20);
    }

    //接口回调
    private AnimationListener listener;

    public void animation(AnimationListener listener){
        this.listener = listener;
    }

    public interface AnimationListener{
        void animation(float y);
    }
}
