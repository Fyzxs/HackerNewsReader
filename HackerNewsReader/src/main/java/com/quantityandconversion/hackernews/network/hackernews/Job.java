package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;

/*
 * We're leaving this empty.
 * I know there will be more fields, it might be merged with "Story"
 */
public class Job extends Item {
    public Job(final ItemId itemId,
               final Title title,
               final Author author,
               final PostTime postTime) {
        super(itemId, title, author, postTime);
    }
}
