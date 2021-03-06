package org.larizmen.analysis.processvirus;

import org.larizmen.analysis.domain.OrderTicket;
import org.larizmen.analysis.domain.ProcessTicket;
import org.larizmen.analysis.domain.VirusExecution;

import io.quarkus.runtime.annotations.RegisterForReflection;


import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@RegisterForReflection
public class KafkaService {

    Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Inject
    VirusExecution virusexecution;

    @Inject
    @Channel("orders-out")
    Emitter<ProcessTicket> orderprocessEmitter;


    @Incoming("virusprocess-in")
    public CompletableFuture<Object> handleOrderIn(OrderTicket orderTicket) {

        logger.debug("Order received: {}", orderTicket);

        return CompletableFuture.supplyAsync(() -> {
            return virusexecution.process(orderTicket);
        }).thenApply(processticket -> {
            logger.debug( "Processed Ticket: {}", processticket);
            orderprocessEmitter.send(processticket);
            logger.debug( "Ticket sent: {}", processticket);
            return null;
        });
    }
}
