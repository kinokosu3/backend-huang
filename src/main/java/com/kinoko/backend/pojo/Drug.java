package com.kinoko.backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Drug {
    private String id;
    private String name;
    private String drug_code;
    private String measure;
    private String company;
    private float price;
}
