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

    }};
    public String newItem(Object obj) {
        String baseSql = "insert into";
        Class<?> clazz = obj.getClass();
        String className = tables.get(clazz.getSimpleName());
        StringBuilder res = new StringBuilder(baseSql + " " + className + " " + "values(");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String[] arrays = field.toString().split("\\.");
            String filedName = arrays[arrays.length-1];
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
        String baseSql = "delete from";
        return baseSql + " " + tables.get(tableName) + " " + "where id=#{id}";
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
