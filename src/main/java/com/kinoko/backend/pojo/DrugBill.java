package com.kinoko.backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DrugBill {
    private String id;
    private String drug_id;
    private String bill_id;
    private Integer quantity;
    private float price_count;
}
