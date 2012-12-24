package com.abk.smartalarm.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class MongoDBFactory {

    private Mongo mongo;
    private final String username;
    private final String password;
    private final List<ServerAddress> replicaSetSeeds;
    // Tuning params
    private Integer connectionsPerHost;
    private Integer threadsAllowedToBlockForConnectionMultiplier;
    private Integer maxWaitTime;
    private Integer connectTimeout;
    private final boolean readFromSecondary;

    public MongoDBFactory(String uris, String username, String password,
            boolean readFromSecondary) throws UnknownHostException {
        this.readFromSecondary = readFromSecondary;
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new NullPointerException(
                    "username and password cannot be null");
        }

        this.replicaSetSeeds = getServerAddresses(uris);
        this.username = username;
        this.password = password;
    }

    public void setConnectionsPerHost(Integer connectionsPerHost) {
        if (this.connectionsPerHost == null) {
            this.connectionsPerHost = connectionsPerHost;
        }
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            Integer threadsAllowedToBlockForConnectionMultiplier) {
        if (this.threadsAllowedToBlockForConnectionMultiplier == null) {
            this.threadsAllowedToBlockForConnectionMultiplier =
                    threadsAllowedToBlockForConnectionMultiplier;
        }
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        if (this.maxWaitTime == null) {
            this.maxWaitTime = maxWaitTime;
        }
    }

    public void setConnectTimeout(Integer connectTimeout) {
        if (this.connectTimeout == null) {
            this.connectTimeout = connectTimeout;
        }
    }

    private List<ServerAddress> getServerAddresses(String uris)
            throws UnknownHostException {
        String[] hosts = uris.split(",");
        List<ServerAddress> serverAddresses =
                new ArrayList<ServerAddress>(hosts.length);
        for (String host : hosts) {
            serverAddresses.add(new ServerAddress(host));
        }
        return serverAddresses;
    }

    private MongoOptions getMongoOptions() {
        MongoOptions mongoOptions = new MongoOptions();

        if (this.connectionsPerHost != null)
            mongoOptions.connectionsPerHost = this.connectionsPerHost;
        if (this.threadsAllowedToBlockForConnectionMultiplier != null)
            mongoOptions.threadsAllowedToBlockForConnectionMultiplier =
                    this.threadsAllowedToBlockForConnectionMultiplier;
        if (this.maxWaitTime != null)
            mongoOptions.maxWaitTime = this.maxWaitTime;
        if (this.connectTimeout != null)
            mongoOptions.connectTimeout = this.connectTimeout;

        return mongoOptions;
    }

    private Mongo getMongo() {
        if (this.mongo == null) {
            synchronized (this) {
                if (this.mongo == null) {
                    this.mongo =
                            new Mongo(this.replicaSetSeeds, getMongoOptions());
                }
            }
        }
        return this.mongo;
    }

    public DB getDatabase(String databaseName) {
        if (StringUtils.trimToNull(databaseName) == null) {
            throw new IllegalArgumentException("Illegal database name: "
                    + databaseName);
        }
        DB db = getMongo().getDB(databaseName);
        if (!db.isAuthenticated()) {
            db.authenticate(this.username, this.password.toCharArray());
        }
        if (readFromSecondary)
            db.setReadPreference(ReadPreference.SECONDARY);
        return db;
    }

}
