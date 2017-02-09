package com.quantityandconversion.screens.topstories;

import com.quantityandconversion.hackernews.screens.topstories.TopStoriesActivity;
import com.quantityandconversion.hackernews.screens.topstories.TopStoriesActivityBridge;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesActivityBridgeTests extends MockWebServerTestClass {

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopStoriesActivityBridge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivity");

        new TopStoriesActivityBridge(new TopStoriesActivity());
    }

}
