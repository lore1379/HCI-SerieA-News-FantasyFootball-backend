package org.unifi.model;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MatchStatsDeserializer extends ObjectMapperDeserializer<MatchStats> {
    public MatchStatsDeserializer() {
        super(MatchStats.class);
    }
}
