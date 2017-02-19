package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.test.AsyncFakeSetText;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthorTests {
    private final static Author OneAuthor = new Author("one_auth");
    private final static Author OneAuthorDiff = new Author("one_auth");
    private final static Author TwoAuthor = new Author("other_guy");

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new Author(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("input can not be null");
        assertThatThrownBy(() -> new Author(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("author can not be null");
        new Author("some value");
    }
    @Test
    public void equals(){
        assertThat(OneAuthor).isEqualTo(OneAuthor);
        assertThat(OneAuthor).isEqualTo(OneAuthorDiff);
        assertThat(OneAuthor).isNotEqualTo(TwoAuthor);
    }

    @Test
    public void authorSetsValue() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        OneAuthor.authorInto(fakeSetText);

        Java6Assertions.assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void authorDoesNotThrowGivenNull() throws InterruptedException {
        OneAuthor.authorInto(null);//Update TitleTests w/this form
    }

    @Test
    public void hash(){
        assertThat(OneAuthor.hashCode()).isEqualTo(OneAuthor.hashCode());
        assertThat(OneAuthor.hashCode()).isEqualTo(OneAuthorDiff.hashCode());
        assertThat(OneAuthor.hashCode()).isNotEqualTo(TwoAuthor.hashCode());
    }

}
