package com.example.PgReport.Controllers;


import com.example.PgReport.User.UserBO;
import com.example.PgReport.User.UserBOService;
import com.example.PgReport.model.AuthenticationRequest;
import com.example.PgReport.model.AuthenticationResponse;
import com.example.PgReport.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserBOService userBOService;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
        Integer userName = authenticationRequest.getMobile();
        Integer password = authenticationRequest.getPin();

        UserBO user = new UserBO();
        user.setMobile(user.getMobile());
        user.setPin(user.getPin());
       boolean checker= userBOService.addUser(user);

       if(checker == true){
           return  ResponseEntity.ok(new AuthenticationResponse("200","valid for "+userName));
       }
       else{
           return  ResponseEntity.ok(new AuthenticationResponse("400","User Already existed "+userName));
       }


    }

    @PostMapping("/userPresent")
    private ResponseEntity<?> userPresent(@RequestBody AuthenticationRequest authenticationRequest){
        int mobile = authenticationRequest.getMobile();

        UserBO userBO  = userBOService.findByMobile(mobile);

        if(userBO != null){
            return  ResponseEntity.ok(new AuthenticationResponse("400","User Already existed "+mobile));
        }
        else{
            return  ResponseEntity.ok(new AuthenticationResponse("200","User Does not exits"));
        }


    }



    @PostMapping("/auth")
    private ResponseEntity<?> authClient(@RequestBody AuthenticationRequest authenticationRequest){
        int mobile = authenticationRequest.getMobile();
        int pin = authenticationRequest.getPin();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobile,pin));

        }
        catch (Exception e){
            return  ResponseEntity.ok(new AuthenticationResponse("400","Exception for "+mobile));
        }

        UserDetails loadedUser = userService.loadUserByUsername(Integer.toString(mobile));

        String generatedToken = jwtUtils.generateToken(loadedUser);

        return  ResponseEntity.ok(new AuthenticationResponse("200",generatedToken));
    }
}
