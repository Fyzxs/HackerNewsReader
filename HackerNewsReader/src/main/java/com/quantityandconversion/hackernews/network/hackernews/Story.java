package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.ood.SetText;

public class Story {
    public final static Story NullStory = new Story(StoryId.NullStoryId,
            Title.NullTitle,
            Author.NullAuthor,
            StoryComments.NullStoryComments,
            StoryScore.NullStoryScore,
            PostTime.NullPostTime);

    private final StoryId storyId;
    private final Title title;
    private final Author author;
    private final StoryComments storyComments;
    private final StoryScore storyScore;
    private final PostTime postTime;

    public Story(final StoryId storyId,
                 final Title title,
                 final Author author,
                 final StoryComments storyComments,
                 final StoryScore storyScore,
                 final PostTime postTime) {

        if(title == null) { throw new IllegalArgumentException("title can not be null"); }
        if(storyId == null) { throw new IllegalArgumentException("storyId can not be null");}
        if(author == null) { throw new IllegalArgumentException("author can not be null");}
        if(storyComments == null) { throw new IllegalArgumentException("storyComments can not be null");}
        if(storyScore == null) { throw new IllegalArgumentException("storyScore can not be null");}
        if(postTime == null) { throw new IllegalArgumentException("postTime can not be null");}

        this.storyId = storyId;
        this.title = title;
        this.author = author;
        this.storyComments = storyComments;
        this.storyScore = storyScore;
        this.postTime = postTime;
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

    public void titleInto(final SetText item) {
        title.titleInto(item);
    }

    public void authorInto(final SetText item) {
        author.authorInto(item);
    }

    //I'm very BOOOO on this
    /* package */ StoryId storyId(){
        return storyId;
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
