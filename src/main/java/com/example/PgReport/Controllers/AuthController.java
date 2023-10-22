package com.example.PgReport.Controllers;


import com.example.PgReport.User.UserBO;
import com.example.PgReport.User.UserBOService;
import com.example.PgReport.doa.RefreshTokenRepository;
import com.example.PgReport.model.AuthenticationRequest;
import com.example.PgReport.model.AuthenticationResponse;
import com.example.PgReport.model.AuthenticationToken;
import com.example.PgReport.model.RefreshTokenBO;
import com.example.PgReport.service.RefreshTokenService;
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
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
        Long mobile = authenticationRequest.getMobile();
        String password = authenticationRequest.getPin();
        String salt  = authenticationRequest.getSalt();
        UserBO user = new UserBO();
        user.setMobile(mobile);
        user.setPin(password);
        user.setSalt(salt);
       boolean checker= userBOService.addUser(user);

       if(checker == true){
           return  ResponseEntity.ok(new AuthenticationResponse("200","valid for "+mobile,"",null));
       }
       else{
           return  ResponseEntity.ok(new AuthenticationResponse("400","User Already existed "+mobile,"" +
                   "",null));
       }


    }

    @PostMapping("/userPresent")
    private ResponseEntity<?> userPresent(@RequestBody AuthenticationRequest authenticationRequest){
        Long mobile = authenticationRequest.getMobile();

        UserBO userBO  = userBOService.findByMobile(mobile);

        if(userBO != null){
            return  ResponseEntity.ok(new AuthenticationResponse("400","User Already existed "+mobile, "",userBO.getSalt()));
        }
        else{
            return  ResponseEntity.ok(new AuthenticationResponse("200","User Does not exits","",null));
        }


    }



    @PostMapping("/auth")
    private ResponseEntity<?> authClient(@RequestBody AuthenticationRequest authenticationRequest){
        Long mobile = authenticationRequest.getMobile();
        String pin = authenticationRequest.getPin();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobile,pin));

        }
        catch (Exception e){
            return  ResponseEntity.ok(new AuthenticationResponse("400","Exception for "+mobile,"",null));
        }

        UserDetails loadedUser = userService.loadUserByUsername(Long.toString(mobile));

        String generatedToken = jwtUtils.generateToken(loadedUser);

        return  ResponseEntity.ok(new AuthenticationResponse("200",generatedToken,"",null));
    }

    @PostMapping("/refreshToken")
    private ResponseEntity<?> refreshToken(@RequestBody AuthenticationToken authenticationRequest){
        String refreshToken = authenticationRequest.getRefreshToken();
        RefreshTokenBO resfreshTokenB0 = refreshTokenService.findByToken(refreshToken);
        //check expiry
       boolean checker = refreshTokenService.verifyExpiration(resfreshTokenB0);
       if(checker){
           UserDetails loadedUser = userService.loadUserByUsername(Long.toString(resfreshTokenB0.getMobile()));

           String generatedToken = jwtUtils.generateToken(loadedUser);
           return  ResponseEntity.ok(new AuthenticationResponse("200",generatedToken,"",null));
       }
       else{
           return ResponseEntity.ok(new AuthenticationResponse("400","Refresh token Expired","",null));
       }

       // return  ResponseEntity.ok(new AuthenticationResponse("200",generatedToken,null));
    }


}
