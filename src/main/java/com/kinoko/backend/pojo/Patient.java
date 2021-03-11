package com.kinoko.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Patient {
    private String id;
    private String name;
    private String sex;
    private int age;
    private String idCard;
    private String telNum;
    private String address;
    private String treatIf;
}
