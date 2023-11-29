package org.unifi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class UserEntity extends PanacheEntity {
    public String username;
    public String favoriteTeam;
}
