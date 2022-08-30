package com.sofka.alphapostcomments.domain.values;

import co.com.sofka.domain.generic.Identity;

public class PostId extends Identity {

    public PostId(){

    }

    private PostId(String id){
        super(id);
    }

    public static PostId of(String id){
        return new PostId(id);
    }
}
