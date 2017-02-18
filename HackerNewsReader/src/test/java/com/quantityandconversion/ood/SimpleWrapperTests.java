package com.quantityandconversion.ood;

import com.quantityandconversion.hackernews.network.hackernews.internal.Author;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class SimpleWrapperTests {

    private final static SimpleWrapper IntOneWrapper = new SimpleWrapper<Integer>(1){};
    private final static SimpleWrapper IntOneWrapperOther = new SimpleWrapper<Integer>(1){};
    private final static SimpleWrapper StringOneWrapper = new SimpleWrapper<String>("1"){};
    private final static SimpleWrapper StringTwoWrapper = new SimpleWrapper<String>("2"){};

    @Test
    public void ctorThrows(){
        assertThatThrownBy(() -> new Author(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("input can not be null");

    }
    @Test
    public void ctorString(){
        new SimpleWrapper<String>("a string"){};
        new SimpleWrapper<Integer>(1){};
    }

    @Test
    public void equals(){
        assertThat(IntOneWrapper).isEqualTo(IntOneWrapper);
        assertThat(IntOneWrapper).isEqualTo(IntOneWrapperOther);
        assertThat(StringOneWrapper).isNotEqualTo(StringTwoWrapper);
        assertThat(StringOneWrapper).isNotEqualTo(IntOneWrapper);
    }

    @Test
    public void hash(){
        assertThat(IntOneWrapper.hashCode()).isEqualTo(IntOneWrapper.hashCode());
        assertThat(IntOneWrapper.hashCode()).isEqualTo(IntOneWrapperOther.hashCode());
        assertThat(StringOneWrapper.hashCode()).isNotEqualTo(StringTwoWrapper.hashCode());
        assertThat(StringOneWrapper.hashCode()).isNotEqualTo(IntOneWrapper.hashCode());
    }
}
