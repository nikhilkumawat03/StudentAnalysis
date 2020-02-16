package entitymanager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entites.FacultyEntity;
import entites.FacultyLoginEntity;
import entites.StudentEntity;
import entites.StudentLoginEntity;

public class GetDetailsManager {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConnection");
	EntityManager em = emf.createEntityManager();
	
	//Function to get student genral information
	public List<StudentEntity> getStudentGenralInfo(StudentLoginEntity loginDetails) {
				
		em.getTransaction().begin();
		
		TypedQuery<StudentEntity> query = em.createNamedQuery("getStudentGenralInfo", StudentEntity.class); 
		query.setParameter("student_id", loginDetails.getStudent_id());
		
		System.out.println("Student Manager Query Executed");
		
		List<StudentEntity> studentData = query.getResultList();
		
		System.out.println("StudentManager: Student Data stored in List");
		
		return studentData;		
	}
	public List<FacultyEntity> getfacultyGenralInfo(FacultyLoginEntity loginDetails) {
		
		em.getTransaction().begin();
		
		TypedQuery<FacultyEntity> query = em.createNamedQuery("getFacultyGenralInfo", FacultyEntity.class); 
		query.setParameter("faculty_id",loginDetails.getFaculty_id());
		
		System.out.println("Faculty Manager Query Executed");
		
		List<FacultyEntity> facultyData = query.getResultList();
		
		System.out.println("FacultyManager: Facutly Data stored in List");
		
		return facultyData;		
	}
}
