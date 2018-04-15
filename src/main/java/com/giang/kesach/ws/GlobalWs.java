package com.giang.kesach.ws;


import com.giang.kesach.models.Author;
import com.giang.kesach.resources.AuthorResource;
import com.giang.kesach.resources.BookResource;
import com.giang.kesach.resources.BookShelfResource;
import com.giang.kesach.resources.CategoryResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("search")
public class GlobalWs {
    @POST
    @Consumes("text/plain")
    @Produces("application/json")
    public Response search(String keyWord){
        List<Object> result= new ArrayList<>();
        result.addAll(BookResource.searchBook(keyWord));
        result.addAll(CategoryResource.searchBook(keyWord));
        result.addAll(AuthorResource.searchAuthor(keyWord));
        return Response.ok(result).build();
    }

}
