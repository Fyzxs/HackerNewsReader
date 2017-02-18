package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.widget.interfaces.QacTextView;

public class Story {
    public final static Story NullStory = new Story(StoryId.NullStoryId, Title.NullTitle, Author.NullAuthor);
    private final StoryId storyId;
    private final Title title;
    private final Author author;

    public Story(final StoryId storyId, final Title title, final Author author) {
        if(title == null) { throw new IllegalArgumentException("title can not be null"); }
        if(storyId == null) { throw new IllegalArgumentException("storyId can not be null");}
        if(author == null) { throw new IllegalArgumentException("author can not be null");}

        this.storyId = storyId;
        this.title = title;
        this.author = author;
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

    public void title(final SetText item) {
        title.title(item);
    }

    public void author(final SetText item) {
        author.author(item);
    }

    //I'm very BOOOO on this
    /* package */ StoryId storyId(){
        return storyId;
    }
}
