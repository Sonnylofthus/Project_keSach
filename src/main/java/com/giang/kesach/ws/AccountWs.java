package com.giang.kesach.ws;

import com.giang.kesach.models.Account;
import com.giang.kesach.models.BookShelf;
import com.giang.kesach.models.UserInfo;
import com.giang.kesach.resources.BookShelfResource;
import com.giang.kesach.resources.ReadListResource;
import com.giang.kesach.resources.UserAccountResource;
import com.giang.kesach.resources.UserInfoResource;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("account")
public class AccountWs {
    private UserAccountResource UAR=new UserAccountResource();
    @POST
    @Path("login")
    @Consumes({"application/json","application/xml"})
    @Produces("application/json")
    public Response login(Account account){
        if(UAR.checkLoginInfo(account)==1) {

            UAR.setToken(account);
            return Response.ok(account).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/{id}")
    @Consumes("text/plain")
    @Produces("application/json")
    public Account getAccount(@PathParam("id") int id){
        return UAR.getAccountById(id);
    }
    @GET
    @Path("{id}/profile")
    @Consumes("text/plain")
    @Produces("application/json")
    public Response getProfile(@PathParam("id") int id,@HeaderParam("token") long token){
        if(UAR.isTokenCorrect(id,token)) {
            UserInfoResource userInfoResource = new UserInfoResource();

            return Response.ok(userInfoResource.getUserInfo(id)).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    @POST
    @Path("{id}/profile/create")
    @Consumes("application/json")
    public Response createProfile(@PathParam("id")int id, UserInfo userInfo,@HeaderParam("token") long token) {
        if(UAR.isTokenCorrect(id,token)) {
            UserInfoResource userInfoResource = new UserInfoResource();
            if (userInfoResource.createUserInfo(id, userInfo) != 0)
                return Response.ok(userInfoResource.getUserInfo(id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
        }
    @PUT
    @Path("/{id}/profile/update")
    @Consumes("application/json")
    public Response updateProfile(@PathParam("id") int id, UserInfo userInfo,@HeaderParam("token") long token) {
        if(UAR.isTokenCorrect(id,token)) {
            UserInfoResource userInfoResource = new UserInfoResource();
            if (userInfoResource.isUserInfoExist(id))
                if (userInfoResource.modifyProfile(id, userInfo) != 0)
                    return Response.ok(userInfoResource.getUserInfo(id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();


    }

    @GET
    @Path("{id}/readlist")
    @Produces("application/json")
    public Response getReadList(@PathParam("id") int id){

        ReadListResource readListResource=new ReadListResource();
        return Response.ok(readListResource.getReadList(id)).build();
    }
    @GET
    @Path("{id}/bookshelves")
    @Produces("application/json")
    public Response getAllbookshelf(@PathParam("id") int id){

        return Response.ok(UAR.showAllBookShelf(id)).build();
    }

    @POST
    @Path("{id}/bookshelves/new")
    @Produces("application/json")
    public Response createNewBookShelf(@PathParam("id")int accountId, BookShelf bookShelf){
        return Response.ok(UAR.createNewBookShelf(accountId,bookShelf)).build();
    }
    @DELETE
    @Path("{id}/bookshelves/{idShelf}/delete")
    public Response removeBookFromShelf(@PathParam("id")int accountId,@PathParam("idShelf") int idShelf,@HeaderParam("token")long token){
    if(UserAccountResource.isTokenCorrect(accountId,token))
        UAR.deleteBookShelf(accountId,idShelf);
    return Response.ok().build();
    }
    @POST
    @Path("new")
    @Consumes({"application/json","application/xml"})
    @Produces("application/json")
    public Response signUp(Account account){
        if(!(account.getUsername()==null)&&!(account.getPassword()==null)) {
            if (UAR.createNewAccount(account) != 0) {
                account=UAR.getAccountByName(account.getUsername());
                return Response.ok(account).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Path("/{id}/signout")
    public  Response signOut(@HeaderParam("token") long token,@PathParam("id") int id){
        if(UAR.isTokenCorrect(id,token))
            UAR.deleteToken(id);

            return Response.ok().build();
    }

}
