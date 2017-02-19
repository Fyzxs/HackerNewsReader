package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TitleTests extends QacTestClass {

    private final static String TitleOneVal = RandomValues.alphaNumeric(10);
    private final static String TitleTwoVal = RandomValues.alphaNumeric(10);
    public final static Title TitleOne = new Title(TitleOneVal);
    public final static Title TitleOneDiff = new Title(TitleOneVal);
    public final static Title TitleTwo = new Title(TitleTwoVal);
    @Test
    public void ctor(){
        assertThatThrownBy(() -> new Title(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("input can not be null");

        assertThatThrownBy(() -> new Title(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("title can not be null");

        new Title(TitleOneVal);
    }
    @Test
    public void equals(){
        assertThat(TitleOne).isEqualTo(TitleOne);
        assertThat(TitleOne).isEqualTo(TitleOneDiff);
        assertThat(TitleOne).isNotEqualTo(TitleTwo);
    }

    @Test
    public void hash(){
        assertThat(TitleOne.hashCode()).isEqualTo(TitleOne.hashCode());
        assertThat(TitleOne.hashCode()).isEqualTo(TitleOneDiff.hashCode());
        assertThat(TitleOne.hashCode()).isNotEqualTo(TitleTwo.hashCode());
    }

    @Test
    public void title() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        TitleOne.titleInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void titleDoesNotThrowGivenNull() throws InterruptedException {
        TitleOne.titleInto(null);
    }
}
