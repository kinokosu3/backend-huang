package com.kinoko.backend.mapper;


import com.kinoko.backend.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author su
 */
@Mapper
public interface DataMapper {

    @Select("select count(*) from patient_information")
    int countPatient();


    @Select("select * from patient_information")
    List<Patient> getPatientAllData();

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newPatientItem(Patient patient);

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deletePatientItem(String id, String tableName);

    @UpdateProvider(type = SqlProvider.class,method = "updateItem")
    void editPatientItem(Patient patient);
    @Select("select * from patient_information where name like concat('%',#{val},'%')")
    List<Patient> searchMatchPatient(String val);


    @Select("select * from drug_table")
    List<Drug> getDrugAllData();

    @Select("select price from drug_table where id=#{id}")
    float getDrugPrice(String id);

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newDrugItem(Drug drug);

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deleteDrugItem(String id, String tableName);

    @UpdateProvider(type = SqlProvider.class,method = "updateItem")
    void editDrugItem(Drug drug);

    @Select("select * from drug_table where name like concat('%',#{val},'%')")
    List<Drug> searchMatchDrug(String val);




    @Select("select * from staff")
    List<Staff> getStaffAllData();

    @Select("select * from office")
    List<Office> getOfficeAllData();

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newOfficeStaff(Staff staff);

    @UpdateProvider(type = SqlProvider.class, method = "updateItem")
    void editOfficeStaff(Staff staff);

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deleteOfficeStaff(String id, String tableName);

    @Select("select * from staff where name like concat('%',#{val},'%')")
    List<Staff> searchMatchStaff(String val);


//    @Insert("insert into bill_list values(#{id}, #{name_id}, #{doctor_id}, #{count_price},#{check_text},#{time})")
    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newBillItem(Bill bill);

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newDrugBillItem(DrugBill drugBill);
}
