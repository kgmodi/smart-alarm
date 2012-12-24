package com.abk.smartalarm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.abk.smartalarm.model.User;
import com.abk.smartalarm.util.MongoUtil;

@Path("/login")
public class LoginResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user){
        System.out.println(user);
        try{
            final User findUser = MongoUtil.getMongoDataAccessObject().findUser(user);
            return Response.status(Status.OK).entity(findUser).build();
        }catch(Exception ex){
            return Response.status(Status.FORBIDDEN).entity(ex.getMessage()).build();
        }
    }
}
