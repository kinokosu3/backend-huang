package com.kinoko.backend;

import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.Service.UserService;
import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.mapper.SqlProvider;
import com.kinoko.backend.pojo.BillView;
import com.kinoko.backend.pojo.Patient;
import com.kinoko.backend.pojo.Staff;
import com.kinoko.backend.pojo.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@SpringBootTest

class BackendApplicationTests {

    @Autowired
    DataService dataService;
    @Autowired
    UserService userService;
    @Autowired
    DataMapper dataMapper;

    public void testReflect(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        String buf = fields[0].toString();
//        String[] arrays = buf.split("\\.");
        for (Field field : fields) {
            System.out.println(field);
            String[] arrays = field.toString().split("\\.");
            System.out.println(Arrays.toString(arrays));
        }

//        System.out.println(arrays[arrays.length-1]);
//        System.out.println(clazz.getSimpleName());
    }


    @Test
    void contextLoads() {
//        Patient p = dataService.getAllPatientData().get(0);
//        SqlProvider sqlProvider = new SqlProvider();
//        System.out.println(sqlProvider.updateItem(p));

//        List<Staff> list= dataService.getAllStaffData();
////        Map<String, List<Staff>> res = new HashMap<>();
//        Map<String, List<Integer>> test = new HashMap<String, List<Integer>>(){{
//            put("a", Lists.newArrayList(1, 2));
//        }};
//        List<Integer> a = test.get("a");
//        a.add(3);
//
//
//        System.out.println(test.get("a"));
//        for (int i=0;i<list.size();i++){
//            if(i == 0) {
//                List<Staff> a = Lists.newArrayList(list.get(i));
//                res.put(list.get(i).getOffice(), a);
//                continue;
//            }
//            if(res.containsKey(list.get(i).getOffice())){
//
//            }
//        }
//        List<BillView> buf = dataService.getBillViewList();
//        buf.forEach(item->item.setDrugCount(dataService.getDrugBillCount(item.getId())));
//        System.out.println(dataService.getDrugBillList("b31350b5487"));
//        User user = new User();
//        user.setPassword("1");
//        user.setUsername("1");
//        user.setId("123");
//        testReflect(user);
        System.out.println(dataMapper.countBillPrice());
    }

}
