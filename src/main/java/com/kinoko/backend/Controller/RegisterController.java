package com.kinoko.backend.Controller;

import com.kinoko.backend.Service.UserService;
import com.kinoko.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    UserService userService;

    @Value("${status_code.invalid}")
    private int errorCode;
    @Value("${status_code.success}")
    private int successCode;

    private Map<String, Object> message(String message, int code){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", message);
        map.put("code", code);
        return map;
    }

    @PostMapping("/register")
    public Map<String, Object> main(@RequestBody User user){
        User res = userService.getByName(user);
        user.setId(DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12));
        if(res == null){
            System.out.println(user);
            userService.insertNewUser(user);
            return message("添加管理员成功", successCode);
        }else{
            return message("用户已存在",errorCode);
        }
    }






}
