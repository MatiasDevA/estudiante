
package com.example.demo.service;

import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.model.Department;
import java.util.List;


public interface IDepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long deparmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentID, Department department);

    public Department fetchDepartmentByName(String departmentName);
    
 
    
}
