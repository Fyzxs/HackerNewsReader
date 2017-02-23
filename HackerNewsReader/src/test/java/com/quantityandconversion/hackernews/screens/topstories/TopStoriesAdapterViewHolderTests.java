package com.quantityandconversion.hackernews.screens.topstories;

import android.view.View;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.widget.QacTextView;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;

public class TopStoriesAdapterViewHolderTests extends QacTestClass{
    @Mock QacTextView mockQacTextView;
    @Mock View mockView;

    @Test
    public void ctor(){
        Mockito.when(mockView.findViewById(anyInt())).thenReturn(mockQacTextView);

        new TopStoriesAdapter.ViewHolder(mockView);

        Mockito.verify(mockView).findViewById(R.id.tv_title);
        Mockito.verify(mockView).findViewById(R.id.tv_score_value);
        Mockito.verify(mockView).findViewById(R.id.tv_posted_by);
        Mockito.verify(mockView).findViewById(R.id.tv_comments);
        Mockito.verify(mockView).findViewById(R.id.tv_posted_time);
        Mockito.verifyNoMoreInteractions(mockView);

    }
}
