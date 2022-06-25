package com.example.PgReport.User;

import com.example.PgReport.common.ResponseBody;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserBOService userService;

    @PostMapping("/addUser1")
    public ResponseEntity<?> addPracticeUser(@RequestBody UserTO userTo){

        UserBO userRepo = new UserBO(userTo.getName(),userTo.getRoomNo());
        userService.addUser(userRepo);
        return new ResponseEntity<>(new ResponseBody(200,"Success","User added Succesfully",userRepo), HttpStatus.OK);
    }
}
