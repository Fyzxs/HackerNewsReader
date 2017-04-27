package com.quantityandconversion.hackernews.screens.topitems;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.internal.JobBuilder;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryBuilder;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.Strings;
import com.quantityandconversion.widget.QacTextView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        //Arrange
        final StoryBuilder storyBuilder = createStoryBuilder();
        final OnBindViewHolderValidator validator = OnBindViewHolderValidator.arrange(storyBuilder.buildStory());

        //Act
        validator.act(validator::clickContainer);

        //Assert
        validator.assertStory(storyBuilder, () -> Mockito.verify(mockToast).show());
    }

    @Test
    public void onBindViewHolderShouldSetStory(){
        //Arrange
        final StoryBuilder storyBuilder = createStoryBuilder();
        final OnBindViewHolderValidator validator = OnBindViewHolderValidator.arrange(storyBuilder.buildStory());

        //Act
        validator.act(null);

        //Assert
        validator.assertStory(storyBuilder, null);
    }
    @Test
    public void onBindViewHolderShouldSetJob(){
        //Arrange
        final JobBuilder jobBuilder = CreateJobBuilder();
        final OnBindViewHolderValidator validator = OnBindViewHolderValidator.arrange(jobBuilder.buildJob());

        //Act
        validator.act(null);

        //Assert
        validator.assertJob(jobBuilder);
    }
    private static class OnBindViewHolderValidator{

        private OnBindViewHolderValidator(){
        }

        public static OnBindViewHolderValidator arrange(final Item item){
            OnBindViewHolderValidator validator = new OnBindViewHolderValidator();

            validator.topItemsAdapter = createTopItemsAdapter(validator.position, item);

            return validator;
        }

        private final View mockView = Mockito.mock(View.class);

        private final ArgumentCaptor<View.OnClickListener> containerCaptor =
                configureItemContainer(mockView, R.id.ll_item_container);
        private final ArgumentCaptor<CharSequence> titleCaptor =
                configureViewQacTextView(mockView, R.id.tv_title);
        private final ArgumentCaptor<CharSequence> scoreCaptor =
                configureViewQacTextView(mockView, R.id.tv_score_value);
        private final ArgumentCaptor<CharSequence> authorCaptor =
                configureViewQacTextView(mockView, R.id.tv_posted_by);
        private final ArgumentCaptor<CharSequence> commentsCaptor =
                configureViewQacTextView(mockView, R.id.tv_comments);
        private final ArgumentCaptor<CharSequence> postedTimeCaptor =
                configureViewQacTextView(mockView, R.id.tv_posted_time);

        private final int position = RandomValues.nextInt(1000);

        private TopItemsAdapter topItemsAdapter;

        public void clickContainer(){
            containerCaptor.getValue().onClick(mockView);
        }

        public void act(final Runnable postAct){
            topItemsAdapter.onBindViewHolder(new TopItemsAdapter.ViewHolder(mockView), position);

            if(postAct == null) return;
            postAct.run();
        }

        public void assertStory(final StoryBuilder storyBuilder, final Runnable postAssert){
            assertThat(titleCaptor.getValue()).isEqualTo(storyBuilder.title());
            assertThat(authorCaptor.getValue()).isEqualTo("Posted by: " + storyBuilder.author());
            assertThat(commentsCaptor.getValue()).isEqualTo(Long.toString(storyBuilder.commentCount()) + " comments");
            assertThat(scoreCaptor.getValue()).isEqualTo(Long.toString(storyBuilder.storyScore()));
            assertThat(postedTimeCaptor.getValue()).endsWith(" seconds ago - only");

            if(postAssert == null) return;
            postAssert.run();
        }

        public void assertJob(final JobBuilder jobBuilder){
            assertThat(containerCaptor.getValue()).isNotNull();
            assertThat(titleCaptor.getValue()).isEqualTo(jobBuilder.title());
            assertThat(authorCaptor.getValue()).isEqualTo("Posted by: " + jobBuilder.author());
            assertThat(commentsCaptor.getValue()).isEqualTo(Strings.Empty);
            assertThat(scoreCaptor.getValue()).isEqualTo(Strings.Empty);
            assertThat(postedTimeCaptor.getValue()).endsWith(" seconds ago - only");

        }
        private static TopItemsAdapter createTopItemsAdapter(final int position, final Item item) {
            final TopItemsActivityMediator mockTopItemsActivityMediator = Mockito.mock(TopItemsActivityMediator.class);
            Mockito.when(mockTopItemsActivityMediator.itemAt(position)).thenReturn(item);
            return new TopItemsAdapter(mockTopItemsActivityMediator);
        }

        private ArgumentCaptor<CharSequence> configureViewQacTextView(final View mockView, final int resId) {
            final ArgumentCaptor<CharSequence> captor = ArgumentCaptor.forClass(CharSequence.class);
            final QacTextView qacTextView = Mockito.mock(QacTextView.class);
            Mockito.doNothing().when(qacTextView).setText(captor.capture());
            Mockito.when(mockView.findViewById(resId)).thenReturn(qacTextView);
            return captor;
        }

        private ArgumentCaptor<View.OnClickListener> configureItemContainer(final View mockView, final int resId) {
            final ArgumentCaptor<View.OnClickListener> captor = ArgumentCaptor.forClass(View.OnClickListener.class);
            final LinearLayout container = Mockito.mock(LinearLayout.class);
            Mockito.doNothing().when(container).setOnClickListener(captor.capture());
            Mockito.when(mockView.findViewById(resId)).thenReturn(container);
            return captor;
        }

    }

    private StoryBuilder createStoryBuilder() {

        final String titleExpected = RandomValues.alphaNumeric(50);
        final String scoreExpected = Integer.toString(RandomValues.nextInt(1000));
        final String authorExpected = RandomValues.alphaNumeric(50);
        final long commentsExpected = RandomValues.nextInt(1000);
        final String postedTimeExpected = Integer.toString(RandomValues.nextInt(1000));

        return new StoryBuilder()
                .setTitle(titleExpected)
                .setAuthor(authorExpected)
                .setCommentCount(commentsExpected)
                .setScore(Integer.parseInt(scoreExpected))
                .setPostTime(Integer.parseInt(postedTimeExpected));
    }

    private JobBuilder CreateJobBuilder() {

        final String titleExpected = RandomValues.alphaNumeric(50);
        final String authorExpected = RandomValues.alphaNumeric(50);
        final String postedTimeExpected = Integer.toString(RandomValues.nextInt(1000));

        return new JobBuilder()
                .setTitle(titleExpected)
                .setAuthor(authorExpected)
                .setPostTime(Integer.parseInt(postedTimeExpected));
    }

}
