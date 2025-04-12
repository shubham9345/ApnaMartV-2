package com.ApnaMart.demo.Service;

import com.ApnaMart.demo.Model.UserInfo;
import com.ApnaMart.demo.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo AddUser(UserInfo user) {
        UserInfo users = userInfoRepository.findByUsername(user.getUsername());
//        if(user!=null){
//            throw new IllegalArgumentException("Username is already taken");
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userInfoRepository.save(user);
    }
}
