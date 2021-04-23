package com.turo.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.turo.repo.HibernateMysqlRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class BasicNativeTest1 {

  @Autowired private HibernateMysqlRepository userRepository;
  
  
  @Test
  @Transactional
  public void getUserTest() {
    
    System.out.println("------****Hibernate Native can't do EntityGraph *****-------"); 
    userRepository.getEmployeesNative();
    
  }
  
}
