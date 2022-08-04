
package com.example.demo.service;

import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.departmentRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest
public class IDepartmentServiceTest {
    
   @Autowired
   private IDepartmentService departmentService;
   
   @MockBean
   private departmentRepository depaRepo;
    
    @BeforeEach
    public void setUp() {
        
        Department department;
       department = Department.builder().departmentName("IT")
               .departmentAddress("Buenos Aires")
               .departmentCode("1804")
               .departmentId(1L)
               .build();
        
        Mockito.when(depaRepo.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }
    
    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        Department found = 
                departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
   
    
}
