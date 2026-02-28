package com.restapi.superhero.entity;

//package com.example.superhero;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "superheroes")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    // @Type(Json.class)
    @Column(columnDefinition = "jsonb") // Explicitly define the column as JSONB in the DB
    private HeroData heroData;

    public Superhero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeroData getHeroData() {
        return heroData;
    }

    public void setHeroData(HeroData heroData) {
        this.heroData = heroData;
    }

}
