package com.sofka.alphapostcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.business.gateways.DomainEventRepository;
import com.sofka.alphapostcomments.business.gateways.EventBus;
import com.sofka.alphapostcomments.business.generic.UseCaseForCommand;
import com.sofka.alphapostcomments.domain.Post;
import com.sofka.alphapostcomments.domain.commands.CreatePost;
import com.sofka.alphapostcomments.domain.values.Author;
import com.sofka.alphapostcomments.domain.values.PostId;
import com.sofka.alphapostcomments.domain.values.Title;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreatePostUseCase extends UseCaseForCommand<CreatePost> {

    // Instantiate the repository
    private final DomainEventRepository repository;
    private final EventBus bus;


    //Just the constructor
    public CreatePostUseCase(DomainEventRepository repository, EventBus bus) {

        this.repository = repository;
        this.bus = bus;

    }

    public Flux<DomainEvent> apply(Mono<CreatePost> createPost) {
        //We receive a Mono Command -CreatePost, so we need to do a flat map
        // FlatMapIterable: Maps the values of the upstream source into Iterables and iterates each of them one after the other.
        // In other words, it merges dynamically generated pull sources.
        // FlatMapIterable allows us to transform the Mono<List> into a Flux
        return createPost.flatMapIterable(command -> {
                    // Now we have the command, we need to create the post. For this we call the constructor and get
                    // its VO's from the command
                    Post post = new Post(PostId.of(command.getPostId()), new Title(command.getTitle()), new Author(command.getAuthor()));

                    // We return the events (remember this is an Event Oriented program)
                    // get the List of events stored in the subscriber (called change event)
                    // In this case the event is PostCreated
                    return post.getUncommittedChanges();
                })
                .flatMap(domainEvent -> repository.saveEvent(domainEvent).thenReturn(domainEvent))
                .doOnNext(bus::publish);
        //Finally we save the event in the repository and return it
    }
}
