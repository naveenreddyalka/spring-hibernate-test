package com.turo.test;

import org.hibernate.FetchMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.turo.repo.HibernateMysqlRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CriteriaTest2 {

  @Autowired private HibernateMysqlRepository userRepository;
  
  
  @Test
  @Transactional
  public void getUserTest() {
    
    System.out.println("------****EmployeesCretira with FetchMode Default *****-------"); 
    userRepository.getEmployeesCretirawithFetchModeDefault();
    
    System.out.println("------****EmployeesCretira with FetchMode SELECT "
        + "can't set entity graph on cretira, we need to get the cretira query to set*****-------"); 
    userRepository.getEmployeesCretirawithFetchMode(FetchMode.JOIN);
    
    //FetchMode : org.hibernate.FetchMode DEFAULT, SELECT JOIN
    //FetchMode : org.hibernate.annotations.FetchMode SELECT, JOIN and SUBSELECT (not for *toOne, i.e only collections)
    
    
  }
  
}
