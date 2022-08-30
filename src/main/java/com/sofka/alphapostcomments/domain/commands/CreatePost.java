package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CreatePost extends Command {

    private String postId;
    private String title;
    private String author;

    public CreatePost() {

    }

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
