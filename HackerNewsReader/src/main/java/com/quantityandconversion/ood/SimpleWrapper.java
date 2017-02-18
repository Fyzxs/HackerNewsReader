package com.quantityandconversion.ood;

public abstract class SimpleWrapper<T> {
    private final T input;

    protected SimpleWrapper(final T input) {
        if(input == null) {throw new IllegalArgumentException("input can not be null"); }
        validate(input);
        this.input = input;
    }

    protected void validate(final T input){}

    protected T value(){
        return input;
    }

    @Override
    public int hashCode() {
        return input.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof SimpleWrapper && this.equals((SimpleWrapper)other);
    }
    private boolean equals(final SimpleWrapper other){
        return this.input.equals(other.input);
    }
}
