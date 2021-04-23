package com.kinoko.backend.Service;


import com.kinoko.backend.mapper.UserMapper;
import com.kinoko.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getByNamePwd(Map<String, Object> map){
        return userMapper.searchUserWithMap(map);
    }

    public User getByName(User user){
        return userMapper.searchUser(user);
    }

    public void insertNewUser(User user){
        System.out.println(user);
        userMapper.insertNewUser(user);
    }
//    public List<User> searchMatchUser(String val){
//        return userMapper.searchMatchUser(val);
//    }
}
