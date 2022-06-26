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
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();

        UserBO user = new UserBO();
        user.setUserName(userName);
        user.setPassword(password);
       boolean checker= userBOService.addUser(user);

       if(checker == true){
           return  ResponseEntity.ok(new AuthenticationResponse("valid for "+userName));
       }
       else{
           return  ResponseEntity.ok(new AuthenticationResponse("User Already existed "+userName));
       }


    }


    @PostMapping("/auth")
    private ResponseEntity<?> authClient(@RequestBody AuthenticationRequest authenticationRequest){
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        }
        catch (Exception e){
            return  ResponseEntity.ok(new AuthenticationResponse("Exception for "+userName));
        }

        UserDetails loadedUser = userService.loadUserByUsername(userName);

        String generatedToken = jwtUtils.generateToken(loadedUser);

        return  ResponseEntity.ok(new AuthenticationResponse(generatedToken));
    }
}
