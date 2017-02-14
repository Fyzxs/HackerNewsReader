package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesAdapterTests {

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new TopStoriesAdapter(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityMediator");
    }


    @Test
    public void getItemCount(){
        final int val = RandomValues.nextInt(100);
        final TopStoriesActivityMediator fake = new TopStoriesActivityMediator(new FakeTopStoriesActivityBridge(null, new FakeTopStoriesActivity(null))){
            @Override
            int topStoriesSize() {
                return val;
            }
        };
        assertThat(new TopStoriesAdapter(fake).getItemCount()).isEqualTo(val);
    }


}
