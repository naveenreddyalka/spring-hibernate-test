package com.turo.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.Date;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.turo.model.Department;
import com.turo.model.Employee;
import com.turo.model.Organization;
import com.turo.repo.HibernateMysqlRepository;
import com.turo.repo.JpaMysqlRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ApplicationTests {

  @Autowired private HibernateMysqlRepository userRepository;
  @Autowired private JpaMysqlRepository jpaRepository;
  
  final Organization org = new Organization();
 
  
  
  @Before
  public void init() {
    org.setName("Test");
  }
  
  //@Test
  public void saveHrUserTest() {
    
    final Department hr = new Department();
    hr.setName("hr");
    hr.setOrganization(org);
    
    final Employee user1 = new Employee();
    user1.setCreatedDate(new Date());
    user1.setName("user1");
    user1.setEmail("user1@test.com");
    user1.setDepartment(hr);
    
    userRepository.save(user1);
  }
  
  //@Test
  public void saveDevEmpTest() {
    
    final Department hr = new Department();
    hr.setName("hr");
    hr.setOrganization(org);
    
    final Employee user1 = new Employee();
    user1.setCreatedDate(new Date());
    user1.setName("user1");
    user1.setEmail("user1@test.com");
    user1.setDepartment(hr);
    
    userRepository.save(user1);
  }

  
  @Test
  @Transactional
  public void saveTestEmpTest() {
    final Organization org = new Organization();
    org.setName("Test");
    
    final Department hr = new Department();
    hr.setName("hr");
    hr.setOrganization(org);
    
    final Employee user1 = new Employee();
    user1.setCreatedDate(new Date());
    user1.setName("user1");
    user1.setEmail("user1@test.com");
    user1.setDepartment(hr);
    
    userRepository.save(user1);
  }
  
  @Test
  @Transactional(readOnly = true)
  public void saveTestEmpTestRead() {
    final Organization org = new Organization();
    org.setName("Test");
    
    final Department hr = new Department();
    hr.setName("hr");
    hr.setOrganization(org);
    
    final Employee user1 = new Employee();
    user1.setCreatedDate(new Date());
    user1.setName("user1");
    user1.setEmail("user1@test.com");
    user1.setDepartment(hr);
    
    assertDoesNotThrow(() -> userRepository.save(user1));
  }
  
  //@Test
  public void getUserTest() {
    
    
    System.out.println("------*****JPA findAll with EntityGraph AllJoins Adhoc ****-------"); 
    //jpaRepository.findAll();
   
    System.out.println("------*****JPA findAll2 with NamedEntityGraphs all joins ****-------"); 
    //jpaRepository.findAll2();
   
    System.out.println("------*****JPA findAll3 with NamedEntityGraphs no joins = 1 query no N+1 ****-------"); 
    //jpaRepository.findAll3();
   
    System.out.println("------*****JPA findAll4 no NamedEntityGraphs only 1 query no dependecies ****-------"); 
   // jpaRepository.findAll4();
    
    System.out.println("------*****JPA findAllNamed****-------"); 
    //jpaRepository.findAllNamed();
    
    System.out.println("------****Hibernate getEmployees HQL and EntityGraph *****-------"); 
    //userRepository.getEmployeesWithHQLAndEntityGraph();
   
    System.out.println("------****Hibernate getEmployeesWithHQLAndEntityGraph with fetchMode SELECT = Native *****-------"); 
   // userRepository.getEmployeesWithHQLAndEntityGraph();
    
    System.out.println("------****Hibernate Native can't do EntityGraph *****-------"); 
    //userRepository.getEmployeesNative();
  
    System.out.println("------****Hibernate Named Query and EntityGraph *****-------"); 
   //userRepository.getEmployeesWithNamedAndEntityGraph();
    
    
    //userRepository.getEmployeesNativeLoop();
    
    System.out.println("------*****JPA findAll3 with namedWithGraph ****-------"); 
   // jpaRepository.namedWithGraph();
    
    System.out.println("------*****JPA findAll3 with namedWithOurGraphNoJoin  ****-------"); 
   // jpaRepository.namedWithOurGraphNoJoin();
    
    System.out.println("------*****JPA findAll3 with namedWithOurGraph ****-------"); 
    // jpaRepository.namedWithOutGraph();


    
    //userRepository.getEmployeesWithHQLAndEntityGraph();
    
    
    
  
    jpaRepository.namedWithOurGraphNoJoin();
    System.out.println("------*****JPA WithOurGraphNoJoin with namedWithOurGraph ****-------"); 
     
   // jpaRepository.findAll2();
    
  }
  
}
