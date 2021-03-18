package com.kinoko.backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.kinoko.backend.Service.UserService;
import com.kinoko.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.kinoko.backend.tools.AesEncryptUtils.encrypt;


/**
 * @author su
 */
@RestController
public class LoginController{
    @Value("${encrypt.key}")
    private String key;
    @Autowired
    UserService userService;
    private Map<String, Object> build(boolean loginStatus, String encrypt){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("LoginSuccess",loginStatus);
        map.put("userToken", encrypt);
        return map;
    }
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> main(@RequestBody User user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        if(userService.getByNamePwd(map) == null){
            return build(false, null);
        }else {
            String content = JSONObject.toJSONString(map);
            String encrypt = encrypt(content, key);
            return build(true, encrypt);
        }
    }
}

