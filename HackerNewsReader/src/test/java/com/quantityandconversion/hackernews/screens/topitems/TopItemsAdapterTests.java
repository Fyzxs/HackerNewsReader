package com.quantityandconversion.hackernews.screens.topitems;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.internal.JobBuilder;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryBuilder;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.Strings;
import com.quantityandconversion.utils.toast.Toaster;
import com.quantityandconversion.widget.QacTextView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;

public class TopItemsAdapterTests extends QacTestClass {

    @Test
    public void onCreateViewHolderThrowsBecauseStub(){
        final TopItemsActivityMediator fake = new TopItemsActivityMediator(new FakeTopItemsActivityBridge(new FakeTopItemsActivity(), Mockito.mock(TopItemsAdapter.class)));
        assertThatThrownBy(() -> new TopItemsAdapter(fake).onCreateViewHolder(Mockito.mock(ViewGroup.class), 0))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Method from in android.view.LayoutInflater not mocked. See http://g.co/androidstudio/not-mocked for details.");
    }

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new TopItemsAdapter(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topItemsActivityMediator");
    }


    @Test
    public void getItemCount(){
        final int val = RandomValues.nextInt(100);
        final TopItemsActivityMediator fake = new TopItemsActivityMediator(new FakeTopItemsActivityBridge(new FakeTopItemsActivity(), Mockito.mock(TopItemsAdapter.class))){
            @Override
            int topStoriesSize() {
                return val;
            }
        };
        assertThat(new TopItemsAdapter(fake).getItemCount()).isEqualTo(val);
    }

    @Test
    public void onBindViewHolderShouldConfigureViewHolderContainerOnClickListener(){

        final int position = RandomValues.nextInt(1000);

        final ArgumentCaptor<View.OnClickListener> containerCaptor = ArgumentCaptor.forClass(View.OnClickListener.class);

        final String titleExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> titleCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String scoreExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> scoreCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String authorExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> authorCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final long commentsExpected = RandomValues.nextInt(1000);
        final ArgumentCaptor<CharSequence> commentsCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String postedTimeExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> postedTimeCaptor = ArgumentCaptor.forClass(CharSequence.class);


        //Prep View
        final View mockView = Mockito.mock(View.class);
        {
            {
                final LinearLayout container = Mockito.mock(LinearLayout.class);
                Mockito.doNothing().when(container).setOnClickListener(containerCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.ll_item_container)).thenReturn(container);
            }
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
        final TopItemsAdapter.ViewHolder viewHolder = new TopItemsAdapter.ViewHolder(mockView);
        //>
        final TopItemsActivityMediator mockTopItemsActivityMediator = Mockito.mock(TopItemsActivityMediator.class);
        Mockito.when(mockTopItemsActivityMediator.itemAt(position)).thenReturn(
                new StoryBuilder()
                        .setTitle(titleExpected)
                        .setAuthor(authorExpected)
                        .setCommentCount(commentsExpected)
                        .setScore(Integer.parseInt(scoreExpected))
                        .setPostTime(Integer.parseInt(postedTimeExpected))
                        .buildStory());
        //>
        final TopItemsAdapter topItemsAdapter = new TopItemsAdapter(mockTopItemsActivityMediator);
        //END Prep Adapter


        topItemsAdapter.onBindViewHolder(viewHolder, position);

        Toast mockToast = Mockito.mock(Toast.class);
        Toaster.setReplacementToast(mockToast);
        assertThat(containerCaptor.getValue()).isNotNull();
        Mockito.when(mockView.getContext()).thenReturn(Mockito.mock(Context.class));

        //Act
        containerCaptor.getValue().onClick(mockView);

        //Assert
        Mockito.verify(mockToast).show();
    }

    @Test
    public void onBindViewHolderShouldSetStory(){
        final int position = RandomValues.nextInt(1000);

        final ArgumentCaptor<View.OnClickListener> containerCaptor = ArgumentCaptor.forClass(View.OnClickListener.class);

        final String titleExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> titleCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String scoreExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> scoreCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String authorExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> authorCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final long commentsExpected = RandomValues.nextInt(1000);
        final ArgumentCaptor<CharSequence> commentsCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String postedTimeExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> postedTimeCaptor = ArgumentCaptor.forClass(CharSequence.class);


        //Prep View
        final View mockView = Mockito.mock(View.class);
        {
            {
                final LinearLayout container = Mockito.mock(LinearLayout.class);
                Mockito.doNothing().when(container).setOnClickListener(containerCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.ll_item_container)).thenReturn(container);
            }
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
        final TopItemsAdapter.ViewHolder viewHolder = new TopItemsAdapter.ViewHolder(mockView);
        //>
        final TopItemsActivityMediator mockTopItemsActivityMediator = Mockito.mock(TopItemsActivityMediator.class);
        Mockito.when(mockTopItemsActivityMediator.itemAt(position)).thenReturn(
                new StoryBuilder()
                        .setTitle(titleExpected)
                        .setAuthor(authorExpected)
                        .setCommentCount(commentsExpected)
                        .setScore(Integer.parseInt(scoreExpected))
                        .setPostTime(Integer.parseInt(postedTimeExpected))
                        .buildStory());
        //>
        final TopItemsAdapter topItemsAdapter = new TopItemsAdapter(mockTopItemsActivityMediator);
        //END Prep Adapter


        //Act
        topItemsAdapter.onBindViewHolder(viewHolder, position);

        //Assert
        Toast mockToast = Mockito.mock(Toast.class);
        Toaster.setReplacementToast(mockToast);

        assertThat(containerCaptor.getValue()).isNotNull();
        containerCaptor.getValue().onClick(Mockito.mock(View.class));
        assertThat(titleCaptor.getValue()).isEqualTo(titleExpected);
        assertThat(authorCaptor.getValue()).isEqualTo("Posted by: " + authorExpected);
        assertThat(commentsCaptor.getValue()).isEqualTo(Long.toString(commentsExpected) + " comments");
        assertThat(scoreCaptor.getValue()).isEqualTo(scoreExpected);
        assertThat(postedTimeCaptor.getValue()).endsWith(" seconds ago - only");
    }

    @Test
    public void onBindViewHolderShouldSetJob(){

        final int position = RandomValues.nextInt(1000);

        final ArgumentCaptor<View.OnClickListener> containerCaptor = ArgumentCaptor.forClass(View.OnClickListener.class);


        final String titleExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> titleCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final ArgumentCaptor<CharSequence> scoreCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String authorExpected = RandomValues.alphaNumeric(50);
        final ArgumentCaptor<CharSequence> authorCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final ArgumentCaptor<CharSequence> commentsCaptor = ArgumentCaptor.forClass(CharSequence.class);

        final String postedTimeExpected = Integer.toString(RandomValues.nextInt(1000));
        final ArgumentCaptor<CharSequence> postedTimeCaptor = ArgumentCaptor.forClass(CharSequence.class);

        //Prep View
        final View mockView = Mockito.mock(View.class);
        {
            {
                final LinearLayout container = Mockito.mock(LinearLayout.class);
                Mockito.doNothing().when(container).setOnClickListener(containerCaptor.capture());
                Mockito.when(mockView.findViewById(R.id.ll_item_container)).thenReturn(container);
            }
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
        final TopItemsAdapter.ViewHolder viewHolder = new TopItemsAdapter.ViewHolder(mockView);
        //>
        final TopItemsActivityMediator mockTopItemsActivityMediator = Mockito.mock(TopItemsActivityMediator.class);
        Mockito.when(mockTopItemsActivityMediator.itemAt(position)).thenReturn(
                new JobBuilder()
                        .setTitle(titleExpected)
                        .setAuthor(authorExpected)
                        .setPostTime(Integer.parseInt(postedTimeExpected))
                        .buildJob());
        //>
        final TopItemsAdapter topItemsAdapter = new TopItemsAdapter(mockTopItemsActivityMediator);
        //END Prep Adapter


        //Act
        topItemsAdapter.onBindViewHolder(viewHolder, position);

        //Assert
        assertThat(containerCaptor.getValue()).isNotNull();
        assertThat(titleCaptor.getValue()).isEqualTo(titleExpected);
        assertThat(authorCaptor.getValue()).isEqualTo("Posted by: " + authorExpected);
        assertThat(commentsCaptor.getValue()).isEqualTo(Strings.Empty);
        assertThat(scoreCaptor.getValue()).isEqualTo(Strings.Empty);
        assertThat(postedTimeCaptor.getValue()).endsWith(" seconds ago - only");
    }

}
