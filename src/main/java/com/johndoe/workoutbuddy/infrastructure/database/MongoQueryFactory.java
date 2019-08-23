package com.johndoe.workoutbuddy.infrastructure.database;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;

import static com.johndoe.workoutbuddy.infrastructure.database.MongoQueryConstants.*;

public class MongoQueryFactory {

    public Query usernameQuery(String username) {
        return new Query(Criteria.where(USERNAME).is(username));
    }

    public Query emailQuery(String email) {
        return new Query(Criteria.where(EMAIL).is(email));
    }

    public Query tokenIDQuery(String id) {
        return new Query(Criteria.where(TOKEN_ID).is(id));
    }

    public Query usernameDateQuery(String username, LocalDate date) {
        return new Query(Criteria.where(USERNAME).is(username).and(DATE).is(date));
    }
}
