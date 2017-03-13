package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.utils.log.FyzLog;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;

public class Stories {

    public interface ItemRefreshCallback{
        void itemRefreshed(final int index);
    }
    private final Map<ItemId, Item> itemMap = new HashMap<>();
    private final ItemId[] itemIds;

    public Stories(final ItemId[] itemIds) {
        if(itemIds == null) { throw new IllegalArgumentException("itemIds cannot be null"); }

        this.itemIds = itemIds;
        importItemIds();
    }

    private void importItemIds() {
        for (final ItemId itemId : itemIds) {
            if(itemId == null){ throw new IllegalArgumentException("itemId cannot be null");}
            itemMap.put(itemId, NullStory);
        }
    }

    public int size() {
        return itemMap.size();
    }

    public boolean contains(final ItemId itemId) {
        return itemMap.get(itemId) != null;
    }

    public Item itemAt(final int index, final ItemRefreshCallback itemRefreshCallback) {
        final ItemId itemId = itemIds[index];
        final Item item = itemMap.get(itemId);
        if(!shouldRefreshItem(item, itemRefreshCallback)){
            return item;
        }

        refreshItem(index, itemRefreshCallback, itemId);

        return item;
    }

    private void refreshItem(final int index, final ItemRefreshCallback itemRefreshCallback, ItemId itemId) {
        new HackerNewsAccess().item(itemId, new Callback<Item>() {
            @Override
            public void onResponse(final Call<Item> call, final Response<Item> response) {
                final Item item = response.body();
                itemMap.put(item.itemId(), item);
                itemRefreshCallback.itemRefreshed(index);
            }

            @Override
            public void onFailure(final Call<Item> call, final Throwable t) {
                FyzLog.d(call.request().url().toString());
                FyzLog.d(t.getMessage());
                throw new UnsupportedOperationException("onFailure not implemented");
            }
        });
    }

    private boolean shouldRefreshItem(final Item item, final ItemRefreshCallback itemRefreshCallback) {
        return !isValidItem(item) && itemRefreshCallback != null;
    }
    private boolean isValidItem(final Item item){
        return item != null && item.isValidType();
    }
}
