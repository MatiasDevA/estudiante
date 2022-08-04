/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.CourseMaterial;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CourseMaterialRepositoryTest {
   
    @Autowired
    private CourseMaterialRepository courmat;
    
    
    
    
    @Test
    public void SaveCourseMaterial(){
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        
        
        
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.myporfolio.com")
                .course(course)
                .build();
                
                courmat.save(courseMaterial);
    }
    
    @Test
    public void printAllCoursesMaterials(){
        List<CourseMaterial> courseMaterial =
                courmat.findAll();
        
        System.out.println("courseMaterial =" + courseMaterial);
    }
}
