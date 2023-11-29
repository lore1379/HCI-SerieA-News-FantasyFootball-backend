package org.unifi.repositories;

import org.unifi.model.MatchEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatchRepository implements PanacheRepository<MatchEntity> {
    
}
