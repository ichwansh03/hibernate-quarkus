package com.ichwan.books;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table()
public class Book extends PanacheEntity {

    @Column(length = 100)
    public String title;

    @Column(length = 200)
    public String description;

    public String isbn;

    public Integer pages;

    public Double price;

    public static Book findByTitle(String title){
        return find("title", title).firstResult();
    }
}
