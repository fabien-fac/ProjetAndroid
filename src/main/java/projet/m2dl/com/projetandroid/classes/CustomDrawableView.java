package projet.m2dl.com.projetandroid.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import projet.m2dl.com.projetandroid.PictureActivity;

/**
 * Created by fabien on 15/01/15.
 */
public class CustomDrawableView extends View implements View.OnTouchListener{
    private ShapeDrawable mDrawable;
    private float pos_y = -1;
    private float pos_x = -1;

    public CustomDrawableView(Context context) {
        super(context);

        this.setOnTouchListener(this);
    }

    public CustomDrawableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.setOnTouchListener(this);
    }

    public CustomDrawableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(pos_x != -1 && pos_y != -1){
            Paint p = new Paint();
            // smooths
            p.setAntiAlias(true);
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(7.5f);
            // opacity
            //p.setAlpha(0x80); //
            canvas.drawCircle(pos_x, pos_y, 100, p);
            System.out.println("draw");
        }
        else{
            System.out.println("not draw");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder msgTouchBuilder = new AlertDialog.Builder(getContext());
            pos_x = event.getX();
            pos_y = event.getY();
            PictureActivity activity = (PictureActivity) getContext();
            activity.setCoordinatesInterest(pos_x, pos_y);
            invalidate();
        }
        return true;
    }
}
