package com.example.PgReport.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImpl implements UserRepo{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void addUser(UserBO user) {
        mongoTemplate.insert(user);
    }

    @Override
    public UserBO findByMobile(int mobile){
        Query query = Query.query(Criteria.where("mobile").is(mobile));
        return mongoTemplate.findOne(query,UserBO.class);
    }
}
