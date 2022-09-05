package com.sofka.alphapostcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.alphapostcomments.business.gateways.DomainEventRepository;
import com.sofka.alphapostcomments.business.gateways.EventBus;
import com.sofka.alphapostcomments.domain.commands.AddTag;
import com.sofka.alphapostcomments.domain.events.PostCreated;
import com.sofka.alphapostcomments.domain.events.TagAdded;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AddTagUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Mock
    AddTagUseCase useCase;

    @Mock
    EventBus bus;

    @BeforeEach
    void init() {
        useCase = new AddTagUseCase(repository, bus);
    }

    @Test
    @DisplayName("AddTagUseCase")
    void addTagUseCase() {

        var postCreated = new PostCreated("titleTest", "AuthorTest");
        postCreated.setAggregateRootId("1");

        var tadAdded = new TagAdded("#Test");
        tadAdded.setAggregateRootId("1");

        var command = new AddTag("1", "#Test");
        Mono<DomainEvent> responseExpected = Mono.just(tadAdded);

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Flux.just(postCreated));
        Mockito.when(repository.saveEvent(Mockito.any(DomainEvent.class))).thenReturn(responseExpected);

        var useCaseExecute = useCase.apply(Mono.just(command)).collectList();

        StepVerifier.create(useCaseExecute)
                .expectNextMatches(events ->
                        events.get(0).aggregateRootId().equals("1") &&
                        events.get(0) instanceof TagAdded)
                .expectComplete().verify();
        Mockito.verify(repository).findById(Mockito.any(String.class));
        Mockito.verify(repository).saveEvent(Mockito.any(DomainEvent.class));


    }

}