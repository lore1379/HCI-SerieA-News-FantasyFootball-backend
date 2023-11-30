package org.unifi.resources;

import java.net.URI;
import java.util.List;

import org.unifi.model.PlayerEntity;
import org.unifi.repositories.PlayerRepository;

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

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject
    PlayerRepository playerRepository;

    @GET
    public Response getAllPlayers() {
        List<PlayerEntity> players = playerRepository.list("order by surname");
        return Response.ok(players).build();
    }

    @POST
    @Transactional
    public Response create(PlayerEntity player) {
        playerRepository.persist(player);
        if(playerRepository.isPersistent(player)){
            return Response.created(URI.create("/players/" + player.id)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
    
}
