package com.kinoko.backend;

import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.Service.UserService;
import com.kinoko.backend.mapper.SqlProvider;
import com.kinoko.backend.pojo.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest

class BackendApplicationTests {

    @Autowired
    DataService dataService;

    public void testReflect(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        String buf = fields[0].toString();
        String[] arrays = buf.split("\\.");
        System.out.println(arrays[arrays.length-1]);
        System.out.println(clazz.getSimpleName());
    }


    @Test
    void contextLoads() {
        Patient p = dataService.getAllPatientData().get(0);
        SqlProvider sqlProvider = new SqlProvider();
        System.out.println(sqlProvider.updateItem(p));
    }

}
