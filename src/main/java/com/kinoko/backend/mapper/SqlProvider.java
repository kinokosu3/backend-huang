package com.kinoko.backend.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SqlProvider {
    Map<String, String> tables = new HashMap<String, String>(){{
        put("Patient", "patient_information");
        put("Drug","drug_table");
        put("Staff", "staff");
        put("Bill", "bill_list");
        put("DrugBill", "drug_bill");
        put("Office", "office");
    }};
    public String newItem(Object obj) {
        String baseSql = "insert into";
        Class<?> clazz = obj.getClass();
        // getSimpleName() 获得类名
        // Patient

        // "Patient" <- clazz.getSimpleName()
        //  表明 patient_information
        String className = tables.get(clazz.getSimpleName());
        // insert into patient_information values(id=#{id}, asdiajsd=#{asdiajsd},)
        StringBuilder res = new StringBuilder(baseSql + " " + className + " " + "values(");
        // [] STRING
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String[] arrays = field.toString().split("\\.");
            String filedName = arrays[arrays.length-1];
            // drug_list
            if("drug_list".equals(filedName)){
                continue;
            }
            res.append("#{").append(filedName).append("},");
        }
        res.delete(res.length()-1, res.length());
        res.append(")");
        return res.toString();
    }
    public String deleteItem(String id, String tableName){
        // id=1, tableName=Patient
        String baseSql = "delete from";
        // "delete from patient_information where id=#{id}
        return baseSql + " " + tables.get(tableName) + " " + ("Office".equals(tableName)?"where name=#{id}":"where id=#{id}");
    }
    public String updateItem(Object obj){
        String baseSql = "update";
        Class<?> clazz = obj.getClass();
        StringBuilder res = new StringBuilder(baseSql + " " + tables.get(clazz.getSimpleName()) + " " + "set ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String[] arrays = field.toString().split("\\.");
            if("id".equals(arrays[arrays.length - 1])) {
                continue;
            }
            res.append(arrays[arrays.length-1]).append("=").append("#{").append(arrays[arrays.length-1]).append("},");
        }
        res.delete(res.length()-1, res.length());
        res.append(" where id=#{id}");
        return res.toString();
    }
}
