package entitymanager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import entites.FacultyLoginEntity;
import entites.StudentLoginEntity;

public class UserLoginManager {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConnection"); 
    EntityManager em = emf.createEntityManager();
    
  //Function to verify student credentials to process for login
  	public boolean studentLogin(StudentLoginEntity loginDetails) {
  		
  		em.getTransaction().begin();
  		
  		System.out.println("Entered to UserManager.studentLogin");
  		
  		Query query = em.createNamedQuery("getUserIdPassword");
  		query.setParameter("student_id", loginDetails.getStudent_id());
  		
  		System.out.println("Query is generated.");
  		List<Object[]> results = query.getResultList();
  		
  		List<StudentLoginEntity> userData = new ArrayList<>();
  		
  		//Getting data from Object to StudentRegistrationEntity type
  		for(Object[] row : results) {
  			StudentLoginEntity studentData = new StudentLoginEntity();
  			studentData.setStudent_id((String)row[0]);
  			studentData.setPassword((String) row[1]);
  			
  			userData.add(studentData);
  		}
  		
  		//Fetching student id password to check user credentials
  		String id = userData.get(0).getStudent_id();
  		String password = userData.get(0).getPassword();

  		System.out.println("Query Executed");
  		if(password.equals(loginDetails.getPassword())) {
  			System.out.println("Password Matched");
  			return true;
  		}
  		else {
  			System.out.println("Password Not Matched");
  			return false;
  		}
  	}
  	
  	
  	public boolean facultyLogin(FacultyLoginEntity loginDetails) {
  		em.getTransaction().begin();
  		//Query query = em.createNamedQuery("getFacultyIdPassword");
  		TypedQuery<FacultyLoginEntity> query = em.createNamedQuery("getFacultyIdPassword", FacultyLoginEntity.class);
  		query.setParameter("faculty_id", loginDetails.getFaculty_id());
  		
  		//List<Object[]> results = query.getResultList();
  		List<FacultyLoginEntity> results = query.getResultList();
  		System.out.println("After getting result from Query");

  		for(FacultyLoginEntity f:results) {
  			System.out.println(f.getFaculty_id()+"\n" + f.getPassword() + "\n");
  		}
  		
  		if (results.get(0).getPassword().equals(loginDetails.getPassword())) {
  			System.out.println("Password Matched");
  			return true;
  		}
  		else {
  			System.out.println("Password Not Matched");
  			return false;
  		}
  	}
}
