package com.example.demo.Controller;


import com.example.demo.Models.LoginRequest;
import com.example.demo.Models.UserInfModel;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public UserController(UserService userService, AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public void addUser(@RequestBody UserInfModel model){
        userService.addUser(model);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getTcNo(), loginRequest.getPassword())); //ilk deger loadByUserName'e gider



        //    SecurityContextHolder.getContext().setAuthentication(authentication);

            //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          //7  String username = authentication.getName();  // Giriş yapan username burada tcsi cunku userdetails sınıfında usernama tc dondurudk

            UserInfModel user = userService.findUserByTcNo(loginRequest.getTcNo());

            return jwtService.generateToken(user);


        } catch (Exception e) {
            e.printStackTrace(); // Konsola tam exception çıkar

            return "Giriş Hatali ";
        }
    }

}
