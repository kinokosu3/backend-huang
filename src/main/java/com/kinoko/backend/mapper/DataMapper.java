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
    Integer countPatient();
    @Select("select count(*) from bill_list")
    Integer countBillItem();
    @Select("select sum(count_price) from bill_list")
    Integer countBillPrice();


    @Select("select * from patient_information")
    List<Patient> getPatientAllData();

    @Select("select * from patient_information where id=#{id}")
    Patient getOnePatient(String id);

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newPatientItem(Patient patient);

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deletePatientItem(String id, String tableName);

    @UpdateProvider(type = SqlProvider.class,method = "updateItem")
    void editPatientItem(Patient patient);
    @Select("select * from patient_information where name like concat('%',#{val},'%')")
    List<Patient> searchMatchPatient(String val);

    // LIST:{}

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

    @Select("select * from office where name=#{name}")
    Office getOfficeOneData(String name);

    @Select("select * from office")
    List<Office> getOfficeAllData();
    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deleteOffice(String id, String tableName);

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newOffice(Office office);

    @Select("select * from staff where id=#{id}")
    Staff getOneStaff(String id);

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

    @Select("select t.name, t.measure, b.quantity, b.price_count from drug_bill as b join drug_table as t on b.drug_id = t.id where b.bill_id=#{id}")
    List<DrugBillListPreview> getDrugBillList(String id);

    @Select("select count(*) from drug_bill where bill_id=#{id}")
    Integer getDrugBillCount(String id);


    @Select("select bl.name_id as patientId, bl.doctor_id as doctorId,bl.id as id,pi.name as patientName, s.name as doctorName, count_price, check_text, time " +
            "from bill_list as bl " +
            "left join patient_information as pi on bl.name_id=pi.id " +
            "left join staff as s on bl.doctor_id=s.id")
    @Results(id="billView", value = {
            @Result(property = "countPrice", column = "count_price"),
            @Result(property = "checkText", column = "check_text"),

    })
    List<BillView> getBillViewList();

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deleteBillItem(String id, String tableName);
}
