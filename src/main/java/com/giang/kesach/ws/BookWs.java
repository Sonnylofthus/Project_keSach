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
        Book newBook=new Book();
//        newBook.setAuthors(book.getAuthors());
//        newBook.setbName(book.getbName());
//        newBook.setCategory(book.getCategory());
//        newBook.setDescription(book.getDescription());
//        newBook.setPulisher(book.getPulisher());
//        newBook.setPulishYear(book.getPulishYear());
//        newBook.setImgLink(book.getImgLink());
        id=BR.createNewBook(book);
        if(id!=0)
        return Response.ok(BR.getBook(id)).build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteBook(@PathParam("id") long id,@HeaderParam("token") String token) {
        if(!(token.equals("1234")))
            return Response.status(Response.Status.BAD_REQUEST).build();
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
    public Response updateBook(@PathParam("id") int id, Book book) {

        int count = BR.modifyBook(id, book);
        if (count == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
