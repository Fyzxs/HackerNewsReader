package com.quantityandconversion.hackernews.screens.topstories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.log.FyzLog;
import com.quantityandconversion.widget.QacTextView;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    private final TopStoriesActivityMediator topStoriesActivityMediator;

    public TopStoriesAdapter(final TopStoriesActivityMediator topStoriesActivityMediator){
        this.topStoriesActivityMediator = topStoriesActivityMediator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        FyzLog.v("viewType=" + viewType);
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
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //I feel Mediator should have a "displayStoryAt" to avoid this Demeter violation
        topStoriesActivityMediator.storyAt(position).title(viewHolder.title);
    }

    @Override
    public int getItemCount() {
        return topStoriesActivityMediator.topStoriesSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private QacTextView title;

        public ViewHolder(final View itemView) {
            super(itemView);

            bindControls(itemView);
        }

        private void bindControls(final View itemView) {
            title = (QacTextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
