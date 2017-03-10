package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.ood.SetText;

public class Story extends Item{
    public final static Story NullStory = new Story(ItemId.NULL_ITEM_ID,
            Title.NullTitle,
            Author.NullAuthor,
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore,
            PostTime.NullPostTime);

    private final StoryComments storyComments;
    private final StoryScore storyScore;

    public Story(final ItemId itemId,
                 final Title title,
                 final Author author,
                 final StoryComments storyComments,
                 final StoryScore storyScore,
                 final PostTime postTime) {
        super(itemId, title, author, postTime);

        if(storyComments == null) { throw new IllegalArgumentException("storyComments can not be null");}
        if(storyScore == null) { throw new IllegalArgumentException("storyScore can not be null");}

        this.storyComments = storyComments;
        this.storyScore = storyScore;
        }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Story && this.equals((Story)other);
    }
    private boolean equals(final Story other){
        return super.equals(other);
    }

    public void commentCountInto(final SetText item) {
        storyComments.commentCountInto(item);
    }

    public void scoreInto(final SetText item) {
        storyScore.scoreInto(item);
    }
}
