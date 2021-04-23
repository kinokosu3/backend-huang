package com.kinoko.backend.mapper;


import com.kinoko.backend.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {


    @Select("select * from admin_user where username = #{username} and password = #{password}")
    User searchUserWithMap(Map<String, Object> map);

    @Select("select * from admin_user where username = #{username}")
    User searchUser(User user);

    @Insert("insert into admin_user values(#{id},#{username},#{password})")
    void insertNewUser(User user);

//    @Select("select * from admin_user where username like concat('%',#{val},'%')")
//    List<User> searchMatchUser(String val);
}
