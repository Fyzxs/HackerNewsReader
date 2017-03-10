package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.date.DateUtils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JobAdapterTests extends QacTestClass {
    @Test
    public void jobFromJsonThrowsJobJson() {
        assertThatThrownBy(() -> new JobAdapter().jobFromJson(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("can not be null");
    }

    @Test
    public void jobFromJsonBuildsExpectantly() {
        final String expectedTitle = RandomValues.alphaNumeric(10);
        final String expectedAuthor = RandomValues.alphaNumeric(10);
        final long expectedId = RandomValues.nextLongAbs();
        final long expectedTime = RandomValues.nextLongAbs();

        final JobJson jobJson = new JobJson();
        jobJson.id = expectedId;
        jobJson.title = expectedTitle;
        jobJson.type = "job";
        jobJson.by = expectedAuthor;
        jobJson.time = expectedTime;


        final Job expectedJob = new Job(
                ItemId.createJobId(expectedId),
                Title.NullTitle,
                Author.NullAuthor,
                PostTime.NullPostTime);

        final Job targetJob = new JobAdapter().jobFromJson(jobJson);

        //id
        assertThat(targetJob).isEqualTo(expectedJob);

        final AsyncFakeSetText item = new AsyncFakeSetText();
        //title
        targetJob.titleInto(item);
        assertThat(item.getText()).isEqualTo(expectedTitle);
        //by
        targetJob.authorInto(item);
        assertThat(item.getText()).isEqualTo("Posted by: " + expectedAuthor);

        //time - Since this is time; there's a change this will fail when the time doesn't match
        //perfectly.
        //Which happens. Using some text manipulation to prevent this.
        final DateUtils du = new DateUtils();
        targetJob.postTimeInto(item);
        final CharSequence expectedTimeString = du.relativeTimeSpanString(expectedTime*1000);
        assertThat(item.getText().subSequence(0, 18)).isEqualTo(expectedTimeString.subSequence(0, 18));
        assertThat(item.getText().subSequence(20, expectedTimeString.length()))
                .isEqualTo(expectedTimeString.subSequence(20, expectedTimeString.length()));
    }

    @Test
    public void jobToJson() {
        assertThatThrownBy(() -> new JobAdapter().jobToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
