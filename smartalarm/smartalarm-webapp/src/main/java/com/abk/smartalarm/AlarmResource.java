package com.abk.smartalarm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.abk.smartalarm.model.Alarm;
import com.abk.smartalarm.util.MongoUtil;

@Path("/users/{uid}/alarms/{aid}")
public class AlarmResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAlarm(@PathParam(value = "aid") String aid, @PathParam(
            value = "uid") String uid) {
        
        Alarm alarm = MongoUtil.getMongoDataAccessObject().getAlarmById(uid,aid);
        final ResponseBuilder responseBuilder = Response.status(200);

        return responseBuilder.entity(alarm).build();
    }
}
