
package com.example.demo.repository;

import com.example.demo.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class departmentRepositoryTest {
    
    @Autowired
    private departmentRepository depaRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @BeforeEach
    public void setUp() {
        
        Department department =
                Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("ME -01")
                .departmentAddress("Delhi")
                .build();
        
        entityManager.persist(department);
    }
    
    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department =
                depaRepo.findById(1L).get();
        assertEquals(department.getDepartmentName(),"Mechanical Engineering");
    }

   
    
}
