package com.sofka.alphapostcomments.domain;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.alphapostcomments.domain.events.CommentAdded;
import com.sofka.alphapostcomments.domain.events.CommentContentEdited;
import com.sofka.alphapostcomments.domain.events.PostCreated;
import com.sofka.alphapostcomments.domain.events.TagAdded;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.CommentId;
import com.sofka.alphapostcomments.domain.values.Content;
import com.sofka.alphapostcomments.domain.values.Tag;
import com.sofka.alphapostcomments.domain.values.Title;

import java.util.ArrayList;

public class PostChange extends EventChange {

    public PostChange(Post post) {
        apply((PostCreated event) -> {
            post.title = new Title(event.getTitle());
            post.author = new Author(event.getAuthor());
            post.comments = new ArrayList<>();
            post.tags = new  ArrayList<>();
        });

        apply((CommentAdded event) -> {
            Comment comment = new Comment(
                    CommentId.of(event.getId()),
                    new Author(event.getAuthor()),
                    new Content(event.getContent())
            );
            post.comments.add(comment);
        });

        apply((TagAdded event) ->{
            Tag tag = new Tag(event.getTag());
            post.tags.add(tag);
        });

        apply((CommentContentEdited event)->{
            var comment = post.getCommentById(CommentId.of(event.getCommentId()))
                    .orElseThrow(() -> new IllegalArgumentException("Comment ID doesn't exist"));
            comment.editContent(new Content(event.getContent()));
        });
    }
}
