package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsAccessTests extends MockWebServerTestClass {

    @Test
    public void topStories() throws Exception {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new HackerNewsAccess().topStories(new Callback<Stories>(){
            @Override
            public void onResponse(Call<Stories> call, Response<Stories> response) {
                assertThat(response.isSuccessful()).isTrue();
                assertThat(response.body()).isNotNull();
                final Stories stories = response.body();
                assertThat(stories.size()).isEqualTo(2);
                assertThat(stories.contains(new StoryId(10000))).isTrue();
                assertThat(stories.contains(new StoryId(2))).isTrue();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<Stories> call, Throwable t) {

            }
        });

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void story() throws Exception{
        hackerNewsNetworkTestResponses.simpleStory(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new HackerNewsAccess().story(new StoryId(1L), new Callback<Story>(){
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                assertThat(response.isSuccessful()).isTrue();
                assertThat(response.body()).isNotNull();
                final Story story = response.body();
                //final Scanner titleScanner = story.title();

                assertThat("").isEqualTo("this is a faked title");
                latch.countDown();
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {

            }
        });

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
