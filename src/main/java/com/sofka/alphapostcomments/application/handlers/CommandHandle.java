package com.sofka.alphapostcomments.application.handlers;


import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.business.usecases.AddCommentUseCase;
import com.sofka.alphapostcomments.business.usecases.AddTagUseCase;
import com.sofka.alphapostcomments.business.usecases.CreatePostUseCase;
import com.sofka.alphapostcomments.business.usecases.EditCommentContentUseCase;
import com.sofka.alphapostcomments.domain.commands.AddComment;
import com.sofka.alphapostcomments.domain.commands.AddTag;
import com.sofka.alphapostcomments.domain.commands.CreatePost;
import com.sofka.alphapostcomments.domain.commands.EditCommentContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class CommandHandle {

    @Bean
    public RouterFunction<ServerResponse> createPost(CreatePostUseCase useCase) {

        return route(
                POST("/create/post").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.bodyToMono(CreatePost.class))
                        .collectList()
                        .flatMap(events -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(events))
                        .onErrorResume(error -> {
                            log.error(error.getLocalizedMessage());
                            return ServerResponse.badRequest().build();
                        })


                // ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                // .body(BodyInserters.fromPublisher(useCase.apply(request.bodyToMono(CreatePost.class)), DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addComment(AddCommentUseCase useCase) {

        return route(
                POST("/add/comment").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.bodyToMono(AddComment.class))
                        .collectList()
                        .flatMap(domainEvens -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(domainEvens))
                        .onErrorResume(error -> {
                            log.error(error.getLocalizedMessage());
                            return ServerResponse.badRequest().build();
                        })

                // ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                // .body(BodyInserters.fromPublisher(useCase.apply(request.bodyToMono(AddComment.class)), DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addTag(AddTagUseCase useCase) {

        return route(
                POST("/add/tag").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.bodyToMono(AddTag.class)), DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> editCommentContent(EditCommentContentUseCase useCase) {

        return route(
                PUT("/edit/comment").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.bodyToMono(EditCommentContent.class)), DomainEvent.class))
        );
    }
}
