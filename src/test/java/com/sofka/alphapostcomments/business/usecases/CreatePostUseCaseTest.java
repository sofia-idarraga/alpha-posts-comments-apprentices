package com.sofka.alphapostcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.business.gateways.DomainEventRepository;
import com.sofka.alphapostcomments.business.gateways.EventBus;
import com.sofka.alphapostcomments.domain.commands.CreatePost;
import com.sofka.alphapostcomments.domain.events.PostCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class CreatePostUseCaseTest {

    @Mock
    DomainEventRepository repository;
    @Mock
    CreatePostUseCase useCase;
    @Mock
    EventBus bus;

    @BeforeEach
    void init() {
        useCase = new CreatePostUseCase(repository, bus);
    }

    @Test
    @DisplayName("CreatePostUseCase")
    void createPostUseCase() {

        var postCreated = new PostCreated("titleTest", "AuthorTest");
        postCreated.setAggregateRootId("1");

        var command = new CreatePost("1", "titleTest", "AuthorTest");
        Mono<DomainEvent> responseExpected = Mono.just(postCreated);

        Mockito.when(repository.saveEvent(Mockito.any(DomainEvent.class)))
                .thenReturn(responseExpected);


        var useCaseExecute = useCase.apply(Mono.just(command)).collectList();

        StepVerifier.create(useCaseExecute)
                .expectNextMatches(events -> events.get(0) instanceof PostCreated)
                .expectComplete().verify();

        Mockito.verify(repository).saveEvent(Mockito.any(DomainEvent.class));
    }


}