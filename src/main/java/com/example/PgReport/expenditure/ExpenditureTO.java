package com.example.PgReport.expenditure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpenditureTO {
    private String category;

    private Double spendMoney;

    private String description;

    private String month;

    private Date createdAt;

    private Date updatedAt;
}
