package com.kinoko.backend.Controller;

import com.kinoko.backend.Service.DataService;
import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.pojo.Office;
import com.kinoko.backend.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * @author su
 * 科室管理，增删查改
 * 人员管理，增删查改
 */
@RestController
@RequestMapping("/api")
public class OfficeController {

    @Autowired
    DataService dataService;
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

    @ServiceTokenRequired
    @GetMapping("/office")
    @ResponseBody
    public Object main() {
        List<Staff>staffList = dataService.getAllStaffData();
        List<Office>officeList = dataService.getOfficeAllData();
        Map<String, List<Staff>> res = new HashMap<>();
        for(Staff staff:staffList){
            List<Staff> buf;
            if(staff.getOffice() == null)
                continue;
            if(res.containsKey(staff.getOffice())){
                buf = res.get(staff.getOffice());
                buf.add(staff);
            }else{
                buf = new ArrayList<>() {{
                    add(staff);
                }};
                res.put(staff.getOffice(), buf);
            }
        }
        for(Office office:officeList){
            if(!res.containsKey(office.getName())){
                res.put(office.getName(), Collections.emptyList());
            }
        }
        return res;
    }
    @ServiceTokenRequired
    @PostMapping("/office/delete")
    public Map<String, Object> deleteOffice(@RequestBody String name){
        dataService.deleteOfficeData(name);
        return message("删除成功", successCode);
    }
    @ServiceTokenRequired
    @PostMapping("/office/new")
    public Map<String, Object> newOffice(@RequestBody Office office){
        if(dataService.getOfficeOneData(office.getName()) != null){
            return message("该科室名已经存在", errorCode);
        }
        office.setId(DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12));
        dataService.newOfficeData(office);
        return message("添加成功", successCode);
    }


    @ServiceTokenRequired
    @PostMapping("/officeManage/edit")
    public Map<String, Object> editData(@RequestBody Staff staff){
        dataService.editOfficeStaffItem(staff);
        return message("修改成功",successCode);
    }

    @ServiceTokenRequired
    @PostMapping("/officeManage/new")
    public Map<String, Object> addData(@RequestBody Staff staff){
        staff.setId(DigestUtils.md5DigestAsHex(Long.toString(System.currentTimeMillis()).getBytes()).substring(1,12));
        System.out.println(staff);
        dataService.addNewOfficeStaffItem(staff);

        return message("添加成功",successCode);
    }

    @ServiceTokenRequired
    @PostMapping("/officeManage/delete")
    public Map<String, Object> deleteData(@RequestBody String id){
        dataService.deleteOfficeStaffItem(id);
        return message("删除成功", successCode);
    }

    @ServiceTokenRequired
    @PostMapping("/officeManage/search")
    public List<Staff> searchStaff(@RequestBody String val){
        return dataService.searchMatchStaff(val);
    }



}
