package com.kinoko.backend.Controller;

import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    DataService dataService;

    private static final int ID_CARD_LENGTH = 18;
    private static final int TEL_NUM_LENGTH = 11;

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

    private Map<String, Object> fieldValid(int idCardLength,
                                           int telNumLength,
                                           int ageLength){
        if(idCardLength != ID_CARD_LENGTH){
            return message("身份证号不符合规范",errorCode);
        }else if (telNumLength != TEL_NUM_LENGTH){
            return message("手机号码不符合国内规范",errorCode);
        }else if (ageLength < 0){
            return message("年龄不符合规范",errorCode);
        }
        return message("",successCode);
    }

    @ServiceTokenRequired
    @GetMapping("/patient")
    @ResponseBody
    public Object main() {
        return dataService.getAllPatientData();
    }

    @ServiceTokenRequired
    @PostMapping("/patient/new")
    public Map<String, Object> addData(@RequestBody Patient patient){
        // 验证

        Map<String, Object> message = fieldValid(patient.getIdCard().length(),patient.getTelNum().length(),patient.getAge());
        if (Integer.parseInt(message.get("code").toString()) != errorCode) {
            patient.setId(DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12));
            dataService.addNewPatientItem(patient);
            message.put("msg", "添加成功");
        }
        return message;
    }
    @ServiceTokenRequired
    @PostMapping("/patient/edit")
    public Map<String, Object> editData(@RequestBody Patient patient){

        Map<String, Object> message = fieldValid(
                patient.getIdCard().length(),
                patient.getTelNum().length(),patient.getAge());
        if (Integer.parseInt(message.get("code").toString()) != errorCode) {
            dataService.editPatientItem(patient);
            message.put("msg", "修改成功");
        }
        return message;
    }

    @ServiceTokenRequired
    @PostMapping("/patient/delete")
    public Map<String, Object> deleteData(@RequestBody String id){
        // return message
        dataService.deletePatientItem(id);
        return message("删除成功", successCode);
    }
}
