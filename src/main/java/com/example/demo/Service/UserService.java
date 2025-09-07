package com.example.demo.Service;

import com.example.demo.Models.UserInfModel;
import com.example.demo.Models.UserInfPasswordModel;
import com.example.demo.Repository.UserInfPasswordRepository;
import com.example.demo.Repository.UserInfRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserInfRepository userInfRepository;
    private final UserInfPasswordRepository userInfPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserInfRepository userInfRepository, UserInfPasswordRepository userInfPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userInfRepository = userInfRepository;
        this.userInfPasswordRepository = userInfPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserInfModel user) {

       Integer userId =  userInfRepository.addUser(user);

        String defaultPassword = passwordEncoder.encode("ali123");

        UserInfPasswordModel pass = new UserInfPasswordModel();
        pass.setUserId(userId);
        pass.setPassword(defaultPassword);

        userInfPasswordRepository.insertPassword(pass);
    }
}
