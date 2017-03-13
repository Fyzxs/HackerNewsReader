package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.widget.QacTextView;

/* package */ class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ TopStoriesAdapter(final TopItemsActivityMediator topItemsActivityMediator){
        if(topItemsActivityMediator == null) {throw new IllegalArgumentException("topItemsActivityMediator cannot be null"); }
        this.topItemsActivityMediator = topItemsActivityMediator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_stories_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //I feel Mediator should have a "displayStoryAt" to avoid this Demeter violation
        //Hard code cast - // FIXME: 3/12/17 
        final Story story = (Story)topItemsActivityMediator.itemAt(position);
        story.titleInto(viewHolder.title);
        story.authorInto(viewHolder.author);
        story.commentCountInto(viewHolder.comments);
        story.scoreInto(viewHolder.points);
        story.postTimeInto(viewHolder.time);

    }

    @Override
    public int getItemCount() {
        return topItemsActivityMediator.topStoriesSize();
    }

    /* package */ static class ViewHolder extends RecyclerView.ViewHolder{
        private QacTextView title;
        private QacTextView points;
        private QacTextView author;
        private QacTextView comments;
        private QacTextView time;

        /* package */ ViewHolder(final View itemView) {
            super(itemView);

            bindControls(itemView);
        }

        private void bindControls(final View itemView) {
            title = (QacTextView)itemView.findViewById(R.id.tv_title);
            points = (QacTextView)itemView.findViewById(R.id.tv_score_value);
            author = (QacTextView)itemView.findViewById(R.id.tv_posted_by);
            comments = (QacTextView)itemView.findViewById(R.id.tv_comments);
            time = (QacTextView)itemView.findViewById(R.id.tv_posted_time);
        }
    }
}
