package com.example.PgReport.expenditure;


import com.example.PgReport.commonFunction.ConvertorClass;
import com.example.PgReport.commonFunction.DateService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenditureServiceImpl implements ExpenditureService{

    @Autowired
    private ExpenditureRepo expenditureRepo;
    @Override
    public void     addExpenditure(ExpenditureTO expenditureTO) {
        ExpenditureBO expenditureBO = ConvertorClass.objectMapper(expenditureTO,ExpenditureBO.class);
        expenditureBO.setMonth(DateService.getTodayDateInParticularType("MMMM"));
        ExpenditureBO expenditureBO1 = expenditureRepo.getExpenditureByMonthAndCategory(expenditureBO);
        if(expenditureBO1 != null &&expenditureBO1.getCategory().equals(expenditureBO.getCategory())){
           addExpenditureObject(expenditureBO1,expenditureTO,"alreadyPresent");
        }
        else{
            addExpenditureObject(expenditureBO,expenditureTO,"createNew");
        }

    }

    @Override
    public void showExpenditure(ExpenditureBO expenditureBO) {

    }

    void addExpenditureObject(ExpenditureBO expenditureBO,ExpenditureTO expenditureTO,String type){
        if(type.equals("alreadyPresent")){
            Update update = new Update();
            update.set("spendMoney", expenditureBO.getSpendMoney()+expenditureTO.getSpendMoney());
            Map<String,Object> map = new HashMap<>();
            map.put("spendMoney",expenditureTO.getSpendMoney());
            map.put("updatedAt", DateService.currDate());
            map.put("description",expenditureTO.getDescription());
            String userName = "";
            map.put("updatedBy",userName);
            expenditureBO.getHistory().add(map);
            update.set("history",expenditureBO.getHistory());
            expenditureRepo.updateExpenditure(update,expenditureBO.get_id());
        }
        else if(type.equals("createNew")){
            Map<String,Object> map = new HashMap<>();
            map.put("spendMoney",expenditureTO.getSpendMoney());
            map.put("updatedAt", DateService.currDate());
            map.put("description",expenditureTO.getDescription());
            String userName = "";
            map.put("updatedBy",userName);
            List<Map<String,Object>> list = new ArrayList<>();
            list.add(map);
            expenditureBO.setHistory(list);
            expenditureBO.setCreatedAt(DateService.currDate());
            expenditureRepo.addExpenditure(expenditureBO);
        }
    }



    @Override
    public List<ExpenditureBO> showExpenditureForParticularMonth() {
    return     expenditureRepo.getExpenditureByMonth(DateService.getTodayDateInParticularType("MMMM"));
    }






}
