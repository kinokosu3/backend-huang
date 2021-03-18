package com.kinoko.backend.Service;

import com.kinoko.backend.mapper.DataMapper;
import com.kinoko.backend.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
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
    public List<Patient> searchMatchPatient(String val){
        return dataMapper.searchMatchPatient(val);
    }

    public List<Drug> getAllDrugData(){
        return dataMapper.getDrugAllData();
    }
    public void addNewDrugItem(Drug drug){
        dataMapper.newDrugItem(drug);
    }
    public float getDrugPrice(String id){return dataMapper.getDrugPrice(id);}
    public void deleteDrugItem(String id){
        dataMapper.deleteDrugItem(id, "Drug");
    }
    public void editDrugItem(Drug drug){
        dataMapper.editDrugItem(drug);
    }
    public List<Drug> searchMatchDrug(String val){
        return dataMapper.searchMatchDrug(val);
    }

    public List<Staff> getAllStaffData(){
        return dataMapper.getStaffAllData();
    }
    public List<Office> getAllOfficeData(){
        return dataMapper.getOfficeAllData();
    }
    public void addNewOfficeStaffItem(Staff staff){
        dataMapper.newOfficeStaff(staff);
    }
    public void deleteOfficeStaffItem(String id){
        dataMapper.deleteOfficeStaff(id, "Staff");
    }
    public void editOfficeStaffItem(Staff staff){
        dataMapper.editOfficeStaff(staff);
    }

    public List<Staff> searchMatchStaff(String val){
        return dataMapper.searchMatchStaff(val);
    }

    public void addNewDrugBillItem(DrugBill drugBill){dataMapper.newDrugBillItem(drugBill);}

    public void addNewBillItem(Bill bill){
        dataMapper.newBillItem(bill);
    }
}
