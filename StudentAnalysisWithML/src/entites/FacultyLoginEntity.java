package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="getFacultyIdPassword", query="SELECT loginDetails FROM FacultyLoginEntity loginDetails WHERE loginDetails.faculty_id LIKE :faculty_id")

@Entity
@Table(name="facultylogin")
public class FacultyLoginEntity {
	@Id
	@Column(name = "faculty_id", insertable = false, updatable = false)
	private String faculty_id;
	
	@Column(name = "password", insertable = false, updatable = false)
	private String password;

	public String getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(String faculty_id) {
		this.faculty_id = faculty_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
