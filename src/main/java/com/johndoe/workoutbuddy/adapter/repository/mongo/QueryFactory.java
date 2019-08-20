package com.johndoe.workoutbuddy.adapter.repository.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;

import static com.johndoe.workoutbuddy.adapter.repository.mongo.QueryConstants.*;

class QueryFactory {

    Query usernameQuery(String username) {
        return new Query(Criteria.where(USERNAME).is(username));
    }

    Query emailQuery(String email) {
        return new Query(Criteria.where(EMAIL).is(email));
    }

    Query tokenIDQuery(String id) {
        return new Query(Criteria.where(TOKEN_ID).is(id));
    }

    Query usernameDateQuery(String username, LocalDate date) {
        var criteria = Criteria.where(USERNAME).is(username).and(DATE).is(date);
        System.out.println(criteria);
        var query = new Query(criteria);
        System.out.println(query);
        return query;
    }
}
