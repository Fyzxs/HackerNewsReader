package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.widget.SetText;

public class Story {
    public final static Story NullStory = new Story(StoryId.NullStoryId, Title.NullTitle);
    private final StoryId storyId;
    private final Title title;
//
//    public Story(final StoryId storyId, final String title) {
//        if(Strings.isNullOrEmpty(title)) { throw new IllegalArgumentException("title cannot be null"); }
//        if(storyId == null) { throw new IllegalArgumentException("storyId cannot be null");}
//
//        this.storyId = storyId;
//        this.title = null;
//    }
    public Story(final StoryId storyId, final Title title) {
        if(title == null) { throw new IllegalArgumentException("title cannot be null"); }
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
        return other instanceof Story && this.equals((Story)other);
    }
    private boolean equals(final Story other){
        return this.storyId.equals(other.storyId);
    }

    public void title(final SetText setText) {
        title.title(setText);
    }

    //This remains in 'cause I'm thinknig about it. It's not
    //needed, but i might try swapping it in.
//    private String title(final String stringFormat){
//        return title.title(stringFormat);
//    }

    /* package */ StoryId storyId(){
        return storyId;
    }
}
