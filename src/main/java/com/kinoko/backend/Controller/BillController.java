package com.kinoko.backend.Controller;


import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.pojo.Bill;
import com.kinoko.backend.pojo.DrugBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @ServiceTokenRequired
    @PostMapping("/bill")
    //Bill(id=, name_id=ae123c,
    // doctor_id=c1f8b1be97e,
    // drug_list={2=2, 3=2}, count_price=92.0,
    // check_text=fuck)
    public void main(@RequestBody Bill bill){
        bill.setTime(genTime());
        System.out.println(bill);
        String billID = DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12);
        String drugBillID = DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(3,14);

        // warning
        bill.setId(billID);
        dataService.addNewBillItem(bill);

        bill.getDrug_list().forEach((key, value) -> {
            DrugBill drugBill = new DrugBill();
            drugBill.setId(drugBillID);
            drugBill.setBill_id(billID);
            drugBill.setQuantity(value);
            drugBill.setDrug_id(key);
            drugBill.setPrice_count(dataService.getDrugPrice(key) * value);
            dataService.addNewDrugBillItem(drugBill);
        });
    }

}
