package com.ichwan.books;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/books")
public class BookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Book> books = Book.listAll();
        return Response.ok(books).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return Book.findByIdOptional(id)
                .map(book -> Response.ok(book).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("title/{title}")
    public Response getByTitle(@PathParam("title") String title) {
        return Book.find("title", title)
                .singleResultOptional()
                .map(book -> Response.ok(book).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTotalPrice(){
//        Book.count("SELECT SUM(b.price) FROM Book b")
//    }


    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Book book) {
        Book.persist(book);
        if (book.isPersistent()) {
            return Response.created(URI.create("/books" + book.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Response update(Book updatedBook, @PathParam("id") Long id){

        Book book = Book.findById(id);

        book.title = updatedBook.title;
        book.description = updatedBook.description;
        book.isbn = updatedBook.isbn;
        book.pages = updatedBook.pages;
        book.price = updatedBook.price;

        Book.persist(book);

        if (book.isPersistent()) {
            return Response.accepted(book).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = Book.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}