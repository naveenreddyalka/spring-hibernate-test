package com.turo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.turo.model.Employee;

@Repository
public interface JpaMysqlRepository extends JpaRepository<Employee, Long>{
  
  @Override
  List<Employee> findAll();

  @Query("from Employee emp_findAllNoGraph")
  List<Employee> findAllQuery();
  
  @Query("from Employee emp_findAllQueryAndGraph")
  @EntityGraph(attributePaths = {"department","department.organization"})
  List<Employee> findAllQueryAndGraphAdhoc();
  
  @Query("select findAllQueryAndNamedGraph from Employee findAllQueryAndNamedGraph")
  @EntityGraph(value = "departmentAndOrgJoins")
  List<Employee> findAllQueryAndNamedGraph();
  
  @Query(name="Employee.findAllNamed")
  @EntityGraph(value = "departmentAndOrgJoins")
  List<Employee> namedWithGraph();
  
  @Query(name="Employee.findAllNamed")
  List<Employee> namedWithOutGraph();
  
  @Query(name="Employee.findAllNamed")
  @EntityGraph(value = "noJoins")
  List<Employee> namedWithOurGraphNoJoin();
  
}
