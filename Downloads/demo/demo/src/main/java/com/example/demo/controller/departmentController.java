
package com.example.demo.controller;

import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.service.IDepartmentService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class departmentController {
    
    @Autowired
    private IDepartmentService deparimp;
    
    
    private final Logger LOGGER =
            LoggerFactory.getLogger(departmentController.class);
    
    
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return deparimp.saveDepartment(department);
    }
    
    @GetMapping("/departments")
    public List<Department> fetchDeparmentList(){
          LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        return deparimp.fetchDepartmentList();
    }
    
    @GetMapping("/departments/{id}")
    public Department fetchDataById(@PathVariable("id") Long deparmentId) throws DepartmentNotFoundException {
        return deparimp.fetchDepartmentById(deparmentId);
    }
    
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        deparimp.deleteDepartmentById(departmentId);
        return "deparment deleted succeddfully";
    }
    
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id")Long departmentID, @RequestBody Department department){
        
        return deparimp.updateDepartment(departmentID, department);
    }
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return deparimp.fetchDepartmentByName(departmentName);
    }
    
}
