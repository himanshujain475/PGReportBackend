package com.example.PgReport.expenditure;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface ExpenditureRepo {

    void addExpenditure(ExpenditureBO expenditureBO);

    void updateExpenditure(org.springframework.data.mongodb.core.query.Update update, String id);

    ExpenditureBO getExpenditureByMonthAndCategory(ExpenditureBO expenditureBO);

    List<ExpenditureBO> getExpenditureByMonth(String month);
}
