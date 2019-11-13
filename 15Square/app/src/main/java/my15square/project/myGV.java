package my15square.project;
import android.os.Build;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;
import android.annotation.TargetApi;


/**
 * @author: Gianni Magliana
 * @description: creates a gridView that looks for touches and drag actions
 * @date: 11/10/19
 * @bugs: none are known
 */
/**
 * https://developer.android.com/reference/android/widget/GridView
 * Everything on how to use GridLayout
 *
 */
public class myGV extends GridView {

    private GestureDetector touchSense;
    private float touchX;//x location of event
    private float touchY;//y location of event
    private boolean touchConfirmed = false;//boolean for the validity of a touch
    private static final int minDrag = 100;//minimum distance touch must be dragged to take effect
    private static final int maxOffPath = 100;//maximum a drag can stray from path
    private static final int dragSpeed = 100;//minimum speed of dragged finger

    public myGV(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public myGV(Context context) {
        super(context);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public myGV(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }


    public myGV(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initialize(context);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        touchSense.onTouchEvent(event);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            touchConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            touchX = event.getX();
            touchY = event.getY();
        } else {
            if (touchConfirmed) {
                return true;
            }


            float xDiff = (Math.abs(event.getX() - touchX));
            float yDiff = (Math.abs(event.getY() - touchY));
            if ((xDiff > minDrag) || (yDiff > minDrag)) {
                touchConfirmed = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return touchSense.onTouchEvent(event);
    }

    private void initialize(final Context context) {
        touchSense = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float speedX, float speedY) {
                final int position = myGV.this.pointToPosition(Math.round(event1.getX()), Math.round(event1.getY()));

                if (Math.abs(event1.getY() - event2.getY()) > maxOffPath) {
                    if (Math.abs(event1.getX() - event2.getX()) > maxOffPath || Math.abs(speedY) < dragSpeed) {
                        return false;
                    }
                    if (event1.getY() - event2.getY() > minDrag) {
                        MainActivity.moveTiles(context, MainActivity.up, position);
                    }
                    else if (event2.getY() - event1.getY() > minDrag) {
                        MainActivity.moveTiles(context, MainActivity.down, position);
                    }
                } else {
                    if (Math.abs(speedX) < dragSpeed) {
                        return false;
                    }
                    if (event1.getX() - event2.getX() > minDrag) {
                        MainActivity.moveTiles(context, MainActivity.left, position);
                    } else if (event2.getX() - event1.getX() > minDrag) {
                        MainActivity.moveTiles(context, MainActivity.right, position);
                    }
                }

                return super.onFling(event1, event2, speedX, speedY);
            }


            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }
        });
    }

}


