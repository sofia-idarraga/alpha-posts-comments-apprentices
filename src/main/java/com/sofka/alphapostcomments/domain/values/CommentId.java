package com.sofka.alphapostcomments.domain.values;

import co.com.sofka.domain.generic.Identity;

public class CommentId extends Identity {

    public CommentId(){

    }

    private CommentId(String id){
        super(id);
    }

    public static CommentId of(String id){
        return new CommentId(id);
    }
}
