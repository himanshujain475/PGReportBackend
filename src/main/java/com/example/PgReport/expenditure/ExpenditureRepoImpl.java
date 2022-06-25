package com.example.PgReport.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenditureRepoImpl implements ExpenditureRepo{



    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addExpenditure(ExpenditureBO expenditureBO) {
        mongoTemplate.insert(expenditureBO);
    }

    @Override
    public void updateExpenditure(Update update, String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(query, update,"expenditure" );
    }

    @Override
    public ExpenditureBO getExpenditureByMonthAndCategory(ExpenditureBO expenditureBO) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("category").is(expenditureBO.getCategory()).andOperator(Criteria.where("month").is(expenditureBO.getMonth()))),
                Aggregation.project()
                        .and("month").as("month")
                        .and("spendMoney").as("spendMoney")
                        .and("category").as("category")
                        .and("history").as("history")
        );
        return      mongoTemplate.aggregate(aggregation, "expenditure", ExpenditureBO.class).getUniqueMappedResult();
    }

    @Override
    public List<ExpenditureBO> getExpenditureByMonth(String month) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("month").is(month)),
                Aggregation.project()
                        .and("month").as("month")
                        .and("spendMoney").as("spendMoney")
                        .and("category").as("category")
                        .and("history").as("history")
        );
        return    (List<ExpenditureBO>)  mongoTemplate.aggregate(aggregation, "expenditure", ExpenditureBO.class).getMappedResults();

    }


}


 /*
    @Query(value = "SELECT * FROM admin_panel WHERE treatment_code in (?1) AND ins_code = ?2 AND tpa_code = ?3 "
			+ " AND hospital_code in (?4) AND start_time <= DATE(NOW())"
			+ " ORDER BY start_time desc", nativeQuery = true)
	List<AdminPanel> getCurrentAdminCharges(List<String> treatmentCodes, String insCompanyCode, String tpaCode,
			List<String> hospitalCodes);

	@Query(value = "SELECT * FROM admin_panel "
			+ " WHERE city_code = ?1 AND category_name = ?2 AND treatment_code = ?3 "
			+ " AND co_pay = ?4 AND room_id = ?5 AND mode_of_payment = ?6 AND start_time <= DATE(NOW())"
			+ " ORDER BY start_time desc limit 1", nativeQuery = true)
	AdminPanel getAdminCharges(String city, String category, String treatmentCode, Integer coPay, Integer roomId,
			String modeOfPayment);
     */
