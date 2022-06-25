package com.example.PgReport.expenditure;

import com.example.PgReport.Utility.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;


@Getter
@Setter
public class Expenditure {

    @Transient
    private static IdGenerator idg = new IdGenerator();

    @Id
    private String _id = idg.getId();

    private ExpenditureBO expenditureBO;

    private Date createdAt;

    private Date updatedAt;

}
