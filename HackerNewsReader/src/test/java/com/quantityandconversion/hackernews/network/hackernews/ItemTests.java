package com.quantityandconversion.hackernews.network.hackernews;


import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.hackernews.network.hackernews.internal.TitleTests;
import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ItemTests extends QacTestClass {

    private final static ItemId ITEM_ID_ONE = ItemId.createStoryId(1L);
    private final static ItemId ITEM_ID_TWO = ItemId.createStoryId(2L);
    private final static Title TitleOne = TitleTests.TitleOne;
    private final static Title TitleTwo = TitleTests.TitleTwo;
    private final static Item ItemOne = new Item(ITEM_ID_ONE,
            TitleOne,
            new Author("That_Guy"),
            ItemComments.NullItemComments,
            ItemScore.NullItemScore, PostTime.NullPostTime);
    private final static Item ItemOneDiff = new Item(ITEM_ID_ONE,
            TitleOne,
            new Author("That_Guy"),
            ItemComments.NullItemComments,
            ItemScore.NullItemScore, PostTime.NullPostTime);
    private final static Item ItemTwo = new Item(ITEM_ID_TWO,
            TitleTwo,
            new Author("That_Guy"),
            ItemComments.NullItemComments,
            ItemScore.NullItemScore, PostTime.NullPostTime);

    @Test
    public void constructorThrowsForNullItemId(){
        assertThatThrownBy(() -> new Item(null,
                TitleOne,
                new Author("That_Guy"),
                ItemComments.NullItemComments,
                ItemScore.NullItemScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemId can not be null");
    }
    @Test
    public void constructorThrowsForNullTitle(){
        assertThatThrownBy(() -> new Item(ITEM_ID_ONE,
                null,
                new Author("That_Guy"),
                ItemComments.NullItemComments,
                ItemScore.NullItemScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title can not be null");
    }
    @Test
    public void constructorThrowsForNullAuthor(){
        assertThatThrownBy(() -> new Item(ITEM_ID_ONE,
                TitleOne,
                null,
                ItemComments.NullItemComments,
                ItemScore.NullItemScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("author can not be null");
    }

    @Test
    public void constructorThrowsForNullPostTime(){
        assertThatThrownBy(() -> new Item(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                ItemComments.NullItemComments,
                ItemScore.NullItemScore,
                null))

                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("postTime can not be null");
    }

    @Test
    public void equalsSameInstance(){
        assertThat(ItemOne).isEqualTo(ItemOne);
    }
    @Test
    public void equalsSameValue(){
        assertThat(ItemOne).isEqualTo(ItemOneDiff);
    }
    @Test
    public void equalsDifferentValue(){
        assertThat(ItemOne).isNotEqualTo(ItemTwo);
    }

    @Test
    public void hashSameInstance(){
        assertThat(ItemOne.hashCode()).isEqualTo(ItemOne.hashCode());
    }
    @Test
    public void hashSameValue(){
        assertThat(ItemOne.hashCode()).isEqualTo(ItemOneDiff.hashCode());
    }
    @Test
    public void hashDifferentValue(){
        assertThat(ItemOne.hashCode()).isNotEqualTo(ItemTwo.hashCode());
    }

    @Test
    public void titleInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemOne.titleInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void authorInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemOne.authorInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void postTimeInto() throws InterruptedException{
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemOne.postTimeInto(fakeSetText);
        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void commentCountInto() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemOne.commentCountInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void scoreInto() throws InterruptedException{
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemOne.scoreInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void createJobShouldThrowIllegalArgumentExceptionGivenNonJobId(){
        assertThatThrownBy(() -> Item.createJob(ItemId.NullItemId, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Job must have a job id");
    }

    @Test
    public void createItemShouldThrowIllegalArgumentExceptionGivenNonItemId(){
        assertThatThrownBy(() -> Item.createStory(ItemId.NullItemId, null, null, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Story must have a item id");
    }

    @Test
    public void createItemShouldThrowsForNullItemComments(){
        assertThatThrownBy(() -> Item.createStory(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                null,
                ItemScore.NullItemScore, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemComments can not be null");
    }
    @Test
    public void createItemShouldThrowsForNullItemScore(){
        assertThatThrownBy(() -> Item.createStory(ITEM_ID_ONE,
                TitleOne,
                new Author("That_Guy"),
                ItemComments.NullItemComments,
                null, PostTime.NullPostTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemScore can not be null");
    }
}
