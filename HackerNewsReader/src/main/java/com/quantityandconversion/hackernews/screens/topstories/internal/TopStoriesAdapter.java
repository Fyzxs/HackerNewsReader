package com.quantityandconversion.hackernews.screens.topstories.internal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.Stories;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    private final Stories topStories;

    public TopStoriesAdapter(final Stories topStories){
        this.topStories = topStories;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return viewHolder(parent);
    }

    @NonNull
    private ViewHolder viewHolder(final ViewGroup parent) {
        return new ViewHolder(topStoryView(parent));
    }

    private View topStoryView(final ViewGroup parent) {
        return layoutInflater(parent).inflate(R.layout.top_stories_item, parent, false);
    }

    private LayoutInflater layoutInflater(final ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final StoryId item = topStories.itemIdAt(position);



//        TextView textView = viewHolder.nameTextView;
//        textView.setText(contact.getName());
//        Button button = viewHolder.messageButton;
//        button.setText("Message");
    }

    @Override
    public int getItemCount() {
        return topStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);

            bindControls(itemView);

        }

        private void bindControls(final View itemView) {
            title = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
