package com.abk.smartalarm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.abk.smartalarm.model.User;
import com.abk.smartalarm.util.MongoUtil;

@Path("/users/{id}")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam(value = "id") String id){
        System.out.println(id);
        final User user = MongoUtil.getMongoDataAccessObject().getUser(id);
        if(user == null){
            return Response.status(404).entity("User with id : "+id+" not found").build();
        }else{
            return Response.status(200).entity(user).build();
        }
    }
    
}
