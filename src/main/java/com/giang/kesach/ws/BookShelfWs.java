package com.giang.kesach.ws;

import com.giang.kesach.models.Book;
import com.giang.kesach.resources.BookShelfResource;
import com.giang.kesach.resources.IBookShelf;
import com.giang.kesach.resources.IUserAccount;
import com.giang.kesach.resources.UserAccountResource;
import sun.security.action.PutAllAction;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("bookshelf")
public class BookShelfWs {
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getBookshelf(@PathParam("id") int id){
        IBookShelf BS=new BookShelfResource();
        return Response.ok(BS.getBookshelfsBook(id)).build();
    }
    @PUT
    @Path("{id}/add")
    @Consumes("application/json")
    public Response addBookToBookShelf(@PathParam("id") int BookshelfId, long BookId){
        IBookShelf BS=new BookShelfResource();
        int count=BS.addBookToBookShelf(BookshelfId,BookId);
        if(count>0)
            return Response.ok().build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}
