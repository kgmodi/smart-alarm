package com.abk.smartalarm.util;

import java.net.UnknownHostException;

import com.abk.smartalarm.mongo.MongoDBFactory;
import com.abk.smartalarm.mongo.dao.MongoDataAccessObject;
import com.mongodb.DB;

public class MongoUtil {
    public static MongoDBFactory mongoDBFactory;
    public static MongoDataAccessObject mongoDataAccessObject;
    static{
        try {
            mongoDBFactory = new MongoDBFactory("localhost", "kunal", "password", false);
            mongoDataAccessObject = new MongoDataAccessObject(mongoDBFactory, "users");
           
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String [] args){
        mongoDBFactory.getDatabase("users");
    }
    
    
    /**
     * @return the mongoDBFactory
     */
    public static MongoDBFactory getMongoDBFactory() {
        return mongoDBFactory;
    }
    
    public static DB getDatabase(){
        return mongoDBFactory.getDatabase("users");

    }


    /**
     * @return the mongoDataAccessObject
     */
    public static MongoDataAccessObject getMongoDataAccessObject() {
        return mongoDataAccessObject;
    }

}
