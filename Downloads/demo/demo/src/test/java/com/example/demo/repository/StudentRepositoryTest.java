
package com.example.demo.repository;

import com.example.demo.model.Guardian;
import com.example.demo.model.Student;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository stuRepo;
    
    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("mati@hotmail.com")
                .firstName("mati")
                .lastName("apple")
               // .guardianName("waoo")
               // .guardianEmail("waoo.hotmail.com")
               // .guardianMobile("99999999")
                .build();
                
                stuRepo.save(student);
    }
    @Test
    public void saveStudentWithGuardian(){
        
        Guardian guardian = Guardian.builder()
                  .email("waoo.hotmail.com")
                  .name("waoo")
                  .mobile("999999995641")
                  .build();
        
        
        Student student = Student.builder()
                .firstName("pepe")
                .emailId("pepe@hotmail.com")
                .lastName("aloja")
                .guardian(guardian)
                .build();
        
        stuRepo.save(student);
    }
    
    
    
    @Test
    public void printAllStudent(){
        List<Student> studentList =
                stuRepo.findAll();
                
                System.out.println("studentList = " + studentList);
    }
    
    
    @Test
    public void printStudentByFirstName(){
        List<Student> students =
                stuRepo.findByFirstName("pepe");
        
        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                stuRepo.findByFirstNameContaining("ti");
        
        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentsBasedOnGuardianName(){
        List<Student> students =
                stuRepo.findByGuardianName("waoo");
          System.out.println("students = " + students);
    }
    
    @Test
    public void printgetStudentByEmailAddress(){
        Student student =
                stuRepo.getStudentByEmailAddress("pepe@hotmail.com");
                
                
                  System.out.println("students = " + student);
                
    }
    
    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String firstName=
       
                stuRepo.getStudentByFirstNameEmailAddress("pepe@hotmail.com");
        
         System.out.println("students = " + firstName);
    }
    
    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student=
       
                stuRepo.getStudentByEmailAddressNative("pepe@hotmail.com");
        
         System.out.println("students = " + student);
    }
    
    
    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student =
                stuRepo.getStudentByEmailAddressNativeNamedParam("pepe@hotmail.com");
        
         System.out.println("students = " + student);
    }
    
    @Test
    public void updateStudentNamedByEmailId(){
        stuRepo.updateStudentNamedByEmailId("Safiro", "pepe@hotmail.com");
    }
    
    
}
