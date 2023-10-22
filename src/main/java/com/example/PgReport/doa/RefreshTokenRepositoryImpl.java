package com.example.PgReport.doa;

import com.example.PgReport.expenditure.ExpenditureBO;
import com.example.PgReport.model.RefreshTokenBO;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public RefreshTokenBO findByToken(String token) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("token").is(token)),
                Aggregation.project()
                        .and("token").as("token")
                        .and("mobile").as("mobile")
                        .and("expiryDate").as("expiryDate")
        );
        return    (RefreshTokenBO)  mongoTemplate.aggregate(aggregation, "refreshToken", RefreshTokenBO.class).getMappedResults().get(0);


    }

    @Override
    public void deleteByRefreshToken(String token) {
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("token").is(token));
        mongoTemplate.remove(query1, RefreshTokenBO.class);

    }

    @Override
    public void save(RefreshTokenBO refreshTokenBO) {
        mongoTemplate.insert(refreshTokenBO);
    }


}
