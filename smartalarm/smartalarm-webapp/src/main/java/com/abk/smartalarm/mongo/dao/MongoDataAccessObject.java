package com.abk.smartalarm.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abk.smartalarm.model.Alarm;
import com.abk.smartalarm.model.Alarms;
import com.abk.smartalarm.model.User;
import com.abk.smartalarm.mongo.MongoDBFactory;
import com.abk.smartalarm.util.MongoUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class MongoDataAccessObject {
    private static final String USERS = "users";
    private final MongoDBFactory factory;
    private final String database;
    private final static Logger logger = LoggerFactory
            .getLogger(MongoDataAccessObject.class);

    public MongoDataAccessObject(MongoDBFactory factory, String database) {
        this.factory = factory;
        this.database = database;
    }

    private DBCollection getUsersCollection() {
        DB db = this.factory.getDatabase(this.database);
        return db.getCollectionFromString(USERS);
    }

    public void addUser(User user) {
        try {
            WriteResult result =
                    getUsersCollection().save(MongoUser.toDBObject(user),
                            WriteConcern.SAFE);
            if (result.getError() != null)
                logger.error(result.getError());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public User getUser(String uid) {
        try {
            final DBObject result =
                    getUsersCollection().findOne(
                            BasicDBObjectBuilder.start().add("_id", uid).get());
            if (result == null)
                return null;
            else {
                return MongoUser.toUser(result);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static void main(String[] args) {
        final MongoDBFactory mongoDBFactory = MongoUtil.getMongoDBFactory();
        MongoDataAccessObject accessObject =
                new MongoDataAccessObject(mongoDBFactory, "users");
        User user = new User();
        user.setId("1");
        user.setName("kunal");
        Alarm alarm = new Alarm();
        alarm.setAlarmName("test 1");
        alarm.setAlarmTime("00:29");
        Alarms alarms = new Alarms();
        List<Alarm> alarmList = new ArrayList();
        alarmList.add(alarm);
        alarms.setAlarms(alarmList);
        user.setAlarms(alarms);
        accessObject.addUser(user);
    }

    public void addAlarm(String uid, Alarm alarm) {
        try {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("_id", uid);
            final DBObject user = getUsersCollection().findOne(basicDBObject);
            final Object object = user.get("alarms");
            BasicDBList basicDBList = null;
            if (object == null) {
                basicDBList = new BasicDBList();
            } else {
                basicDBList = (BasicDBList) object;
            }
            alarm.setId(Long.toString(System.nanoTime()));
            basicDBList.add(MongoUser.toDBObject(alarm));
            user.put("alarms", basicDBList);
            WriteResult writeResult =
                    getUsersCollection().update(
                            BasicDBObjectBuilder.start().append("_id", uid)
                                    .get(), user, true, false);
            if (writeResult.getError() != null)
                logger.error(writeResult.getError());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public User findUser(User user) {
        final String password = user.getPassword();
        final String uid = user.getId();
        final DBObject result =
                getUsersCollection().findOne(
                        BasicDBObjectBuilder.start().add("_id", uid).add(
                                "password", password).get());
        if (result == null) {
            throw new RuntimeException("User/Password invalid");
        } else {
            return MongoUser.toUser(result);
        }

    }

    public Alarms getAlarms(String uid) {
        Alarms alarms = new Alarms();
        try {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("_id", uid);
            final DBObject user = getUsersCollection().findOne(basicDBObject);
            final Object object = user.get("alarms");
            BasicDBList basicDBList = null;
            if (object == null) {
                return alarms;
            } else {
                basicDBList = (BasicDBList) object;
            }
            List<Alarm> alarmList = new ArrayList<Alarm>();
            for (Object dbAlarm : basicDBList) {
                alarmList.add(MongoUser.toAlarm((DBObject)dbAlarm));
            }
            alarms.setAlarms(alarmList);
            return alarms;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return alarms;
    }

    public Alarm getAlarmById(String uid, String aid) {
        final Alarms alarms = getAlarms(uid);
        if(alarms != null){
            List<Alarm> alarm = alarms.getAlarms();
            for (Alarm alarm2 : alarm) {
                if(alarm2.getId().equals(aid)){
                    return alarm2;
                }
            }
        }
        return null;
    }

}
