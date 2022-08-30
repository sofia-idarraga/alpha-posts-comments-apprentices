package com.sofka.alphapostcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.Title;

public class PostCreated extends DomainEvent {

    private final Title title;
    private final Author author;

    public PostCreated(Title title, Author author) {
        super("domain.PostCreated");
        this.title = title;
        this.author = author;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
