package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.date.DateUtils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoryAdapterTests extends QacTestClass {
    @Test
    public void storyFromJsonThrowsStoryJson() {
        assertThatThrownBy(() -> new StoryAdapter().storyFromJson(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("can not be null");
    }

    @Test
    public void storyFromJsonBuildsExpectantly() {
        final String expectedTitle = RandomValues.alphaNumeric(10);
        final String expectedAuthor = RandomValues.alphaNumeric(10);
        final long expectedId = RandomValues.nextLongAbs();
        final int expectedScore = RandomValues.nextInt(1000);
        final int expectedComments = RandomValues.nextInt(1000);
        final long expectedTime = RandomValues.nextLongAbs();

        final StoryJson storyJson = new StoryJson();
        storyJson.id = expectedId;
        storyJson.title = expectedTitle;
        storyJson.type = "story";
        storyJson.by = expectedAuthor;
        storyJson.score = expectedScore;
        storyJson.time = expectedTime;
        storyJson.descendants = expectedComments;


        final Story expectedStory = new Story(
                new StoryId(expectedId),
                Title.NullTitle,
                Author.NullAuthor,
                StoryComments.NullStoryComments,
                StoryScore.NullStoryScore,
                PostTime.NullPostTime);

        final Story targetStory = new StoryAdapter().storyFromJson(storyJson);

        //id
        assertThat(targetStory).isEqualTo(expectedStory);

        final AsyncFakeSetText item = new AsyncFakeSetText();
        //title
        targetStory.titleInto(item);
        assertThat(item.getText()).isEqualTo(expectedTitle);
        //by
        targetStory.authorInto(item);
        assertThat(item.getText()).isEqualTo("Posted by: " + expectedAuthor);
        //descendants
        targetStory.commentCountInto(item);
        assertThat(item.getText()).isEqualTo(expectedComments + " comments");
        //score
        targetStory.scoreInto(item);
        assertThat(item.getText()).isEqualTo(Integer.toString(expectedScore));

        //time - Since this is time; there's a TEENY change this will fail when the calls cross
        //the second boundary. Small. Deal with it then.
        // Happened - Broke apart object creation to hopefully minimize
        final DateUtils du = new DateUtils();
        targetStory.postTimeInto(item);
        final CharSequence expectedTimeString = du.relativeTimeSpanString(expectedTime*1000);
        assertThat(item.getText()).isEqualTo(expectedTimeString);

    }

    @Test
    public void storyToJson() {
        assertThatThrownBy(() -> new StoryAdapter().storyToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
