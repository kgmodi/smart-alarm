package com.abk.smartalarm.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import com.abk.smartalarm.model.Alarm;
import com.abk.smartalarm.model.Alarms;
import com.abk.smartalarm.model.User;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoUser {

    public static DBObject toDBObject(User user) {
        BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
        if (user != null) {
            basicDBObjectBuilder.add("name", user.getName()).add("_id",
                    user.getId()).add("number", user.getNumber()).add("password", user.getPassword());
            final Alarms alarms = user.getAlarms();
            if (alarms != null && alarms.getAlarms() != null) {
                BasicDBList dbList = new BasicDBList();
                for (Alarm alarm : alarms.getAlarms()) {
                    if (alarm != null) {
                        dbList.add(toDBObject(alarm));
                    }
                }
                basicDBObjectBuilder.add("alarms", dbList);
            }
        }
        return basicDBObjectBuilder.get();
    }

    public static DBObject toDBObject(Alarm alarm){
        if(alarm != null){
            return BasicDBObjectBuilder.start().add("id",
                    alarm.getId()).add("alarmName",
                            alarm.getAlarmName()).add("alarmType",
                            alarm.getAlarmType()).add("alarmTime",
                            alarm.getAlarmTime()).add("fuid",
                            alarm.getFuid()).add("userId",
                            alarm.getUserId()).get();
        }
        return null;
    }
    
    public static User toUser(DBObject dbObject){
        if(dbObject != null){
            User user = new User();
            user.setId((String)dbObject.get("_id"));
            Alarms alarms = new Alarms();
            List<Alarm> alarmList = new ArrayList<Alarm>();
            final Object object = dbObject.get("alarms");
            if(object != null){
                BasicDBList basicDBList = (BasicDBList)object;
                for (Object alarm : basicDBList) {
                    if(alarm != null){
                        DBObject object2 = (DBObject)alarm;
                        alarmList.add(toAlarm(object2));                    }
                }
                alarms.setAlarms(alarmList);
                user.setAlarms(alarms);
            }
            return user;
        }
        return null;
    }

    public static Alarm toAlarm(DBObject dbObject) {
        Alarm alarm = new Alarm();
        if(dbObject != null){
            alarm.setAlarmName((String)dbObject.get("alarmName"));
            alarm.setAlarmTime((String)dbObject.get("alarmTime"));
            alarm.setFuid((String)dbObject.get("fuid"));
            alarm.setId((String)dbObject.get("_id"));
            alarm.setUserId((String)dbObject.get("userId"));
            return alarm;
        }
        return null;
    }
}
