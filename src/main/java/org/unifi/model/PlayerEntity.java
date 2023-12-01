package org.unifi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class PlayerEntity extends PanacheEntity {

    public String name;
    public String surname;
    public String role;
    public String score;
    
}
