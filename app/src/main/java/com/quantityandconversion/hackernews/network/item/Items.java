package com.quantityandconversion.hackernews.network.item;

public class Items {
    private final ItemId[] itemIds;

    public Items(ItemId[] itemIds) {
        this.itemIds = itemIds;
    }

    public int size() {
        return itemIds.length;
    }
}
