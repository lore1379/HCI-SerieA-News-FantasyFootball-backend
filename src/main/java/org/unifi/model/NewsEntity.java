package org.unifi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class NewsEntity extends PanacheEntity {
    
    public String teamName;
    public String imageUrl;
    public String title;

    @Column(columnDefinition = "TEXT")
    public String shortDescription;
    
    @Column(columnDefinition = "TEXT")
    public String fullDescription;
}
