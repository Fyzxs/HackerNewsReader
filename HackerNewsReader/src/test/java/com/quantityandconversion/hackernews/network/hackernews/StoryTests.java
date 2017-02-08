package com.quantityandconversion.hackernews.network.hackernews;


import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryTests {

    @Test
    public void constructor(){
        assertThatThrownBy(() -> new Story(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title can not be null");
    }

    @Test
    public void equals(){

        final Story same = new Story(RandomValues.alphaNumeric(10));
        assertThat(same).isEqualTo(same);
        final String title = RandomValues.alphaNumeric(10);
        assertThat(new Story(title)).isEqualTo(new Story(title));
        assertThat(new Story(RandomValues.alphaNumeric(10)))
                .isNotEqualTo(new Story(RandomValues.alphaNumeric(10)));
    }


    @Test
    public void hash(){
        final String title = RandomValues.alphaNumeric(10);
        assertThat(new Story(title).hashCode()).isEqualTo(new Story(title).hashCode());
        assertThat(new Story(RandomValues.alphaNumeric(10)).hashCode())
                .isNotEqualTo(new Story(RandomValues.alphaNumeric(10)).hashCode());
    }
}
