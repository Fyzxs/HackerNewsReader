package com.quantityandconversion.hackernews.network.item;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final List<ItemId> itemIdList = new ArrayList<>();

    public Items(ItemId[] itemIds) {
        if(itemIds == null) { throw new IllegalArgumentException("itemIds cannot be null"); }

        importItemIds(itemIds);
    }

    private void importItemIds(final ItemId[] itemIds) {
        for (final ItemId itemId : itemIds) {
            if(itemId == null){ continue; }
            itemIdList.add(itemId);
        }
    }

    public int size() {
        return itemIdList.size();
    }

    public boolean contains(final ItemId itemId) {
        return itemIdList.contains(itemId);
    }
}
