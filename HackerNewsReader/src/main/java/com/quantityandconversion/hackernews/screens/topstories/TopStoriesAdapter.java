package com.quantityandconversion.hackernews.screens.topstories;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.widget.QacRecyclerView;
import com.quantityandconversion.widget.QacTextView;
import com.quantityandconversion.widget.interfaces.QacView;

/* package */ class TopStoriesAdapter extends QacRecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {

    private final TopStoriesActivityMediator topStoriesActivityMediator;

    /* package */ TopStoriesAdapter(final TopStoriesActivityMediator topStoriesActivityMediator){
        if(topStoriesActivityMediator == null) {throw new IllegalArgumentException("topStoriesActivityMediator cannot be null"); }
        this.topStoriesActivityMediator = topStoriesActivityMediator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return viewHolder(parent);
    }

    @NonNull
    private ViewHolder viewHolder(final ViewGroup parent) {
        return new ViewHolder(topStoryView(parent));
    }

    private QacView topStoryView(final ViewGroup parent) {
        return (QacView)layoutInflater(parent).inflate(R.layout.top_stories_item, parent, false);
    }

    private LayoutInflater layoutInflater(final ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //I feel Mediator should have a "displayStoryAt" to avoid this Demeter violation
        topStoriesActivityMediator.storyAt(position).title(viewHolder.title);
        viewHolder.points.setText("1234");
        viewHolder.author.setText("Posted by Fyzxs");
        viewHolder.comments.setText("23 comments");
        viewHolder.time.setText("10 minutes ago");

    }

    @Override
    public int getItemCount() {
        return topStoriesActivityMediator.topStoriesSize();
    }

    /* package */ static class ViewHolder extends QacRecyclerView.QacViewHolder implements com.quantityandconversion.widget.interfaces.QacRecyclerView.QacViewHolder{
        private QacTextView title;
        private QacTextView points;
        private QacTextView author;
        private QacTextView comments;
        private QacTextView time;

        /* package */ ViewHolder(final QacView itemView) {
            super(itemView.asView());

            //Bit of an unsafe cast - except it should be a QacView implementation
            bindControls(itemView);
        }

        private void bindControls(final QacView itemView) {
            title = (QacTextView)itemView.findViewById(R.id.tv_title);
            points = (QacTextView)itemView.findViewById(R.id.tv_score_value);
            author = (QacTextView)itemView.findViewById(R.id.tv_posted_by);
            comments = (QacTextView)itemView.findViewById(R.id.tv_comments);
            time = (QacTextView)itemView.findViewById(R.id.tv_posted_time);
        }
    }
}
