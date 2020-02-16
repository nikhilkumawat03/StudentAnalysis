package entitymanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import entites.ComputerNetwork;
import entites.FacultyEntity;
import entites.StudentEntity;
import marksprediction.MarksPredict;

public class InsertDataManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConnection");
	EntityManager em = emf.createEntityManager();
	
	
	public void insertData(String filePath, String fileType) throws FileNotFoundException,IOException, ParseException, ClassNotFoundException {
		BufferedReader reader  = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		if(line!=null) {
			String[] columns = line.split(",");
			if(fileType.equals("GeneralData")) {
				if(columns.length == 6) {
					try {
						insertStudentData(filePath);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						insertFacultyData(filePath);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			}
			else {
				int flag = 0;
				for(String col : columns)
					if(col.equals("external_marks")) {
							insertStudentPerformanceData(filePath);
							flag = 1;
							break;
					}
				if(flag==0)
				{
					MarksPredict marksPredict = new MarksPredict();
					Dataset<Row> predictedData = marksPredict.marksPrediction("ComputerNetworks", filePath);
					Properties properties = new Properties();
					properties.put("user", "root");
					properties.put("password", "ibmlenovo");
					Class.forName("com.mysql.jdbc.Driver");
					predictedData.write().mode(SaveMode.Overwrite).jdbc("jdbc:mysql://localhost:3306/college", "college.currentComputerNetworks", properties);
					
				}
			}
		}
		
	}
	
	public void insertStudentPerformanceData(String filePath) throws IOException {
		
		System.out.println("Transaction begin");
		BufferedReader reader  = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		if(line!=null) {
			String[] columns = line.split(",");
		}
		ComputerNetwork computerNetwork = new ComputerNetwork();
		line = reader.readLine();
		int count = 1;
		while(line!=null) {
			System.out.println("Line no. = " + count + "   " +line);
			String[] data = line.split(",");
			computerNetwork.setStudent_id(data[0]);
			computerNetwork.setBranch_id(data[1]);
			computerNetwork.setVisited_resources(Integer.parseInt(data[2]));
			computerNetwork.setDoubt_asked(Integer.valueOf(data[3]));
			computerNetwork.setAttendence(data[4]);
			computerNetwork.setDiscipline_score(Integer.valueOf(data[5]));
			computerNetwork.setInternal_marks((data[6]));
			computerNetwork.setExternal_marks((data[7]));
			computerNetwork.setInterest_level(Integer.valueOf(data[8]));
			
			System.out.println(computerNetwork.getStudent_id());
			System.out.println(computerNetwork.getBranch_id());
			System.out.println(computerNetwork.getDiscipline_score());
			System.out.println(computerNetwork.getDoubt_asked());
			
			em.getTransaction().begin();
			em.persist(computerNetwork); 
		    em.getTransaction().commit(); 
			

			line = reader.readLine();
			count += 1;
		    //Before inserting studentPerformance data insert student data into student login table and register table
		}
	}
	
	public void insertStudentData(String filePath) throws ParseException, IOException {
		em.getTransaction().begin();
		
		System.out.println("Transaction begin");
		
		Query query = em.createNativeQuery("INSERT INTO Student VALUES(?,?,?,?,?,?)");
		
		BufferedReader reader  = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		if(line!=null) {
			String[] columns = line.split(",");
		}
		StudentEntity studentEntity = new StudentEntity();
		line = reader.readLine();
		int count = 1;
		while(line!=null) {
			System.out.println("Line no. = " + count + "   " +line);
			String[] data = line.split(",");
			studentEntity.setStudent_id(data[0]);
			studentEntity.setRoll_no(data[1]);
			studentEntity.setGender(data[2]);
			studentEntity.setNationality(data[3]);
			studentEntity.setPlaceOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(data[4]));
			studentEntity.setDayScholar(data[5]);
		
			query.setParameter(1, studentEntity.getStudent_id()); 
			query.setParameter(2, studentEntity.getRoll_no());
			query.setParameter(3, studentEntity.getGender());
			query.setParameter(4, studentEntity.getNationality());
			query.setParameter(5, studentEntity.getPlaceOfBirth());
			query.setParameter(6, studentEntity.getDayScholar());
			
			line = reader.readLine();
			count += 1;
		//System.out.println("Query Created");
		
		query.executeUpdate();
		//em.persist(studentEntity);
		}
		em.getTransaction().commit();
		System.out.println("Student Data Successfully Inserted");
	}
	
	public void insertFacultyData(String filePath) throws ParseException, IOException {
		em.getTransaction().begin();
		
		System.out.println("Transaction begin");
		
		Query query = em.createNativeQuery("INSERT INTO Faculty VALUES(?,?,?,?)");
		
		BufferedReader reader  = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		if(line!=null) {
			String[] columns = line.split(",");
		}
		FacultyEntity facultyEntity = new FacultyEntity();
		line = reader.readLine();
		int count = 1;
		while(line!=null) {
			System.out.println("Line no. = " + count + "   " +line);
			String[] data = line.split(",");
			
			facultyEntity.setFaculty_id(data[0]);
			facultyEntity.setContact_no(data[1]);
			facultyEntity.setSubject(data[2]);
			facultyEntity.setDegree(data[3]);
			
			query.setParameter(1, facultyEntity.getFaculty_id());
			query.setParameter(2, facultyEntity.getContact_no());
			query.setParameter(3, facultyEntity.getSubject());
			query.setParameter(4, facultyEntity.getDegree());
			
			line = reader.readLine();
			count += 1;
		System.out.println("Query Created");
		//em.persist(facultyEntity);
		query.executeUpdate();
		}
		em.getTransaction().commit();
		System.out.println("Faculty Data Successfully Inserted");
	}
}
	
