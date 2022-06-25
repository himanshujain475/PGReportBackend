package com.example.PgReport.expenditure;

import com.example.PgReport.Utility.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@Document(collection = "expenditure")
public class ExpenditureBO {

    @Transient
    private static IdGenerator idg = new IdGenerator();

    @Id
    private String _id = idg.getId();


    private String category;

    private String month;


    private Double spendMoney;

    private List<Map<String,Object>> history;

    private Date createdAt ;

    private Date updatedAt;

}
