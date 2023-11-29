package org.unifi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class MatchEntity extends PanacheEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;

    public String homeTeamName;
    public String awayTeamName;
    public Integer scoreH;
    public Integer scoreA;
    
}
