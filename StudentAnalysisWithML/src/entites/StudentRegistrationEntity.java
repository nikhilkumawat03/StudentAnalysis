package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="studentlogin")
public class StudentRegistrationEntity {
	
	@Id
	@Column(name = "student_id", insertable = true, updatable = false)
	private String student_id;
	
	@Column(name = "student_name", insertable = true, updatable = false)
	private String student_name;
	
	@Column(name = "password", insertable = true, updatable = false)
	private String password;
	
	@Column(name = "student_mail_id", insertable = true, updatable = false)
	private String student_mail_id;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudent_mail_id() {
		return student_mail_id;
	}
	public void setStudent_mail_id(String student_mail_id) {
		this.student_mail_id = student_mail_id;
	}
}
