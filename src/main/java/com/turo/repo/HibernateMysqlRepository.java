package com.turo.repo;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.turo.model.Employee;

@Repository
public class HibernateMysqlRepository {

  @Autowired private SessionFactory sessionFactory;

  
  public List<Employee> getEmployeesWithNamedAndEntityGraph(final String graph) {
    Session session = null;
    Transaction transaction = null;
    // Stream<Employee> employees = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      employees =
          session
              .createNamedQuery("Employee.findAllNamed", Employee.class)
              .setHint(
                  "javax.persistence.fetchgraph", session.getEntityGraph(graph))
              .list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesWithHQLAndEntityGraph(final String graph) {
    Session session = null;
    Transaction transaction = null;
    // Stream<Employee> employees = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      // employees = session.createQuery("select e from Employee e", Employee.class).stream();
      employees =
          session
              .createQuery("select hqlWithGraph from Employee hqlWithGraph ", Employee.class)
              .setHint(
                  "javax.persistence.fetchgraph", session.getEntityGraph(graph))
              .list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesWithHQL() {
    Session session = null;
    Transaction transaction = null;
    // Stream<Employee> employees = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      // employees = session.createQuery("select e from Employee e", Employee.class).stream();
      employees =
          session
              .createQuery("select hql from Employee hql ", Employee.class)
              .list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesCretirawithFetchMode(final FetchMode mode) {
    Session session = null;
    Transaction transaction = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      final Criteria cr = session.createCriteria(Employee.class);
      cr.setFetchMode("department", mode);
      employees = cr.list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesCretirawithFetchModeDefault() {
    Session session = null;
    Transaction transaction = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      final Criteria cr = session.createCriteria(Employee.class);
      employees = cr.list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesCretiraWithEntityGraph() {
    Session session = null;
    Transaction transaction = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      final Criteria cr = session.createCriteria(Employee.class);
      //query.setHint("javax.persistence.loadgraph", entityGraph);
      employees = cr.list();

      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }

  public List<Employee> getEmployeesNative() {
    Session session = null;
    Transaction transaction = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      employees =
          employees =
              session
                  .createNativeQuery("select * from Employee", Employee.class)
                  .list();
      
      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }
  
  public List<Employee> getEmployeesNativeLoop() {
    Session session = null;
    Transaction transaction = null;
    List<Employee> employees = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      employees =
          employees =
              session
                  .createNativeQuery("select * from Employee", Employee.class)
                  .list();
      
      
      
      //employees.forEach(emp -> {emp.getDepartment().getOrganization();});
      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }

  public void save(final Employee employee) {
    Session session = null;
    //final Transaction transaction = null;
    try {
      session = sessionFactory.openSession();
      //transaction = session.beginTransaction();
     try {
      final String url =  (session.unwrap(SessionImpl.class)).connection().getMetaData().getURL();
      System.out.println(url ); 
    } catch (final HibernateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

      session.save(employee);

      //transaction.commit();
    
    } finally {
      session.close();
    }
  }
}
