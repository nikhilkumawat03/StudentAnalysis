package login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import entites.FacultyEntity;
import entites.FacultyLoginEntity;
import entites.StudentEntity;
import entites.StudentLoginEntity;
import entitymanager.GetDetailsManager;
import entitymanager.UserLoginManager;
import entitymanager.UserRegistrationManager;
import newML.MarksPredict;
/**
 * Servlet implementation class UserRegistration
 */
@WebServlet(description = "User Login (Faculty, Student and admin)", urlPatterns = { "/LoginPage/login" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		String password = request.getParameter("pass");
		
		System.out.println("UserId: " + userid);
		
		if(userid.substring(0, 1).toLowerCase().equals("s")) {
			
			System.out.println("Entered student login servlet");
			
			StudentLoginEntity loginDetails = new StudentLoginEntity();
			
			loginDetails.setStudent_id(userid);
			loginDetails.setPassword(password);
			
			UserLoginManager userLogin = new UserLoginManager();
			boolean isValidUser = userLogin.studentLogin(loginDetails);
			
			if(isValidUser) {
				GetDetailsManager studentGenralInfo = new GetDetailsManager();
				List<StudentEntity> studentData = studentGenralInfo.getStudentGenralInfo(loginDetails);
				
				StudentEntity passingStudentEntity = new StudentEntity();
				
				System.out.println("UserLoginServlet: Student data recieved");
				
				for(StudentEntity data : studentData) {
					passingStudentEntity.setBranch_id(data.getBranch_id());
					passingStudentEntity.setRoll_no(data.getRoll_no());
					passingStudentEntity.setDayScholar(data.getDayScholar());
					passingStudentEntity.setGender(data.getGender());
					passingStudentEntity.setPlaceOfBirth(data.getPlaceOfBirth());
					passingStudentEntity.setStudent_id(data.getStudent_id());
					passingStudentEntity.setNationality(data.getNationality());
					System.out.println(data.getBranch_id());
					System.out.println(data.getDayScholar());
					System.out.println(data.getGender());
					System.out.println(data.getNationality());
					System.out.println(data.getPlaceOfBirth());
					System.out.println(data.getRoll_no());
					System.out.println(data.getStudent_id());
				}
				
//				MarksPredict mp = new MarksPredict();
//				LinearRegressionModel m = mp.predictionModel("ComputerNetworks");
//				Dataset<Row> result = mp.predictData(m,"ComputerNetworks");
//				result.show(10,10,true);
				
				//request.getSession().setAttribute("studentGenralInfo", passingStudentEntity);
				request.setAttribute("studentGenralInfo", passingStudentEntity);
				System.out.println("request Attribute set");
				response.setContentType("text/html");
				System.out.println("Context Path = " + request.getContextPath());
				RequestDispatcher rd =  request.getRequestDispatcher("/StudentPage/student.jsp");
				//request.getRequestDispatcher(request.getContextPath() + "/StudentPage/student.jsp").forward(request, response);
				//request.getRequestDispatcher("/StudentPage/student.jsp").forward(request, response);
				rd.forward(request, response);
				System.out.println("At request dispatcher");
				
				
				/*MarksPredict marksPredict = new MarksPredict();
				Dataset<Row> predictedData = marksPredict.marksPrediction("ComputerNetworks");
				List<String> l = predictedData.as(Encoders.STRING()).collectAsList();
				for(String s: l)
					System.out.println(s);	
				*/
			}

		}
		else if(userid.substring(0, 1).toLowerCase().equals("f")) {
			
			System.out.println("Entered into faculty login servlet");
			FacultyLoginEntity facultyLoginDetails = new FacultyLoginEntity();
			
			facultyLoginDetails.setFaculty_id(userid);
			facultyLoginDetails.setPassword(password);
			
			UserLoginManager userLogin = new UserLoginManager();
			
			boolean isValidUser = userLogin.facultyLogin(facultyLoginDetails);
			
			if(isValidUser) {
				GetDetailsManager facultyGenralInfo = new GetDetailsManager();
				List<FacultyEntity> facultyData = facultyGenralInfo.getfacultyGenralInfo(facultyLoginDetails);
				
				FacultyEntity passingFacultyEntity = new FacultyEntity();
				
				System.out.println("UserLoginServlet: Faculty data recieved");
				
				for(FacultyEntity data : facultyData) {
					
					passingFacultyEntity.setContact_no(data.getContact_no());
					passingFacultyEntity.setDegree(data.getDegree());
					passingFacultyEntity.setFaculty_id(data.getFaculty_id());
					passingFacultyEntity.setSubject(data.getSubject());
					
					System.out.println(data.getFaculty_id());
					System.out.println(data.getContact_no());
					System.out.println(data.getDegree());
					System.out.println(data.getSubject());
				
				}
				//request.getSession().setAttribute("studentGenralInfo", passingStudentEntity);
				request.setAttribute("facultyGenralInfo", passingFacultyEntity);
				response.setContentType("text/html");		
				RequestDispatcher rd =  request.getRequestDispatcher("/FacultyPage/faculty.jsp");
				//request.getRequestDispatcher(request.getContextPath() + "/StudentPage/student.jsp").forward(request, response);
				//request.getRequestDispatcher("/StudentPage/student.jsp").forward(request, response);
				rd.forward(request, response);
				//System.out.println("At request dispatcher");
			}
			
			
		}
		else if(userid.equals("admin")) {
			System.out.println("Entered admin login servlet");
			FacultyLoginEntity facultyLoginDetails = new FacultyLoginEntity();
			
			facultyLoginDetails.setFaculty_id(userid);
			facultyLoginDetails.setPassword(password);
			
			UserLoginManager userLogin = new UserLoginManager();
			boolean validUser = userLogin.facultyLogin(facultyLoginDetails);
			
			System.out.println("Valid User: " + validUser);
			
			if(validUser) {
				response.sendRedirect(request.getContextPath() + "/AdminPage/admin.jsp");
			}
		}
		else {
			System.out.println("Invalid user");
		}
	}
}
