package com.sofka.alphapostcomments.domain.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.PostId;
import com.sofka.alphapostcomments.domain.values.Title;

public class CreatePost extends Command {

    private final PostId postId;
    private final Title title;
    private final Author author;

    public CreatePost(PostId postId, Title title, Author author) {
        this.postId = postId;
        this.title = title;
        this.author = author;
    }

    public PostId getPostId() {
        return postId;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
