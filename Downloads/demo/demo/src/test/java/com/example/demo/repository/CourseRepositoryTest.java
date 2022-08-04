/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import java.awt.print.Pageable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class CourseRepositoryTest {
    
   @Autowired
   private CourseRepository courseRepository;
   
   @Test
   public void printCourses(){
       List<Course> courses = 
               courseRepository.findAll();
           System.out.println("courses =" + courses);
   }
   
   @Test
   public void SaveCouerseWithTeacher(){
       
       Teacher teacher = Teacher.builder()
               .firstName("sonya")
               .lastName("miranda")
               .build();
       
       
       Course course = Course.builder()
               .title("Python")
               .credit(6)
               .teacher(teacher)
               .build();
       
       courseRepository.save(course);
   }
   
   @Test
   public void saveCourseWithStudentAndTeacher(){
       Teacher teacher = Teacher.builder()
               .firstName("lizzy")
               .lastName("Morgan")
               .build();
       
       Student student = Student.builder()
               .firstName("ernesto")
               .lastName("fernandez")
               .emailId("aloja@hotmail.com")
               .build();
               
       
       Course course = Course.builder()
               .title("AI")
               .credit(12)
               .teacher(teacher)
               .build();
       
       
       course.addStudent(student);
       
       courseRepository.save(course);
   }
}