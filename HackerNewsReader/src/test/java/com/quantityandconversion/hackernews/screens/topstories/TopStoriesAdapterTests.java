package com.quantityandconversion.hackernews.screens.topstories;

import android.view.View;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryBuilder;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.widget.QacTextView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesAdapterTests {

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new TopStoriesAdapter(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityMediator");
    }


    @Test
    public void getItemCount(){
        final int val = RandomValues.nextInt(100);
        final TopStoriesActivityMediator fake = new TopStoriesActivityMediator(new FakeTopStoriesActivityBridge(null, new FakeTopStoriesActivity(null))){
            @Override
            int topStoriesSize() {
                return val;
            }
        };
        assertThat(new TopStoriesAdapter(fake).getItemCount()).isEqualTo(val);
    }


    @Test
    public void onBindViewHolder(){

        final int position = RandomValues.nextInt(1000);

        final String titleExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> titleCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String scoreExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> scoreCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String authorExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> authorCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final long commentsExpected = RandomValues.nextInt(1000);
        final ArgumentCaptor<CharSequence> commentsCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String postedTimeExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> postedTimeCaptor = ArgumentCaptor.forClass(CharSequence.class);

        //Prep View
        final View mockView = Mockito.mock(View.class);
        {
            {
                final QacTextView commentsView = Mockito.mock(QacTextView.class);
                Mockito.doNothing().when(commentsView).setText(commentsCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.tv_comments)).thenReturn(commentsView);
            }
            {
                final QacTextView postedTimeView = Mockito.mock(QacTextView.class);
                Mockito.doNothing().when(postedTimeView).setText(postedTimeCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.tv_posted_time)).thenReturn(postedTimeView);
            }
            {
                final QacTextView titleView = Mockito.mock(QacTextView.class);
                Mockito.doNothing().when(titleView).setText(titleCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.tv_title)).thenReturn(titleView);
            }
            {
                final QacTextView scoreView = Mockito.mock(QacTextView.class);
                Mockito.doNothing().when(scoreView).setText(scoreCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.tv_score_value)).thenReturn(scoreView);
            }
            {
                final QacTextView authorView = Mockito.mock(QacTextView.class);
                Mockito.doNothing().when(authorView).setText(authorCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.tv_posted_by)).thenReturn(authorView);
            }
        }
        //Prep View
        
        //BEG Prep Adapter
        final TopStoriesAdapter.ViewHolder viewHolder = new TopStoriesAdapter.ViewHolder(mockView);
        //>
        final TopStoriesActivityMediator mockTopStoriesActivityMediator = Mockito.mock(TopStoriesActivityMediator.class);
        Mockito.when(mockTopStoriesActivityMediator.storyAt(position)).thenReturn(
                new StoryBuilder()
                        .setTitle(titleExpected)
                        .setAuthor(authorExpected)
                        .setCommentCount(commentsExpected)
                        .buildStory());
        //>
        final TopStoriesAdapter topStoriesAdapter = new TopStoriesAdapter(mockTopStoriesActivityMediator);
        //END Prep Adapter

        
        //Act
        topStoriesAdapter.onBindViewHolder(viewHolder, position);
        
        //Assert
        assertThat(titleCaptor.getValue()).isEqualTo(titleExpected);
        assertThat(authorCaptor.getValue()).isEqualTo(authorExpected);
        assertThat(commentsCaptor.getValue()).isEqualTo(Long.toString(commentsExpected) + " comments");
//        assertThat(scoreCaptor.getValue()).isEqualTo(scoreExpected);
//        assertThat(postedTimeCaptor.getValue()).isEqualTo(postedTimeExpected);
    }

}
