package com.sofka.alphapostcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Tag implements ValueObject<String> {

    private final String value;

    public Tag(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
