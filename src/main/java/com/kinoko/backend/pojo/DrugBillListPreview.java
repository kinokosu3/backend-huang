package com.kinoko.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DrugBillListPreview {
    private String name;
    private String measure;
    private String quantity;
    private float price_count;
}
