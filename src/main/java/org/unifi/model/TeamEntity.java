package org.unifi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class TeamEntity extends PanacheEntity {

    public String name;
    public Integer matchPlayed;
    public Integer wins;
    public Integer draws;
    public Integer losses;
    public Integer points;
    
}
