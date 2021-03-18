package com.kinoko.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    private String id;
    private String name_id;
    private String doctor_id;
    private Map<String,Integer> drug_list;
    private float count_price;
    private String check_text;
    private String time;
}
