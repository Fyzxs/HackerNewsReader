package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.utils.Strings;

import java.util.Scanner;

public class Story {
    public final static Story NullStory = new Story(new StoryId(-1), "Loading Story");
    private final StoryId storyId;
    private final String title;

    public Story(final StoryId storyId, final String title) {
        if(Strings.isNullOrEmpty(title)) { throw new IllegalArgumentException("title cannot be null"); }
        if(storyId == null) { throw new IllegalArgumentException("storyId cannot be null");}

        this.storyId = storyId;
        this.title = title;
    }



    @Override
    public int hashCode() {
        return storyId.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Story &&
                (this == other || this.equals((Story)other));
    }
    private boolean equals(final Story other){
        return this.storyId.equals(other.storyId);
    }

    public Scanner title() {
        return new Scanner(title);
    }

    /* package */ StoryId storyId(){
        return storyId;
    }
}
