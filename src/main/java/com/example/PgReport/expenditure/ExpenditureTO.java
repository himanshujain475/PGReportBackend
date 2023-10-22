package com.example.PgReport.expenditure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpenditureTO {
    public Long expenditureId;
    public Long monthId;
    public String type;
    public  String name;
    public Long amount;
   public Date createdOn;
}