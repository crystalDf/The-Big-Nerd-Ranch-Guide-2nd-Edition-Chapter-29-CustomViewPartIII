package com.star.customviewpartiii;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyListView extends ListView {

    private GestureDetector mGestureDetector;

    private OnDeleteListener mOnDeleteListener;

    private View mDeleteButton;

    private ViewGroup mItemLayout;

    private int mSelectedItem;

    private boolean mIsDeleteButtonShown;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                if (!mIsDeleteButtonShown) {
                    mSelectedItem = pointToPosition((int) e.getX(), (int) e.getY());
                }
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (!mIsDeleteButtonShown && (Math.abs(velocityX) > Math.abs(velocityY))) {
                    mDeleteButton = LayoutInflater.from(getContext()).inflate(
                            R.layout.delete_button, null);
                    mDeleteButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemLayout.removeView(mDeleteButton);
                            mDeleteButton = null;
                            mIsDeleteButtonShown = false;
                            mOnDeleteListener.onDelete(mSelectedItem);
                        }
                    });

                    mItemLayout = (ViewGroup)
                            getChildAt(mSelectedItem - getFirstVisiblePosition());

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

                    mItemLayout.addView(mDeleteButton, layoutParams);

                    mIsDeleteButtonShown = true;
                }
                return false;
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mIsDeleteButtonShown) {
                    mItemLayout.removeView(mDeleteButton);
                    mDeleteButton = null;
                    mIsDeleteButtonShown = false;
                    return false;
                } else {
                    return mGestureDetector.onTouchEvent(event);
                }
            }
        });
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener {
        void onDelete(int index);
    }
}
