package com.johndoe.workoutbuddy.configuration.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value( "${mongodb.user}" )
    private String user;

    @Value( "${mongodb.pass}" )
    private String password;

    @Value( "${mongodb.url}" )
    private String uri;

    @Value( "${mongodb.database}" )
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(makeURI()));
    }

    private String makeURI() {
        return uri.replace("{user}", user).replace("{pass}", password).replace("{db}", database);
    }
}
