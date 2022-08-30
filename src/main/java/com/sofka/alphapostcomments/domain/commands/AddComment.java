package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.CommentId;
import com.sofka.alphapostcomments.domain.values.Content;
import com.sofka.alphapostcomments.domain.values.PostId;

public class AddComment extends Command {

    private final PostId postId;
    private final CommentId id;
    private final Author author;
    private final Content content;

    public AddComment(PostId postId, CommentId id, Author author, Content content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public PostId getPostId() {
        return postId;
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
