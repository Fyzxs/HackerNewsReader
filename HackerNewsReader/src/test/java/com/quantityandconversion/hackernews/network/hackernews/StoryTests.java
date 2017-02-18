package com.quantityandconversion.hackernews.network.hackernews;


import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.hackernews.network.hackernews.internal.TitleTests;
import com.quantityandconversion.test.FakeQacTextView;
import com.quantityandconversion.test.QacTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryTests extends QacTestClass {

    private final static StoryId StoryIdOne = new StoryId(1L);
    private final static StoryId StoryIdTwo = new StoryId(2L);
    private final static Title TitleOne = TitleTests.TitleOne;
    private final static Title TitleTwo = TitleTests.TitleTwo;
    private final static Story StoryOne = new Story(StoryIdOne, TitleOne, new Author("That_Guy"));
    private final static Story StoryOneDiff = new Story(StoryIdOne, TitleOne,new Author("That_Guy"));
    private final static Story StoryTwo = new Story(StoryIdTwo, TitleTwo, new Author("That_Guy"));

    @Test
    public void constructor(){
        assertThatThrownBy(() -> new Story(StoryIdOne, null, new Author("That_Guy") ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title can not be null");
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
    public void title() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final FakeQacTextView fakeSetText = new FakeQacTextView(latch);
        StoryOne.title(fakeSetText);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
    @Test
    public void storyId() throws NoSuchMethodException {
        assertThat(Story.class.getDeclaredMethod("storyId").getModifiers()).isEqualTo(0);
        assertThat(StoryOne.storyId()).isEqualTo(StoryIdOne);
    }
}
