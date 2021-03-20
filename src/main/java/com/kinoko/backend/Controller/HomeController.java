package com.kinoko.backend.Controller;


import com.kinoko.backend.aop.ServiceTokenRequired;
import com.kinoko.backend.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    DataMapper dataMapper;
    @GetMapping("/notice")
    public String notice(){
        return "It is spring that brings love and hope.";
    }
    @ServiceTokenRequired
    @GetMapping("/home/count")
    public Map<String, Integer> siteStats(){
        Map<String, Integer> res = new HashMap<>();
        res.put("count_price", dataMapper.countBillPrice());
        res.put("count_itemNum", dataMapper.countBillItem());
        return res;
    }
}
