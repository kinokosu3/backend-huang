package com.kinoko.backend.mapper;


import com.kinoko.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {


    @Select("select * from admin_user where username = #{username} and password = #{password}")
    User searchUser(Map<String, Object> map);
}
