package com.rnnativeview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;

import java.util.ArrayList;
import java.util.List;

public class CubePager extends ViewPager {
    public CubePager(@NonNull Context context) {
        super(context);

        this.setPageTransformer(true, new CubePageTransformer());
        this.setAdapter(new CubePagerAdapter());
        this.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                WritableMap event = Arguments.createMap();
                event.putInt("page", i);
                ReactContext reactContext = (ReactContext) getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "onPageSelected",
                        event);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @Override
    public CubePagerAdapter getAdapter() {
        return (CubePagerAdapter) super.getAdapter();
    }

    void addViewToAdapter(View child, int index) {
        getAdapter().addView(child, index);
    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    private class CubePagerAdapter extends PagerAdapter {
        private final List<View> mViews = new ArrayList<>();

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view, 0, generateDefaultLayoutParams());
            post(measureAndLayout);
            return view;
        }

        void addView(View child, int index) {
            mViews.add(index, child);
            notifyDataSetChanged();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class CubePageTransformer implements ViewPager.PageTransformer {

        public void transformPage(View view, float position) {
            int angle = 30;
            int roundradius = 30;

            if (position < -1) {
                view.setAlpha(0);
            } else if (position == 0) {
                ReactViewBackgroundDrawable dr = (ReactViewBackgroundDrawable) view.getBackground();
                dr.setRadius(0);
            } else if (position <= 0) {
                view.setAlpha(1);
                view.setPivotX(view.getWidth());
                view.setPivotY(view.getHeight() / 2);
                view.setRotationY(-angle * Math.abs(position));
                ReactViewBackgroundDrawable dr = (ReactViewBackgroundDrawable) view.getBackground();
                dr.setRadius(roundradius);

            } else if (position <= 1) {
                view.setAlpha(1);
                view.setPivotX(0);
                view.setPivotY(view.getHeight() / 2);
                view.setRotationY(angle * Math.abs(position));
                ReactViewBackgroundDrawable dr = (ReactViewBackgroundDrawable) view.getBackground();
                dr.setRadius(roundradius);

            } else {
                view.setAlpha(0);
            }
        }
    }
}
