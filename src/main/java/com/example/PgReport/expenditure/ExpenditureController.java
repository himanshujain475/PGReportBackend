package com.example.PgReport.expenditure;


import com.example.PgReport.User.UserBO;
import com.example.PgReport.User.UserTO;
import com.example.PgReport.common.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @PostMapping("/addExpenditure")
    public ResponseEntity<?> addExpenditure(@RequestBody ExpenditureTO expenditureTo){
        expenditureService.addExpenditure(expenditureTo);
        return new ResponseEntity<>(new ResponseBody(200,"Success","Expenditure Saved Successfully",null),HttpStatus.OK);
    }


    @GetMapping("/showExpenditure")
    public ResponseEntity<?> addExpenditure(){

        return new ResponseEntity<>(new ResponseBody(200,"Success","Expenditure Get Successfully",expenditureService.showExpenditureForParticularMonth()),HttpStatus.OK);
    }


}
