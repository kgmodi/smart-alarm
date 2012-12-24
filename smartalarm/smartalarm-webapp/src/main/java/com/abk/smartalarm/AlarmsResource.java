package com.abk.smartalarm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.abk.smartalarm.model.Alarm;
import com.abk.smartalarm.model.Alarms;
import com.abk.smartalarm.util.MongoUtil;

@Path("/users/{id}/alarms")
public class AlarmsResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response
            createAlarm(@PathParam(value = "id") String uid, Alarm alarm) {
        System.out.println("alarm" + alarm);
        MongoUtil.getMongoDataAccessObject().addAlarm(uid, alarm);
        final ResponseBuilder responseBuilder = Response.status(201);
        return responseBuilder.build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlarms(@PathParam(value = "id") String uid) {
        Alarms alarms = MongoUtil.getMongoDataAccessObject().getAlarms(uid);
        return Response.status(200).entity(alarms).build();
    }

}
