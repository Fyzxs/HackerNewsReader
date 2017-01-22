package com.quantityandconversion.hackernews.network.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Items {
    private final List<ItemId> itemIdList = new ArrayList<>();

    public Items(ItemId[] itemIds) {
        if(itemIds == null) { throw new IllegalArgumentException("itemIds cannot be null"); }

        Collections.addAll(itemIdList, itemIds);
    }

    public int size() {
        return itemIdList.size();
    }
}
