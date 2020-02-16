package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="facultylogin")
public class FacultyRegistrationEntity {
	
	@Id
	@Column(name = "faculty_id", insertable = true, updatable = false)
	private String faculty_id;
	
	@Column(name = "faculty_name", insertable = true, updatable = false)
	private String faculty_name;
	
	@Column(name = "password", insertable = true, updatable = false)
	private String password;
	
	@Column(name = "faculty_mail_id", insertable = true, updatable = false)
	private String faculty_mail_id;
	
	
	public String getFaculty_id() {
		return faculty_id;
	}
	
	public void setFaculty_id(String faculty_id) {
		this.faculty_id = faculty_id;
	}
	
	public String getFaculty_name() {
		return faculty_name;
	}
	
	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFaculty_mail_id() {
		return faculty_mail_id;
	}
	public void setFaculty_mail_id(String faculty_mail_id) {
		this.faculty_mail_id = faculty_mail_id;
	}
}
