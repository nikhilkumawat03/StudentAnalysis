package entitymanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entites.FacultyLoginEntity;
import entites.FacultyRegistrationEntity;
import entites.StudentLoginEntity;
import entites.StudentRegistrationEntity;


public class UserRegistrationManager {

	
	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConnection"); 
     EntityManager em = emf.createEntityManager();
	
     public void addFacultyDetails(FacultyRegistrationEntity facultyRegistration) {
		try {
		
	       
	        
	        em.getTransaction().begin();
			
	        em.persist(facultyRegistration); 
	        
	        em.getTransaction().commit();  

	        //emf.close();  
	        //em.close(); 
	        
			System.out.println("Details Added");
		}catch(Exception  e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
            System.out.println("error");
		}
	}
	
	public void addStudentDetails(StudentRegistrationEntity studentRegistration) {
		try {
			
	        em.getTransaction().begin();
	        
	        em.persist(studentRegistration);
	        
	        em.getTransaction().commit();  

	        //emf.close();  
	        //em.close(); 
	        
			System.out.println("Details Added");
		}catch(Exception  e) {
			System.out.println(e.getMessage());
            System.out.println("error");
		}
	}
}
