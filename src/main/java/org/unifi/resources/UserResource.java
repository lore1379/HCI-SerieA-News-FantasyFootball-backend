package org.unifi.resources;

import java.net.URI;

import org.unifi.model.UserEntity;
import org.unifi.repositories.UserRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    UserRepository userRepository;

    @GET
    @Path("/{name}")
    public Response getFavoriteTeamByUsername(@PathParam("name") String name) {
        UserEntity user = userRepository.find("username", name).firstResult();
        return Response.ok(user).build();
    }

    @POST
    @Transactional
    public Response create(UserEntity user) {
        userRepository.persist(user);
        if (userRepository.isPersistent(user)) {
            return Response.created(URI.create("/user/" + user.id)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
}
