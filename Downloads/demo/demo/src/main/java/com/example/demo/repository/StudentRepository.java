
package com.example.demo.repository;

import com.example.demo.model.Student;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
    
    
    public List<Student> findByFirstName(String firstname);
    
    public List<Student> findByFirstNameContaining(String name);
    
    public List<Student> findByLastNameNotNull();
    
    public List<Student> findByGuardianName(String guardianName);
    
    Student findByFirstNameAndLastName(String firstName, String lastName);
    
    
    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);
    
    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentByFirstNameEmailAddress(String emailId);
    
    //native
    @Query(
    value= "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);
    
      @Query(
    value= "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true)
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId")String emailId);
    
    @Transactional
    @Modifying
    @Query(
    value="update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery=true)
    int updateStudentNamedByEmailId(String firstName, String emailId);
}
