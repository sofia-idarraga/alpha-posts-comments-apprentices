package com.sofka.alphapostcomments.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public interface DomainEventRepository {

    Mono<DomainEvent> saveEvent(DomainEvent domainEvent);

    Flux<DomainEvent> findById(String aggregateID);
}
