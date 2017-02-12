package com.quantityandconversion.hackernews.network.hackernews;


import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryTests extends QacTestClass {

    private final static StoryId StoryIdOne = new StoryId(1L);
    private final static StoryId StoryIdTwo = new StoryId(2L);

    @Test
    public void constructor(){
        assertThatThrownBy(() -> new Story(StoryIdOne, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title cannot be null");
    }

    @Test
    public void equals(){

        final Story same = new Story(StoryIdOne, RandomValues.alphaNumeric(10));
        assertThat(same).isEqualTo(same);
        final String title = RandomValues.alphaNumeric(10);
        assertThat(new Story(StoryIdOne, title)).isEqualTo(new Story(StoryIdOne, title));
        assertThat(new Story(StoryIdOne, title))
                .isNotEqualTo(new Story(StoryIdTwo, title));
    }


    @Test
    public void hash(){
        final String title = RandomValues.alphaNumeric(10);
        assertThat(new Story(StoryIdOne, title).hashCode()).isEqualTo(new Story(StoryIdOne, title).hashCode());
        assertThat(new Story(StoryIdOne, title).hashCode())
                .isNotEqualTo(new Story(StoryIdTwo, title).hashCode());
    }

    @Test
    public void title(){
        final Scanner titleScanner = new Story(StoryIdOne, "Some Title").title();
        assertThat(titleScanner.nextLine()).isEqualTo("Some Title");
    }

    @Test
    public void storyId() throws NoSuchMethodException {
        assertThat(Story.class.getDeclaredMethod("storyId").getModifiers()).isEqualTo(0);

        final String title = RandomValues.alphaNumeric(10);
        assertThat(new Story(StoryIdOne, title).storyId()).isEqualTo(StoryIdOne);
    }
}
