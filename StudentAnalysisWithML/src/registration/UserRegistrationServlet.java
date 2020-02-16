package registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entites.FacultyRegistrationEntity;
import entites.StudentRegistrationEntity;
import entitymanager.UserRegistrationManager;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/RegisterPage/register")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Servlet called to accept registration details
		System.out.println("Servlet called to generate registraion details");
		
		//Setting up parameters values
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("userid");
		String password = request.getParameter("pass");
		String repeat_password = request.getParameter("repeat-pass");
		
		//Checking condition for type of user
		System.out.println("Type of user:");
		
		if(password.equals(repeat_password)) {
			
			//Password matched successful
			System.out.println("Password match succesful.");
			
			HttpSession session = request.getSession(true);
			System.out.println("HttpSession Created.");
			
			if(id.substring(0,1).toLowerCase().equals("s")) {
				try {
					
					System.out.println("Student information is adding to StudentRegistrationEntity.");
					
					StudentRegistrationEntity StudentRegistration = new StudentRegistrationEntity();
					StudentRegistration.setStudent_id(id);
					StudentRegistration.setStudent_name(name);
					StudentRegistration.setStudent_mail_id(email);
					StudentRegistration.setPassword(password);			
					
					UserRegistrationManager student_details = new UserRegistrationManager();
					
					//User Registration manager is called to update student data in table
					System.out.println("User Registration manager is called.");
					
					student_details.addStudentDetails(StudentRegistration);
					
					System.out.println("Student details are added.");
				}
				catch(Exception e) {
					
					e.printStackTrace();
					response.getWriter().append("Already a User.");
					
				}
			}
			
			else if(id.substring(0,1).toLowerCase().equals("f")) {
				try {

					FacultyRegistrationEntity facultyRegistration = new FacultyRegistrationEntity();
					facultyRegistration.setFaculty_id(id);
					facultyRegistration.setFaculty_name(name);
					facultyRegistration.setFaculty_mail_id(email);
					facultyRegistration.setPassword(password);
					
					UserRegistrationManager faculty_details = new UserRegistrationManager();
					faculty_details.addFacultyDetails(facultyRegistration);
				}catch(Exception e) {
					e.printStackTrace();
					response.getWriter().append("Already a User.");
				}
			}
			else {
				response.getWriter().append("You are not a Valid User.");
				//Here show a Message to the User.
			}
		}
		else {	
			response.getWriter().append("Password Not Matched.");
			//Here show a Message to the User.
			
		}

	}

}
