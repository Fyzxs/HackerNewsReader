package com.quantityandconversion.hackernews.screens.topstories;

import android.view.View;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.widget.QacTextView;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;

public class TopStoriesAdapterViewHolderTests {
    @Test
    public void ctor(){
        final QacTextView mockQacTextView = Mockito.mock(QacTextView.class);
        final View mockView = Mockito.mock(View.class);
        Mockito.when(mockView.findViewById(anyInt())).thenReturn(mockQacTextView);

        final TopStoriesAdapter.ViewHolder viewHolder = new TopStoriesAdapter.ViewHolder(mockView);

        Mockito.verify(mockView).findViewById(R.id.tv_title);
        Mockito.verify(mockView).findViewById(R.id.tv_score_value);
        Mockito.verify(mockView).findViewById(R.id.tv_posted_by);
        Mockito.verify(mockView).findViewById(R.id.tv_comments);
        Mockito.verify(mockView).findViewById(R.id.tv_posted_time);
        Mockito.verifyNoMoreInteractions(mockView);

    }
}
