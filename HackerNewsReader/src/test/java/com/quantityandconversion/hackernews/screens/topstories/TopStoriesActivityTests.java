package com.quantityandconversion.hackernews.screens.topstories;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class TopStoriesActivityTests extends QacTestClass {


    @Mock RecyclerView mockRecyclerView;
    @Mock RecyclerView.Adapter mockAdapter;
    @Mock Context mockContext;
    @Mock TypedArray mockTypedArray;
    @Mock TopStoriesRecyclerView mockTopStoriesRecyclerView;

    @Before
    public void setup(){
        Mockito.when(mockRecyclerView.getContext()).thenReturn(mockContext);
        Mockito.when(mockContext.obtainStyledAttributes(any())).thenReturn(mockTypedArray);
    }

    @Test
    public void ctorShouldHaveEmpty(){
        new TopStoriesActivity();
    }

    @Test
    public void ctorShouldTakeBridge(){
        //noinspection RedundantCast - Don't need to mock, but tests the types
        new TopStoriesActivity((TopStoriesActivityBridge)null, (TopStoriesRecyclerView)mockTopStoriesRecyclerView);
    }

    @Test
    public void onCreateShouldLoadData(){
        final FakeTopStoriesActivityBridge fakeTopStoriesActivityBridge = new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(), Mockito.mock(TopItemsAdapter.class));
        final TopStoriesActivity actualTopStoriesActivity = new TopStoriesActivity(fakeTopStoriesActivityBridge, mockTopStoriesRecyclerView);

        fakeTopStoriesActivityBridge.setActualTopStoriesActivity(actualTopStoriesActivity);

        actualTopStoriesActivity.loadData();

        Mockito.verify(mockTopStoriesRecyclerView).notifyTopStoriesChanged();
    }

    @Test
    public void notifyTopStoriesChangedShouldUpdateAdapter(){
        new TopStoriesActivity(null, mockTopStoriesRecyclerView).notifyTopStoriesChanged();

        Mockito.verify(mockTopStoriesRecyclerView).notifyTopStoriesChanged();
    }

    @Test
    public void notifyTopStoryChangedShouldUpdateAdapter(){
        final int position = RandomValues.nextInt(1000);

        new TopStoriesActivity(null, mockTopStoriesRecyclerView).notifyTopStoryChanged(position);

        Mockito.verify(mockTopStoriesRecyclerView).notifyTopStoryChanged(position);
    }

    @Test
    public void bindViews() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopStoriesActivityBridge fakeTopStoriesActivityBridge =
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(), Mockito.mock(TopItemsAdapter.class));
        final TopStoriesActivity actualTopStoriesActivity = new TopStoriesActivity(
                fakeTopStoriesActivityBridge, mockTopStoriesRecyclerView){
            @Override
            public View findViewById(@IdRes int id) {
                if(id == R.id.rv_top_stories){
                    latch.countDown();
                    return mockRecyclerView;
                }
                throw new IllegalArgumentException("Unexpected id requested");
            }
        };

        actualTopStoriesActivity.bindViews();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void onCreateFunctionality() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(4);
        final FakeTopStoriesActivityBridge fakeTopStoriesActivityBridge =
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(), Mockito.mock(TopItemsAdapter.class));
        fakeTopStoriesActivityBridge.setCountDownLatch(latch);
        final TopStoriesActivity actualTopStoriesActivity = new TopStoriesActivity(
                fakeTopStoriesActivityBridge, mockTopStoriesRecyclerView){
            @Override
            public void setContentView(@LayoutRes int id) {
                if(id == R.layout.top_stories_activity){
                    latch.countDown();
                    return;
                }
                throw new IllegalArgumentException("Unexpected id requested");
            }
            @Override
            public View findViewById(@IdRes int id) {
                if(id == R.id.rv_top_stories){
                    latch.countDown();
                    return mockRecyclerView;
                }
                throw new IllegalArgumentException("Unexpected id requested");
            }
        };

        actualTopStoriesActivity.onCreateFunctionality();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
