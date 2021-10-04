package com.java.ghmall.dao;

import com.java.ghmall.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int countByUsername(String username);

    int countByUid(Integer uid);

    int countByEmail(String email);

    User selectByUsername(String username);

    User selectByEmail(String email);

    User selectByPhone(String phone);

    Integer selectByReferralUser(String referralCode);

    List<User> selectAll();

}