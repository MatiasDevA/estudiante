
package com.example.demo.service;

import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.departmentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentIMP implements IDepartmentService  {

    @Autowired
    private departmentRepository depaRepo;
    
    
    @Override
    public Department saveDepartment(Department department) {
       return depaRepo.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return depaRepo.findAll();
    }

    @Override
    public  Department fetchDepartmentById(Long deparmentId) throws DepartmentNotFoundException {
        Optional<Department> department = depaRepo.findById(deparmentId);
        
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
            
        }
        
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
       depaRepo.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentID, Department department) {
       Department depDB = depaRepo.findById(departmentID).get();
       
       if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
           depDB.setDepartmentName(department.getDepartmentName());
       }
       if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
           depDB.setDepartmentCode(department.getDepartmentCode());
       }
       if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
           depDB.setDepartmentAddress(department.getDepartmentAddress());
       }
       
       return depaRepo.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
       return depaRepo.findByDepartmentNameIgnoreCase(departmentName);
    }
    
}
