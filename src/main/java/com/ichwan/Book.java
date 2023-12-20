package com.ichwan;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Book extends PanacheEntity {

    @Column(length = 100)
    public String title;

    @Column(length = 200)
    public String description;

    @Column
    public String isbn;

    @Column
    public Integer pages;
}
