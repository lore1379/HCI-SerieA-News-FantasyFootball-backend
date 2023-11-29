package org.unifi.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.net.URI;
import java.util.List;

import org.unifi.model.NewsEntity;
import org.unifi.repositories.NewsRepository;

@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NewsResource {

    @Inject
    NewsRepository newsRepository;

    @GET
    public Response getAllNews() {
        List<NewsEntity> allNews = newsRepository.listAll();
        return Response.ok(allNews).build();
    }

    @GET
    @Path("/team/{name}")
    public Response getAllNewsByTeamName(@PathParam("name") String name) {
        List<NewsEntity> newsByTeam = newsRepository.list("teamName", name);
        return Response.ok(newsByTeam).build();
    }

    @POST
    @Transactional
    public Response create(NewsEntity news) {
        newsRepository.persist(news);
        if(newsRepository.isPersistent(news)){
            return Response.created(URI.create("/news/" + news.id)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateById(@PathParam("id") Long id, NewsEntity updatedNews) {
        NewsEntity newsToUpdate = newsRepository.findById(id);
        
        if (newsToUpdate == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        if (updatedNews.teamName != null) { newsToUpdate.teamName = updatedNews.teamName; }
        if (updatedNews.imageUrl != null) { newsToUpdate.imageUrl = updatedNews.imageUrl; }
        if (updatedNews.title != null) { newsToUpdate.title = updatedNews.title; }
        if (updatedNews.shortDescription != null) { newsToUpdate.shortDescription = updatedNews.shortDescription; }
        if (updatedNews.fullDescription != null) { newsToUpdate.fullDescription = updatedNews.fullDescription; }
        
        return Response.ok(newsToUpdate).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = newsRepository.deleteById(id);
        return deleted ? Response.noContent().build() 
            : Response.status(Status.NOT_FOUND).build();
    }
    
}
