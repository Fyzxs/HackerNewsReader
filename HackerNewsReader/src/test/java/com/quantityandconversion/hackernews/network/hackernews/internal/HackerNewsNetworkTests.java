package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HackerNewsNetworkTests extends MockWebServerTestClass {


    @Test
    public void constructorHandlesNull(){
        new HackerNewsNetwork(null);
        new HackerNewsNetwork();
    }
    @Test
    public void constructorHandlesMockUrl(){
        new HackerNewsNetwork(mockWebServer.url("/"));
        new HackerNewsNetwork();
    }

    @Test
    public void topStories() throws IOException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final Call<Stories> topStoriesCall = new HackerNewsNetwork().topStories();
        final Stories stories = topStoriesCall.execute().body();
        assertNotNull(stories);
        assertEquals(2, stories.size());
    }

    @Test
    public void story() throws IOException{
        final Story builtStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer);

        final Call<Story> itemCall = new HackerNewsNetwork().story(ItemId.createStoryId(1L));
        final Story story = itemCall.execute().body();

        assertThat(story).isEqualTo(builtStory);
    }

    @Test
    public void job() throws Exception{

        final Job builtJob = hackerNewsNetworkTestResponses.simpleJob(mockWebServer);

        final Call<Job> itemCall = new HackerNewsNetwork().job(ItemId.createJobId(1L));
        final Job job = itemCall.execute().body();

        assertThat(job).isEqualTo(builtJob);
    }
}
