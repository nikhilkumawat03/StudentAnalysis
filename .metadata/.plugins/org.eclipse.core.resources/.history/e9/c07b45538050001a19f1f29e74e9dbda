package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="getUserIdPassword", query="SELECT loginDetails.student_id, loginDetails.password "
		+ "FROM StudentLoginEntity loginDetails WHERE loginDetails.student_id LIKE :student_id")
@Entity
@Table(name="studentlogin")
public class StudentLoginEntity {
	
	@Id
	@Column(name = "student_id", insertable = false, updatable = false)
	private String student_id;
	
	@Column(name = "password", insertable = false, updatable = false)
	private String password;

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
