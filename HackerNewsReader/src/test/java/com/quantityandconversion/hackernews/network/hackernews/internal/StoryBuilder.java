package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.test.utils.RandomValues;

import java.util.Locale;

public class StoryBuilder {
    private final static String jsonFormat = "{\"time\":%d,\"score\":%d,\"id\":%d,\"title\":\"%s\",\"type\":\"story\",\"by\":\"%s\",\"descendants\":%d}";
    private String title = RandomValues.alphaNumeric(50);
    private String author = RandomValues.alphaNumeric(20);
    private long storyId = RandomValues.nextInt(Integer.MAX_VALUE);
    private long commentCount = RandomValues.nextInt(Integer.MAX_VALUE);
    private long storyScore = RandomValues.nextInt(1000);
    private long postTime = RandomValues.nextLongAbs();

    public String title(){
        return title;
    }

    public String author(){
        return author;
    }

    public long storyId(){
        return storyId;
    }

    public long commentCount(){
        return commentCount;
    }

    public long storyScore(){
        return storyScore;
    }

    public StoryBuilder setTitle(final String title){
        this.title = title;
        return this;
    }

    public StoryBuilder setAuthor(final String author){
        this.author = author;
        return this;
    }

    public StoryBuilder setStoryId(final long storyId){
        this.storyId = storyId;
        return this;
    }

    public StoryBuilder setCommentCount(final long commentCount){
        this.commentCount = commentCount;
        return this;
    }

    public StoryBuilder setPostTime(final long postTime) {
        this.postTime = postTime;
        return this;
    }

    public StoryBuilder setScore(final long score) {
        this.storyScore = score;
        return this;
    }

    public String buildJson(){
        return String.format(Locale.US, jsonFormat, postTime, storyScore, storyId, title, author, commentCount);
    }

    public Item buildStory(){
        return Item.createStory(ItemId.createStoryId(storyId),
                new Title(title),
                new Author(author),
                new ItemComments(commentCount),
                new ItemScore(storyScore),
                new PostTime(postTime));
    }
}
