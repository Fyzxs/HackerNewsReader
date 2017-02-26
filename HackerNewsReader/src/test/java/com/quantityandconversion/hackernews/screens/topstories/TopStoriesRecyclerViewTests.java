package com.quantityandconversion.hackernews.screens.topstories;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

public class TopStoriesRecyclerViewTests extends QacTestClass{

    @Mock RecyclerView mockRecyclerView;
    @Mock RecyclerView.Adapter mockAdapter;
    @Mock Context mockContext;
    @Mock TypedArray mockTypedArray;

    @Before
    public void setup(){
        Mockito.when(mockRecyclerView.getContext()).thenReturn(mockContext);
        Mockito.when(mockContext.obtainStyledAttributes(any())).thenReturn(mockTypedArray);
    }

    @Test
    public void ctorShouldThrowIllegalArgumentGivenNullRecyclerView(){
        assertThatThrownBy(() -> new TopStoriesRecyclerView(null, mockAdapter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("recyclerView");
    }
    @Test
    public void ctorShouldThrowIllegalArgumentGivenNullAdapter(){
        assertThatThrownBy(() -> new TopStoriesRecyclerView(mockRecyclerView, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("adapter");
    }

    @Test
    public void ctorShouldTakeRecyclerViewAndAdapter(){
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter);
    }

    @Test
    public void ctorShouldSetRecyclerViewAdapter(){
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter);
        Mockito.verify(mockRecyclerView).setAdapter(mockAdapter);
    }

    @Test
    public void ctorShouldSetLayoutManager(){
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter);

        Mockito.verify(mockRecyclerView).setLayoutManager(any());
    }

    @Test
    public void ctorShouldSetItemDecoration(){
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter);

        Mockito.verify(mockRecyclerView).addItemDecoration(any());
    }

    @Test
    public void notifyTopStoriesChanged(){
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter).notifyTopStoriesChanged();

        Mockito.verify(mockAdapter).notifyDataSetChanged();
    }

    @Test
    public void notifyTopStoryChanged(){
        final int position = RandomValues.nextInt(1000);
        new TopStoriesRecyclerView(mockRecyclerView, mockAdapter).notifyTopStoryChanged(position);

        Mockito.verify(mockAdapter).notifyItemChanged(position);
    }
}
