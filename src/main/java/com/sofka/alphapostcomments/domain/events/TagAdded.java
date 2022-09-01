package com.sofka.alphapostcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class TagAdded extends DomainEvent {

    private String tag;

    public TagAdded(String tag) {
        super("domain.TagAdded");
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
