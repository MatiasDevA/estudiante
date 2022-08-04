
package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TeacherRepositoryTest {
    
    @Autowired
    private TeacherRepository teachRepo;
    
    @Test
    public void saveTeacher(){
        
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(4)
                .build();
        
          Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();
        
        Teacher teacher = Teacher.builder()
                .firstName("pablo")
                .lastName("mendoza")
              //  .courses(List.of(courseDBA, courseJava))
                .build();
        
        
        teachRepo.save(teacher);
    }
    
}
