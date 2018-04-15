package com.giang.kesach.ws;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giang.kesach.models.Category;
import com.giang.kesach.resources.CategoryResource;

import java.util.List;

@Path("genre")
public class CategoryWs {
	private static final CategoryResource CR =new CategoryResource();

	@POST
	@Path("add")
	@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
	public Response addCategory(String cName) {
		int count=CR.addNewCategory('"'+cName+'"');
		if(count==0)
			return  Response.serverError().build();
		return Response.ok().build();
	}
	@GET
	@Path("{name}")
    @Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
    public Category getCategory(@PathParam("name") String name){
	    return CR.getCategory(name);
    }

	@GET
	@Path("{id}/books")
	@Produces("application/json")
	public Response getBooks(@PathParam("id")int id){

		return Response.ok(CR.getBookUnderCategory(id)).build();
	}


	@GET
	@Produces("application/json")
	public List<Category> getAllCategory(){

		return  CR.getAllCategory();

	}
	@DELETE
	@Path("{id}")
	@Consumes({"application/json","text/plain"})
	public Response deleteCategory(@PathParam("id") int id){
		int count=CR.deleteCategory(id);
		if (count==0){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

}
