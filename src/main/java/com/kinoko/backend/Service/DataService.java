package com.kinoko.backend.Service;

import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.pojo.Drug;
import com.kinoko.backend.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;
    public int countPatient(){
        return dataMapper.countPatient();
    }


    public List<Patient> getAllPatientData(){
        return dataMapper.getPatientAllData();
    }
    public void addNewPatientItem(Patient patient){
        dataMapper.newPatientItem(patient);
    }
    public void deletePatientItem(String id){
        dataMapper.deletePatientItem(id, "Patient");
    }
    public void editPatientItem(Patient patient){
        dataMapper.editPatientItem(patient);
    }


    public List<Drug> getAllDrugData(){
        return dataMapper.getDrugAllData();
    }
    public void addNewDrugItem(Drug drug){
        dataMapper.newDrugItem(drug);
    }
    public void deleteDrugItem(String id){
        dataMapper.deleteDrugItem(id, "Drug");
    }
    public void editDrugItem(Drug drug){
        dataMapper.editDrugItem(drug);
    }
}
