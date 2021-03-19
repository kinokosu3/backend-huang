package com.kinoko.backend.Controller;


import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.pojo.Bill;
import com.kinoko.backend.pojo.BillView;
import com.kinoko.backend.pojo.DrugBill;
import com.kinoko.backend.pojo.DrugBillListPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @PostMapping("/bill/new")
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

    @ServiceTokenRequired
    @GetMapping("/bill")
    public List<BillView> main(){
        List<BillView> buf = dataService.getBillViewList();
        buf.forEach(item->item.setDrugCount(dataService.getDrugBillCount(item.getId())));
        return buf;
    }


    @ServiceTokenRequired
    @PostMapping("/bill/drugPreview")
    public List<DrugBillListPreview> drugList(@RequestBody String id){
        return dataService.getDrugBillList(id);
    }
}
