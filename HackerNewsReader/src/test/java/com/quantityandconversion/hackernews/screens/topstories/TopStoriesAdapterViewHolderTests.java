package com.quantityandconversion.hackernews.screens.topstories;

import android.widget.TextView;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

public class TopStoriesAdapterViewHolderTests {
    @Test
    public void ctor(){
//        title = (QacTextView)itemView.findViewById(R.id.tv_title);
//        points = (QacTextView)itemView.findViewById(R.id.tv_score_value);
//        author = (QacTextView)itemView.findViewById(R.id.tv_posted_by);
//        comments = (QacTextView)itemView.findViewById(R.id.tv_comments);
//        time = (QacTextView)itemView.findViewById(R.id.tv_posted_time);

        //final String expectedTitle = RandomValues.alphaNumeric(20);

        final TextView tv = Mockito.mock(TextView.class);
        Mockito.doNothing().when(tv).setText(anyString());
        tv.setText("vclort");
//        final TopStoriesAdapter.ViewHolder vh =
//                new TopStoriesAdapter.ViewHolder(new QacView() {
//                    @Override
//                    public View findViewById(int id) {
//                        return new FakeTextView();
//                    }
//
//                    @Override
//                    public View asView() {
//                        return new FakeTextView(Mockito.mock(Context.class));
//                    }
//                });


    }
}
