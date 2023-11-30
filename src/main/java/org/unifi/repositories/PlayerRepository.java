package org.unifi.repositories;

import org.unifi.model.PlayerEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerEntity>{
    
}
