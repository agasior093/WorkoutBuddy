package com.johndoe.workoutbuddy.adapter.repository.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static com.johndoe.workoutbuddy.adapter.repository.mongo.QueryConstants.*;

class QueryBuilder {

    Query usernameQuery(String username) {
        return new Query(Criteria.where(USERNAME).is(username));
    }

    Query emailQuery(String email) {
        return new Query(Criteria.where(EMAIL).is(email));
    }
}
