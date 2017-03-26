package com.quantityandconversion.hackernews.screens.topitems;

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

@SuppressWarnings("WeakerAccess")
public class TopItemsActivityTests extends QacTestClass {


    @Mock RecyclerView mockRecyclerView;
    @Mock Context mockContext;
    @Mock TypedArray mockTypedArray;
    @Mock TopItemsRecyclerView mockTopItemsRecyclerView;

    @Before
    public void setup(){
        Mockito.when(mockRecyclerView.getContext()).thenReturn(mockContext);
        Mockito.when(mockContext.obtainStyledAttributes(any())).thenReturn(mockTypedArray);
    }

    @Test
    public void ctorShouldHaveEmpty(){
        new TopItemsActivity();
    }

    @Test
    public void ctorShouldTakeBridge(){
        //noinspection RedundantCast - Don't need to mock, but tests the types
        new TopItemsActivity((TopItemsActivityBridge)null, (TopItemsRecyclerView) mockTopItemsRecyclerView);
    }

    @Test
    public void onCreateShouldLoadData(){
        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        final FakeTopItemsActivityBridge fakeTopStoriesActivityBridge = new FakeTopItemsActivityBridge(new FakeTopItemsActivity(), Mockito.mock(TopItemsAdapter.class));
        final TopItemsActivity actualTopItemsActivity = new TopItemsActivity(fakeTopStoriesActivityBridge, mockTopItemsRecyclerView);

        fakeTopStoriesActivityBridge.setActualTopStoriesActivity(actualTopItemsActivity);

        actualTopItemsActivity.loadData();

        Mockito.verify(mockTopItemsRecyclerView).notifyTopStoriesChanged();
    }

    @Test
    public void notifyTopStoriesChangedShouldUpdateAdapter(){
        new TopItemsActivity(null, mockTopItemsRecyclerView).notifyTopStoriesChanged();

        Mockito.verify(mockTopItemsRecyclerView).notifyTopStoriesChanged();
    }

    @Test
    public void notifyTopStoryChangedShouldUpdateAdapter(){
        final int position = RandomValues.nextInt(1000);

        new TopItemsActivity(null, mockTopItemsRecyclerView).notifyTopStoryChanged(position);

        Mockito.verify(mockTopItemsRecyclerView).notifyTopStoryChanged(position);
    }

    @Test
    public void bindViews() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopItemsActivityBridge fakeTopStoriesActivityBridge =
                new FakeTopItemsActivityBridge(new FakeTopItemsActivity(), Mockito.mock(TopItemsAdapter.class));
        final TopItemsActivity actualTopItemsActivity = new TopItemsActivity(
                fakeTopStoriesActivityBridge, mockTopItemsRecyclerView){
            @Override
            public View findViewById(@IdRes int id) {
                if(id == R.id.rv_top_stories){
                    latch.countDown();
                    return mockRecyclerView;
                }
                throw new IllegalArgumentException("Unexpected id requested");
            }
        };

        actualTopItemsActivity.bindViews();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void onCreateFunctionality() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(4);
        final FakeTopItemsActivityBridge fakeTopStoriesActivityBridge =
                new FakeTopItemsActivityBridge(new FakeTopItemsActivity(), Mockito.mock(TopItemsAdapter.class));
        fakeTopStoriesActivityBridge.setCountDownLatch(latch);
        final TopItemsActivity actualTopItemsActivity = new TopItemsActivity(
                fakeTopStoriesActivityBridge, mockTopItemsRecyclerView){
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

        actualTopItemsActivity.onCreateFunctionality();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
