package com.turo.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Organization implements Serializable {
  
  private static final long serialVersionUID = -6520788858732541735L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  
  //Defaults to LAZY
  @OneToMany(mappedBy="organization",cascade={CascadeType.ALL})
 // @Fetch(FetchMode.JOIN)
  //@OneToMany(mappedBy="organization",cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<Department> departments;
  
}


