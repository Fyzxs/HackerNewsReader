package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.date.DateUtils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemAdapterTests extends QacTestClass {
    @Test
    public void storyFromJsonThrowsStoryJson() {
        assertThatThrownBy(() -> new ItemAdapter().itemFromJson(null))
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

        final ItemJson itemJson = new ItemJson();
        itemJson.id = expectedId;
        itemJson.title = expectedTitle;
        itemJson.type = "story";
        itemJson.by = expectedAuthor;
        itemJson.score = expectedScore;
        itemJson.time = expectedTime;
        itemJson.descendants = expectedComments;


        final Item expectedStory = Item.createStory(
                ItemId.createStoryId(expectedId),
                Title.NullTitle,
                Author.NullAuthor,
                ItemComments.NullItemComments,
                ItemScore.NullItemScore,
                PostTime.NullPostTime);

        final Item targetStory = new ItemAdapter().itemFromJson(itemJson);

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

        //time - Since this is time; there's a chance this will fail when the time doesn't match
        //perfectly.
        //Which happens. Using some text manipulation to prevent this.
        final DateUtils du = new DateUtils();
        targetStory.postTimeInto(item);
        final CharSequence expectedTimeString = du.relativeTimeSpanString(expectedTime*1000);
        final long actualPostTimeLong = Long.valueOf(item.getText().subSequence(0, 18).toString());
        final long expectedPostTimeLong = Long.valueOf(expectedTimeString.subSequence(0, 18).toString());
        //This get's around the time issue
        assertThat(actualPostTimeLong).isBetween(expectedPostTimeLong, expectedPostTimeLong + 1);
        assertThat(item.getText().subSequence(20, expectedTimeString.length()))
                .isEqualTo(expectedTimeString.subSequence(20, expectedTimeString.length()));
    }

    @Test
    public void storyToJson() {
        assertThatThrownBy(() -> new ItemAdapter().itemToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
