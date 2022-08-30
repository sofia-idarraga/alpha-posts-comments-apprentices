package com.sofka.alphapostcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.CommentId;
import com.sofka.alphapostcomments.domain.values.Content;

public class CommentAdded extends DomainEvent {

    private final CommentId id;
    private final Author author;
    private final Content content;

    public CommentAdded(CommentId id, Author author, Content content) {
        super("domain.CommentAdded");
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }
}
