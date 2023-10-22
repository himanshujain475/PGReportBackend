package com.example.PgReport.User;

import com.example.PgReport.common.ResponseBody;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserBOService userService;

    @PostMapping("/updateUserDetails")
    public ResponseEntity<?> updateUser(@RequestBody UserDetailsBO userDetailsBO){

          userService.addUserDetails(userDetailsBO);
        return new ResponseEntity<>(new ResponseBody(200,"Success","User added Succesfully",null), HttpStatus.OK);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestParam String mobile){

        return new ResponseEntity<>(new ResponseBody(200,"Success","User fetch Successfully",userService.getUserDetails(Long.parseLong(mobile))), HttpStatus.OK);
    }
}
