package com.kinoko.backend.mapper;


import com.kinoko.backend.pojo.Drug;
import com.kinoko.backend.pojo.Patient;
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


    @Select("select * from drugTable")
    List<Drug> getDrugAllData();

    @InsertProvider(type = SqlProvider.class, method = "newItem")
    void newDrugItem(Drug drug);

    @DeleteProvider(type = SqlProvider.class, method = "deleteItem")
    void deleteDrugItem(String id, String tableName);

    @UpdateProvider(type = SqlProvider.class,method = "updateItem")
    void editDrugItem(Drug drug);
}
