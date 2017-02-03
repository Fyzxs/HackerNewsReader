package com.quantityandconversion.hackernews.network.item;

public class ItemId {
    private long itemId;

    public ItemId(final long itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(itemId).hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof ItemId && this.equals((ItemId)other);
    }
    private boolean equals(final ItemId other){
        return this.itemId == other.itemId;
    }
}
