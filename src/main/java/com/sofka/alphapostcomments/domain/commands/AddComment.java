package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;

public class AddComment extends Command {

    private String postId;
    private String id;
    private String author;
    private String content;

    public AddComment() {

    }

    public AddComment(String postId, String id, String author, String content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
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
