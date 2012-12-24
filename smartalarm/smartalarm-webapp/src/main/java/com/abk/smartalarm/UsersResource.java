package com.abk.smartalarm;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.abk.smartalarm.model.User;
import com.abk.smartalarm.model.Users;
import com.abk.smartalarm.util.MongoUtil;

@Path("/users")
public class UsersResource {

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        List<User> userList = new ArrayList<User>();
        User u1 = new User();
        u1.setId("1");
        u1.setName("kunal");
        
        User u2 = new User();
        u2.setId("2");
        u2.setName("adam");
        
        User u3 = new User();
        u3.setId("3");
        u3.setName("brandon");
        
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        Users users = new Users(userList);
        return Response.ok(users).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user){
        System.out.println(user);
        MongoUtil.getMongoDataAccessObject().addUser(user);
        return Response.status(Status.CREATED).build();
    }
    
}
