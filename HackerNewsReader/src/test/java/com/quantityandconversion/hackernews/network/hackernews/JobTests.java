package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.hackernews.network.hackernews.internal.PostTime;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryComments;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryScore;
import com.quantityandconversion.hackernews.network.hackernews.internal.Title;
import com.quantityandconversion.hackernews.network.hackernews.internal.TitleTests;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JobTests {
    private final static ItemId ITEM_ID_ONE = ItemId.createJobId(1L);
    private final static ItemId ITEM_ID_TWO = ItemId.createJobId(2L);
    private final static Title TitleOne = TitleTests.TitleOne;

}
