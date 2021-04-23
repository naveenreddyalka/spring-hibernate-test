package com.turo.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Data;

@Entity
@Data
public class Department implements Serializable {

  private static final long serialVersionUID = -515553523805903769L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  
  //@OneToMany(mappedBy="department")
  //private Set<Employee> employees;
  
  @ManyToOne(cascade={CascadeType.ALL})
  @JoinColumn(nullable=false)
  @Fetch(FetchMode.JOIN)
  private Organization organization;
  
}
