package com.kinoko.backend.Service;


import com.kinoko.backend.mapper.UserMapper;
import com.kinoko.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getByNamePwd(Map<String, Object> map){
        return userMapper.searchUser(map);
    }

}
