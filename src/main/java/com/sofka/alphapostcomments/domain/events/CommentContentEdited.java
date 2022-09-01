package com.sofka.alphapostcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class CommentContentEdited extends DomainEvent {

    private String commentId;

    private String content;

    public CommentContentEdited(String commentId, String content) {
        super("domain.CommentContentEdited");
        this.commentId = commentId;
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }
}
