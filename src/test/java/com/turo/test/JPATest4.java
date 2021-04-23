package com.turo.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.turo.repo.JpaMysqlRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class JPATest4 {

  @Autowired private JpaMysqlRepository jpaRepository;
  
  @Test
  @Transactional
  public void getUserTest() {
    
    
    System.out.println("------*****JPA findAll deafult ****-------"); 
    jpaRepository.findAll();
   
    System.out.println("------*****JPA findAllQuery with Query ****-------"); 
    jpaRepository.findAllQuery();
    
    System.out.println("------*****JPA findAllQueryAndGraphAdhoc with Query and Adhoc Graph****-------"); 
    jpaRepository.findAllQueryAndGraphAdhoc();
   
    System.out.println("------*****JPA findAllQueryAndNamedGraph with Query and Named Graph with all Joins****-------"); 
    jpaRepository.findAllQueryAndNamedGraph();
    
    System.out.println("------*****JPA namedWithOurGraphNoJoin with Query and Named Graph with no Joins****-------"); 
    jpaRepository.namedWithOurGraphNoJoin();
    
  }
  
}
