package org.unifi.resources;

import java.net.URI;
import java.util.List;

import org.unifi.model.MatchEntity;
import org.unifi.repositories.MatchRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatchResource {

    @Inject
    MatchRepository matchRepository;

    @GET
    @Path("/played")
    public Response getAllPlayedMatches() {
        List<MatchEntity> playedMatches = matchRepository.list("scoreH is not null order by date");
        return Response.ok(playedMatches).build();
    }

    @GET
    @Path("/upcoming")
    public Response getAllUpcomingMatches() {
        List<MatchEntity> upcomingmatches = matchRepository.list("scoreH is null order by date");
        return Response.ok(upcomingmatches).build();
    }

    @POST
    @Transactional
    public Response create(MatchEntity match) {
        matchRepository.persist(match);
        if(matchRepository.isPersistent(match)){
            return Response.created(URI.create("/matches/" + match.id)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
    
}
