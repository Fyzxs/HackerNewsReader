package com.quantityandconversion.hackernews.screens.topitems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.utils.toast.Toaster;
import com.quantityandconversion.widget.QacTextView;

/* package */ class TopItemsAdapter extends RecyclerView.Adapter<TopItemsAdapter.ViewHolder> {

    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ TopItemsAdapter(final TopItemsActivityMediator topItemsActivityMediator){
        if(topItemsActivityMediator == null) {throw new IllegalArgumentException("topItemsActivityMediator cannot be null"); }
        this.topItemsActivityMediator = topItemsActivityMediator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_stories_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Item story = topItemsActivityMediator.itemAt(position);
        story.titleInto(viewHolder.title);
        story.authorInto(viewHolder.author);
        story.commentCountInto(viewHolder.comments);
        story.scoreInto(viewHolder.points);
        story.postTimeInto(viewHolder.time);
        viewHolder.container.setOnClickListener(v ->
                new Toaster().makeToast(v.getContext(), "TAPPED: " + viewHolder.title, Toast.LENGTH_LONG).show());
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
        private LinearLayout container;

        /* package */ ViewHolder(final View itemView) {
            super(itemView);

            bindControls(itemView);
        }

        private void bindControls(final View itemView) {
            container = (LinearLayout)itemView.findViewById(R.id.ll_item_container);
            title = (QacTextView)itemView.findViewById(R.id.tv_title);
            points = (QacTextView)itemView.findViewById(R.id.tv_score_value);
            author = (QacTextView)itemView.findViewById(R.id.tv_posted_by);
            comments = (QacTextView)itemView.findViewById(R.id.tv_comments);
            time = (QacTextView)itemView.findViewById(R.id.tv_posted_time);
        }
    }
}
