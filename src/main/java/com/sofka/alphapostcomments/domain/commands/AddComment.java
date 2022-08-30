package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;

public class AddComment extends Command {

    private String postId;
    private String id;
    private String author;
    private String content;

    public AddComment() {

    }

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
