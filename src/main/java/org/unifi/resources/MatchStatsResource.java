package org.unifi.resources;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.unifi.model.MatchStats;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/extStats")
public class MatchStatsResource {

    @Channel("external-requests-channel")
    Emitter<MatchStats> randomStatsEmitter;

    @POST
    @Path("/external")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRequest(MatchStats stats) {
        randomStatsEmitter.send(stats);
        return Response.accepted().build(); 
    }

    @Channel("external")
    Multi<MatchStats> stats;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<MatchStats> stream() {
        return stats;
    }
    
}
