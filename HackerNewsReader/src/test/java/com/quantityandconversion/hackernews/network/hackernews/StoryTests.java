package com.quantityandconversion.hackernews.network.hackernews;


import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.hackernews.network.hackernews.internal.TitleTests;
import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryTests extends QacTestClass {

    private final static ItemId ITEM_ID_ONE = ItemId.createStoryId(1L);
    private final static ItemId ITEM_ID_TWO = ItemId.createStoryId(2L);
    private final static Title TitleOne = TitleTests.TitleOne;
    private final static Title TitleTwo = TitleTests.TitleTwo;
    private final static Story StoryOne = new Story(ITEM_ID_ONE,
            TitleOne,
            new Author("That_Guy"),
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore, PostTime.NullPostTime);
    private final static Story StoryOneDiff = new Story(ITEM_ID_ONE,
            TitleOne,
            new Author("That_Guy"),
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore, PostTime.NullPostTime);
    private final static Story StoryTwo = new Story(ITEM_ID_TWO,
            TitleTwo,
            new Author("That_Guy"),
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore, PostTime.NullPostTime);

    @Test
    public void constructorThrowsForNullStoryId(){
        assertThatThrownBy(() -> new Story(null,
                TitleOne,
                new Author("That_Guy"),
                StoryComments.NullStoryComments,
                StoryScore.NullStoryScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemId can not be null");
    }
    @Test
    public void constructorThrowsForNullTitle(){
        assertThatThrownBy(() -> new Story(ITEM_ID_ONE,
                null,
                new Author("That_Guy"),
                StoryComments.NullStoryComments,
                StoryScore.NullStoryScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title can not be null");
    }
    @Test
    public void constructorThrowsForNullAuthor(){
        assertThatThrownBy(() -> new Story(ITEM_ID_ONE,
                TitleOne,
                null,
                StoryComments.NullStoryComments,
                StoryScore.NullStoryScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("author can not be null");
    }
    @Test
    public void constructorThrowsForNullStoryComments(){
        assertThatThrownBy(() -> new Story(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                null,
                StoryScore.NullStoryScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("storyComments can not be null");
    }
    @Test
    public void constructorThrowsForNullStoryScore(){
        assertThatThrownBy(() -> new Story(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                StoryComments.NullStoryComments,
                null, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("storyScore can not be null");
    }

    @Test
    public void constructorThrowsForNullPostTime(){
        assertThatThrownBy(() -> new Story(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                StoryComments.NullStoryComments,
                StoryScore.NullStoryScore,
                null))

                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("postTime can not be null");
    }

    @Test
    public void equals(){

        assertThat(StoryOne).isEqualTo(StoryOne);
        assertThat(StoryOne).isEqualTo(StoryOneDiff);
        assertThat(StoryOne).isNotEqualTo(StoryTwo);
    }

    @Test
    public void hash(){
        assertThat(StoryOne.hashCode()).isEqualTo(StoryOne.hashCode());
        assertThat(StoryOne.hashCode()).isEqualTo(StoryOneDiff.hashCode());
        assertThat(StoryOne.hashCode()).isNotEqualTo(StoryTwo.hashCode());
    }

    @Test
    public void titleInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        StoryOne.titleInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void authorInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        StoryOne.authorInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void commentCountInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        StoryOne.commentCountInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void scoreInto() throws InterruptedException{
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        StoryOne.scoreInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void postTimeInto() throws InterruptedException{
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        StoryOne.postTimeInto(fakeSetText);
        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void storyId() throws NoSuchMethodException {
        assertThat(Story.class.getDeclaredMethod("storyId").getModifiers()).isEqualTo(0);
        assertThat(StoryOne.storyId()).isEqualTo(ITEM_ID_ONE);
    }
}
