package com.kinoko.backend.Controller;


import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    DataService dataService;

    @Value("${status_code.invalid}")
    private int errorCode;
    @Value("${status_code.success}")
    private int successCode;

    private String genTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        return format.format(date);
    }

    private Map<String, Object> message(String errorMessage, int code){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", errorMessage);
        map.put("code", code);
        return map;
    }
    @ServiceTokenRequired
    @PostMapping("/bill/new")
    //Bill(id=, name_id=ae123c,
    // doctor_id=c1f8b1be97e,
    // drug_list={2=2, 3=2}, count_price=92.0,
    // check_text=fuck)
    public Map<String, Object> main(@RequestBody Bill bill){
        bill.setTime(genTime());
        String billID = DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12);
        // warning
        bill.setId(billID);

        dataService.addNewBillItem(bill);
        bill.getDrug_list().forEach((key, value) -> {
            String drugBillID = DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(3,14);
            DrugBill drugBill = new DrugBill();
            drugBill.setId(drugBillID);
            drugBill.setBill_id(billID);
            drugBill.setQuantity(value);
            drugBill.setDrug_id(key);
            drugBill.setPrice_count(dataService.getDrugPrice(key) * value);
            dataService.addNewDrugBillItem(drugBill);
        });
        return message("添加成功", successCode);
    }

    @ServiceTokenRequired
    @GetMapping("/bill")
    public List<BillView> main(){
        List<BillView> buf = dataService.getBillViewList();
        buf.forEach(item->item.setDrugCount(dataService.getDrugBillCount(item.getId())));
        return buf;
    }

    @ServiceTokenRequired
    @PostMapping("/bill/delete")
    public Map<String, Object> deleteData(@RequestBody String id){
        // return message
        dataService.deleteBillItem(id, "Bill");
        return message("删除成功", successCode);
    }




    @ServiceTokenRequired
    @PostMapping ("/bill/drugPreview")
    public List<DrugBillListPreview> drugList(@RequestBody String id){
        return dataService.getDrugBillList(id);
    }
    @ServiceTokenRequired
    @PostMapping("/bill/doctorPreview")
    public Staff doctorData(@RequestBody String id){
        System.out.println(dataService.getOneStaffItem(id));
        return dataService.getOneStaffItem(id);
    }

    @ServiceTokenRequired
    @PostMapping("/bill/patientPreview")
    public Patient patientData(@RequestBody String id){
        System.out.println(dataService.getOnePatientItem(id));
        return dataService.getOnePatientItem(id);
    }
}
