package com.sofka.alphapostcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.business.gateways.DomainEventRepository;
import com.sofka.alphapostcomments.business.gateways.EventBus;
import com.sofka.alphapostcomments.business.generic.UseCaseForCommand;
import com.sofka.alphapostcomments.domain.Post;
import com.sofka.alphapostcomments.domain.commands.AddComment;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.CommentId;
import com.sofka.alphapostcomments.domain.values.Content;
import com.sofka.alphapostcomments.domain.values.PostId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class AddCommentUseCase extends UseCaseForCommand<AddComment> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AddCommentUseCase(DomainEventRepository repository, EventBus bus) {

        this.repository = repository;
        this.bus = bus;

    }

    public Flux<DomainEvent> apply(Mono<AddComment> addCommentMono) {
        // First, we need the list of events to construct the Post with the ID and the events
        //flatMapMany takes the Mono‘s List, flattens it, and creates a Flux of Events
        return addCommentMono.flatMapMany(command -> repository.findById(command.getPostId())
                //Collect this to obtain the List of events that we gonna use to construct the Post
                .collectList()
                // The flatMapIterable allow us to use the List of events and then return a Flux
                .flatMapIterable(events -> {
                    Post post = Post.from(PostId.of(command.getPostId()), events); //The post
                    // Just adding to the post, the comment that we construct getting the VO's form the command
                    post.addComment(CommentId.of(command.getId()), new Author(command.getAuthor()), new Content(command.getContent()));
                    return post.getUncommittedChanges();
                }).map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(event -> repository.saveEvent(event))
        );
    }
    /*

    public Flux<DomainEvent> applyWrongWay(Mono<AddComment> addCommentMono) {

        return addCommentMono.flatMapIterable(command -> {
                    var events = repository.findById(command.getPostId()).toStream().collect(Collectors.toList());
                    Post post = Post.from(PostId.of(command.getPostId()), events);
                    post.addComment(CommentId.of(command.getId()), new Author(command.getAuthor()), new Content(command.getContent()));
                    return post.getUncommittedChanges();
                })
                .flatMap(domainEvent -> repository.saveEvent(domainEvent).thenReturn(domainEvent));
    }
    
     */
}
