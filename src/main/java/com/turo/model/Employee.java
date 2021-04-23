package com.turo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Data;

@Entity
@Data
@NamedQuery(
    name="Employee.findAllNamed",
    query="select named from Employee named"
)
@NamedEntityGraphs({
  @NamedEntityGraph(
      name = "departmentJoin",
      attributeNodes = {
        @NamedAttributeNode("department"),
      }),
  @NamedEntityGraph(
      name = "departmentAndOrgJoins",
      attributeNodes = {
        @NamedAttributeNode(value = "department", subgraph = "department-subgraph"),
      },
      subgraphs = {
        @NamedSubgraph(
            name = "department-subgraph",
            attributeNodes = {@NamedAttributeNode("organization")})
      }),
  @NamedEntityGraph(
      name = "noJoins",
      attributeNodes = {})
})
public class Employee implements Serializable {

  private static final long serialVersionUID = 65981149772133526L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String name;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @ManyToOne(
      cascade = {CascadeType.ALL},
      fetch = FetchType.EAGER)
  @JoinColumn(nullable = false)
  @Fetch(FetchMode.JOIN)//this and @LazyCollection - basic hibernate annotation 
  private Department department;
}
