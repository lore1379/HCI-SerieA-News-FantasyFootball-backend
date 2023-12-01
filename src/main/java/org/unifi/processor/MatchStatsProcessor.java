package org.unifi.processor;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.unifi.model.MatchStats;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatchStatsProcessor {

    @Incoming("external-requests")
    @Outgoing("external")
    @Blocking
    public MatchStats process(MatchStats stats) throws InterruptedException {
        Thread.sleep(200);
        return stats;
    }
}
