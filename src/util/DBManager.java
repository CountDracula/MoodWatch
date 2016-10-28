package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import model.Page;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.fasterxml.classmate.AnnotationConfiguration;

 
/**
 * A sample program that demonstrates how to perform simple CRUD operations
 * with Hibernate framework.
 * @author www.codejava.net
 *
 */
public class DBManager {
   
private Session session;	
private Configuration configuration;
private static SessionFactory factory;
	
/// USED TO UPDATE DATABASE BY ADMINISTRATOR!

		
	    private static SessionFactory sessionFactory=buildSessionFactory();
	    
	    public static SessionFactory buildSessionFactory(){
	         try {
	             return new Configuration().configure("hibernate.cfg.xml").configure().buildSessionFactory();
	         } catch (Throwable ex) {
	             System.err.println("Initial SessionFactory creation failed." + ex);
	             throw new ExceptionInInitializerError(ex);
	         }
	     }
	   
	     public static SessionFactory getSessionFactory() {
	         return sessionFactory;
	     }

		
	 }


   
