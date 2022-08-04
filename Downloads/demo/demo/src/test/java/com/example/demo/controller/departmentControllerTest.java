
package com.example.demo.controller;

import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.service.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(departmentController.class)
public class departmentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private IDepartmentService depaServ;
    
    private Department department;
    
    @BeforeEach
    public void setUp() {
        
        department = Department.builder()
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void testSaveDepartment() throws Exception {
        Department inputdepartment = Department.builder()
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();
        
        Mockito.when(depaServ.saveDepartment(inputdepartment))
                .thenReturn(department);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/departments").contentType(MediaType.
                APPLICATION_JSON)
        .content("{\n" +
"    \"departmentName\" :\"IT\",\n" +
"    \"departmentAddress\" :\"Ahmedabad\",\n" +
"    \"departmentCode\" :\"IT-06\"\n" +
"}")).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    
    @Test
    void fetchDepartmentById() throws Exception
    {
       Mockito.when(depaServ.fetchDepartmentById(1L)).thenReturn(department);
       
       mockMvc.perform(get("/departments/1")
       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    
}
}
