package com.ApnaMart.demo.Repository;

import com.ApnaMart.demo.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
     UserInfo findByUsername(String Username);
}
