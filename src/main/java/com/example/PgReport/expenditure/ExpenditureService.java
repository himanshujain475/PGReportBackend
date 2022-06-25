package com.example.PgReport.expenditure;

import java.util.List;

public interface ExpenditureService {

    public void addExpenditure(ExpenditureTO expenditureBO);

    public void showExpenditure(ExpenditureBO expenditureBO);

    public List<ExpenditureBO> showExpenditureForParticularMonth();
}
