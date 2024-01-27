package com.ichwan.products;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    String name;

    @Inject
    List<String> type;

    @Inject
    private ProductService productService;

    @GET
    public Response print() {
        System.out.println(name);
        System.out.println(type);
        return Response.ok().build();
    }

    @GET
    public Response executes(){
        productService.execute();
        return Response.ok().build();
    }
}
