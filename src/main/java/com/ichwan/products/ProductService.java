package com.ichwan.products;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Produces
    String name = "Ichwan Sholihin";

    @Produces
    List<String> type() {
        return List.of("Electronics", "Foods", "Farm");
    }

    public void execute() {
        System.out.println("execute service");
    }
}
