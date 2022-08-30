package com.sofka.alphapostcomments.domain;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.alphapostcomments.domain.events.CommentAdded;
import com.sofka.alphapostcomments.domain.events.PostCreated;

import java.util.ArrayList;

public class PostChange extends EventChange {

    public PostChange(Post post){
        apply((PostCreated event)->{
            post.title = event.getTitle();
            post.author = event.getAuthor();
            post.comments = new ArrayList<>();
        });

        apply((CommentAdded event)->{
            Comment comment = new Comment(
                    event.getId(),
                    event.getAuthor(),
                    event.getContent()
            );
            post.comments.add(comment);
        });
    }
}
