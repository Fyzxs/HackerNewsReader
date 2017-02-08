package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantityandconversion.hackernews.R;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
