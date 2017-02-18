package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoryAdapterTests extends QacTestClass {
    @Test
    public void storyFromJson() {

        storyFromJsonThrowsStoryJson();

        storyFromJsonBuildsExpectantly();
    }

    private void storyFromJsonThrowsStoryJson() {
        assertThatThrownBy(() -> new StoryAdapter().storyFromJson(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("can not be null");
    }


    private void storyFromJsonBuildsExpectantly() {
        final String expectedTitle = RandomValues.alphaNumeric(10);

        final StoryJson storyJson = new StoryJson();
        storyJson.id = 1L;
        storyJson.title = expectedTitle;

        final Story expectedStory = new Story(new StoryId(1L), new Title(expectedTitle));

        assertThat(new StoryAdapter().storyFromJson(storyJson)).isEqualTo(expectedStory);
    }
    @Test
    public void storyToJson() {
        assertThatThrownBy(() -> new StoryAdapter().storyToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
