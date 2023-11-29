package org.unifi.resources;

import java.net.URI;
import java.util.List;

import org.unifi.model.TeamEntity;
import org.unifi.repositories.TeamRepository;

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

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository teamRepository;

    @GET
    public Response getAllTeams() {
        List<TeamEntity> teams = teamRepository.list("order by points DESC");
        return Response.ok(teams).build();
    }

    @POST
    @Transactional
    public Response create(TeamEntity team) {
        teamRepository.persist(team);
        if(teamRepository.isPersistent(team)){
            return Response.created(URI.create("/teams/" + team.id)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
    
}
