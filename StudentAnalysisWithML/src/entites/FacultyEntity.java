package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="getFacultyGenralInfo",
			query = "SELECT facultyGenralInfo From FacultyEntity facultyGenralInfo"
						+" WHERE facultyGenralInfo.faculty_id LIKE :faculty_id")


@Entity
@Table(name="faculty")
public class FacultyEntity {
	@Id
	@Column(name = "faculty_id", insertable=true, updatable=true)
	private String faculty_id;
	
	@Column(name = "contact_no", insertable=true, updatable=true)
	private String contact_no;
	
	@Column(name = "subject", insertable=true, updatable=true)
	private String subject;
	
	@Column(name = "degree", insertable=true, updatable=true)
	private String degree;

	public String getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(String faculty_id) {
		this.faculty_id = faculty_id;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	
}
