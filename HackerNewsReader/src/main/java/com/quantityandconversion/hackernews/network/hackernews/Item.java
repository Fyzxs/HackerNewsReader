package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.ood.SetText;

public class Item {

    public final static Item NullItem = new Item(ItemId.NullItemId,
            Title.NullTitle,
            Author.NullAuthor,
            ItemComments.NullItemComments,
            ItemScore.NullItemScore,
            PostTime.NullPostTime);

    public static Item createJob(final ItemId itemId,
                                 final Title title,
                                 final Author author,
                                 final PostTime postTime){
        if(!itemId.isJobId()){ throw new IllegalArgumentException("Job must have a job id");}
        return new Item(itemId, title, author, postTime);
    }
    public static Item createStory(final ItemId itemId,
                                   final Title title,
                                   final Author author,
                                   final ItemComments itemComments,
                                   final ItemScore itemScore,
                                   final PostTime postTime){

        if(!itemId.isStoryId()){ throw new IllegalArgumentException("Story must have a story id");}
        if(itemComments == null) { throw new IllegalArgumentException("itemComments can not be null");}
        if(itemScore == null) { throw new IllegalArgumentException("itemScore can not be null");}
        return new Item(itemId, title, author, itemComments, itemScore, postTime);
    }

    private final ItemId itemId;
    private final Title title;
    private final Author author;
    private final PostTime postTime;
    private final ItemComments itemComments;
    private final ItemScore itemScore;

    /* package */ Item(final ItemId itemId,
                       final Title title,
                       final Author author,
                       final PostTime postTime) {
        this(itemId, title, author, null, null, postTime);
    }
    /* package */ Item(final ItemId itemId,
                final Title title,
                final Author author,
                       final ItemComments itemComments,
                       final ItemScore itemScore,
                final PostTime postTime) {

        if (title == null) {
            throw new IllegalArgumentException("title can not be null");
        }
        if (itemId == null) {
            throw new IllegalArgumentException("itemId can not be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author can not be null");
        }
        if (postTime == null) {
            throw new IllegalArgumentException("postTime can not be null");
        }

        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.postTime = postTime;

        this.itemComments = itemComments != null ? itemComments : ItemComments.NullItemComments;
        this.itemScore = itemScore != null ? itemScore : ItemScore.NullItemScore;
    }

    @Override
    public int hashCode() {
        return itemId.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Item && this.equals((Item)other);
    }
    private boolean equals(final Item other){
        return this.itemId.equals(other.itemId);
    }

    public void titleInto(final SetText item) {
        title.titleInto(item);
    }

    public void authorInto(final SetText item) {
        author.authorInto(item);
    }

    //I'm very BOOOO on this
    /* package */ ItemId itemId(){
        return itemId;
    }

    /* package */  boolean isValidType(){
        return isJob() || isStory();
    }

    public void postTimeInto(final SetText item) {
        postTime.postTimeInto(item);
    }

    public void commentCountInto(final SetText item) {
        itemComments.commentCountInto(item);
    }

    public void scoreInto(final SetText item) {
        itemScore.scoreInto(item);
    }

    public boolean isJob() {
        return itemId.isJobId();
    }

    public boolean isStory() {
        return itemId.isStoryId();
    }


}
