package com.sofka.alphapostcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class PostCreated extends DomainEvent {

    private String title;
    private String author;

    public PostCreated(String title, String author) {
        super("domain.PostCreated");
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
