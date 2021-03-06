package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.test.utils.RandomValues;

import java.util.Locale;

public class JobBuilder {
    private final static String jsonFormat = "{\"time\":%d,\"id\":%d,\"title\":\"%s\",\"type\":\"job\",\"by\":\"%s\"}";
    private String title = RandomValues.alphaNumeric(50);
    private String author = RandomValues.alphaNumeric(20);
    private long itemId = RandomValues.nextInt(Integer.MAX_VALUE);
    private long postTime = RandomValues.nextLongAbs();

    public String title(){
        return title;
    }

    public String author(){
        return author;
    }

    public JobBuilder setTitle(final String title){
        this.title = title;
        return this;
    }

    public JobBuilder setAuthor(final String author){
        this.author = author;
        return this;
    }

    public JobBuilder setItemId(final long itemId){
        this.itemId = itemId;
        return this;
    }

    public JobBuilder setPostTime(final long postTime) {
        this.postTime = postTime;
        return this;
    }

    public String buildJson(){
        return String.format(Locale.US, jsonFormat, postTime, itemId, title, author);
    }

    public Item buildJob(){
        return Item.createJob(ItemId.createJobId(itemId),
                new Title(title),
                new Author(author),
                new PostTime(postTime));
    }
}
