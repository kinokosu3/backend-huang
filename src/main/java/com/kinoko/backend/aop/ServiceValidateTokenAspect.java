package com.kinoko.backend.aop;

import com.alibaba.fastjson.JSONObject;
import com.kinoko.backend.Service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.kinoko.backend.tools.AesEncryptUtils.decrypt;


/**
 * @author su
 */
@Aspect
@Component
public class ServiceValidateTokenAspect{
    @Value("${encrypt.key}")
    private String key;
    @Autowired
    UserService userService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("@annotation(com.kinoko.backend.aop.ServiceTokenRequired)")
    public void validateToken(){}


    @Around("validateToken()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String token = request.getHeader("token");

        String decrypt = decrypt(token, key);

        Map<String, Object> user  = JSONObject.parseObject(decrypt);
        Map<String, Object> res = new HashMap<String, Object>();

        if(userService.getByNamePwd(user) != null){
            result = joinPoint.proceed();
        }else{
            PrintWriter writer=null;
            HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
            assert response != null;
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.setContentType("application/json;charset=UTF-8");
            res.put("msg", "TOKEN不存在");
            res.put("code", "108");
            writer=response.getWriter();
            writer.write(JSONObject.toJSONString(res));
            writer.flush();
        }
        return result;
    }
}
