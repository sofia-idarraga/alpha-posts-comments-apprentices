package com.sofka.alphapostcomments.domain;

import co.com.sofka.domain.generic.Entity;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.CommentId;
import com.sofka.alphapostcomments.domain.values.Content;

import java.util.Objects;

public class Comment extends Entity<CommentId> {

    private Author author;
    private Content content;

    public Comment(CommentId entityId, Author author, Content content) {
        super(entityId);
        this.author = author;
        this.content = content;
    }

    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }

    public void editContent(Content content){
        this.content = Objects.requireNonNull(content);
    }
}
