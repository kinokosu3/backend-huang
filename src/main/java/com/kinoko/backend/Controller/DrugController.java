package com.kinoko.backend.Controller;


import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.pojo.Drug;
import com.kinoko.backend.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DrugController {
    @Autowired
    DataService dataService;

    @Value("${status_code.invalid}")
    private int errorCode;
    @Value("${status_code.success}")
    private int successCode;


    private Map<String, Object> message(String errorMessage, int code){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", errorMessage);
        map.put("code", code);
        return map;
    }

    @ServiceTokenRequired
    @GetMapping("/drug")
    @ResponseBody
    public Object main() {
        return dataService.getAllDrugData();
    }
    @ServiceTokenRequired
    @PostMapping("/drug/new")
    public Map<String, Object> addData(@RequestBody Drug drug){

        // 验证
        drug.setId(DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12));
        dataService.addNewDrugItem(drug);
        return message("添加成功", successCode);
    }

    @ServiceTokenRequired
    @PostMapping("/drug/edit")
    public Map<String, Object> editData(@RequestBody Drug drug){
        dataService.editDrugItem(drug);
        return message("修改成功", successCode);
    }
    @ServiceTokenRequired
    @PostMapping("/drug/delete")
    public Map<String, Object> deleteData(@RequestBody String id){
        // return message
        dataService.deleteDrugItem(id);
        return message("删除成功", successCode);
    }
}
