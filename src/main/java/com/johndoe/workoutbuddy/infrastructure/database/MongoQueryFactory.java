package com.johndoe.workoutbuddy.infrastructure.database;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.Set;

import static com.johndoe.workoutbuddy.infrastructure.database.MongoQueryConstants.*;

public class MongoQueryFactory {

    public Query usernameQuery(String username) {
        return new Query(usernameCriteria(username));
    }

    public Query emailQuery(String email) {
        return new Query(Criteria.where(EMAIL).is(email));
    }

    public Query tokenIDQuery(String id) {
        return new Query(Criteria.where(TOKEN_ID).is(id));
    }

    public Query usernameDateQuery(String username, LocalDate date) {
        return new Query(Criteria.where(USERNAME).is(username).and(DATE).is(DateUtils.toJavaDate(date)));
    }

    public Query idWithin(Set<String> ids) {
        return new Query(Criteria.where(ID).all(ids));
    }

    public Query usernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate) {
        return new Query(usernameCriteria(username).andOperator(dateBetween(startDate, endDate)));
    }

    private Criteria usernameCriteria(String username) {
        return Criteria.where(USERNAME).is(username);
    }

    private Criteria dateBetween(LocalDate startDate, LocalDate endDate) {
        return  Criteria.where(DATE).gte(startDate).and(DATE).lte(endDate);
    }
}
