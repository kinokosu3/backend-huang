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
public class BillView {
    private String id;
    private String patientName;
    private String doctorName;
    private Integer drugCount;
    private float countPrice;
    private String checkText;
    private String time;
}
