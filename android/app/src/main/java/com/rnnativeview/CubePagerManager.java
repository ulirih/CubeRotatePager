package com.rnnativeview;

import android.view.View;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CubePagerManager extends ViewGroupManager<CubePager> {

    private ThemedReactContext context;
    private CubePager cube;

    @Nonnull
    @Override
    public String getName() {
        return "CubeRotateSwiper";
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Nonnull
    @Override
    protected CubePager createViewInstance(@Nonnull ThemedReactContext reactContext) {
        context = reactContext;
        cube = new CubePager(reactContext);

        return cube;
    }

    @ReactProp(name = "startPage")
    public void startPage(CubePager view, int page) {
        cube.postDelayed(new Runnable() {
            @Override
            public void run() {
                cube.setCurrentItem(page, true);
            }
        }, 100);
    }

    @Override
    public void addView(CubePager parent, View child, int index) {
        parent.addViewToAdapter(child, index);
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onPageSelected", MapBuilder.of("registrationName", "onPageSelected"))
                .build();
    }
}