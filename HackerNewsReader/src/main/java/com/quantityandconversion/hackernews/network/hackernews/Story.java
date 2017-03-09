package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.ood.SetText;

public class Story {
    public final static Story NullStory = new Story(ItemId.NULL_ITEM_ID,
            Title.NullTitle,
            Author.NullAuthor,
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore,
            PostTime.NullPostTime);

    private final ItemId itemId;
    private final Title title;
    private final Author author;
    private final StoryComments storyComments;
    private final StoryScore storyScore;
    private final PostTime postTime;

    public Story(final ItemId itemId,
                 final Title title,
                 final Author author,
                 final StoryComments storyComments,
                 final StoryScore storyScore,
                 final PostTime postTime) {

        if(title == null) { throw new IllegalArgumentException("title can not be null"); }
        if(itemId == null) { throw new IllegalArgumentException("itemId can not be null");}
        if(author == null) { throw new IllegalArgumentException("author can not be null");}
        if(storyComments == null) { throw new IllegalArgumentException("storyComments can not be null");}
        if(storyScore == null) { throw new IllegalArgumentException("storyScore can not be null");}
        if(postTime == null) { throw new IllegalArgumentException("postTime can not be null");}

        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.storyComments = storyComments;
        this.storyScore = storyScore;
        this.postTime = postTime;
    }

    @Override
    public int hashCode() {
        return itemId.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Story && this.equals((Story)other);
    }
    private boolean equals(final Story other){
        return this.itemId.equals(other.itemId);
    }

    public void titleInto(final SetText item) {
        title.titleInto(item);
    }

    public void authorInto(final SetText item) {
        author.authorInto(item);
    }

    //I'm very BOOOO on this
    /* package */ ItemId storyId(){
        return itemId;
    }

    public void commentCountInto(final SetText item) {
        storyComments.commentCountInto(item);
    }

    public void scoreInto(final SetText item) {
        storyScore.scoreInto(item);
    }

    public void postTimeInto(final SetText item) {
        postTime.postTimeInto(item);
    }
}
