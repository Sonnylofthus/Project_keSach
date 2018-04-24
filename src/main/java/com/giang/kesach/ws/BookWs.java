package com.giang.kesach.ws;


import com.giang.kesach.models.Book;
import com.giang.kesach.resources.BookResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("book")
public class BookWs {
    public static final BookResource BR = new BookResource();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBook() {


        return BR.getAllBook();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("id") long id) {


        return BR.getBook(id);

    }

    @POST
   @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        long id=0;
        id=BR.createNewBook(book);
        if(id!=0)
        return Response.ok(BR.getBook(id)).build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")

    public Response deleteBook(@PathParam("id") long id) {

        int count = BR.deleteBook(id);
        if (count == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    // thay ob co book_id =id bang book
    @PUT
    @Path("change/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") long id, Book book) {

        int count = BR.modifyBook(id, book);
        if (count == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(BR.getBook(id)).build();
    }
}
