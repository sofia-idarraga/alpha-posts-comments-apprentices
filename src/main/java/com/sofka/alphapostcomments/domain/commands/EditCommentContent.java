package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;

public class EditCommentContent extends Command {

    private String postId;
    private String commentId;
    private String content;

    public EditCommentContent() {
    }

    public String getPostId() {
        return postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }
}
