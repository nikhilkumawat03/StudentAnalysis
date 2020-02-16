package entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({@NamedQuery(name="getStudentGenralInfo", 
			query = "SELECT studentGenralInfo FROM StudentEntity studentGenralInfo"
					+ " WHERE studentGenralInfo.student_id LIKE :student_id"),
			})

/* @NamedQuery(name="insertStudentGenralInfo",
query = "INSERT INTO studentEntity VALUES(:student_id,:branch_id,:roll_no,"
		+ ":gender,:nationality,:placeOfBirth,:dayScholar")*/

@Entity
@Table(name="student")
public class StudentEntity {
	
	@Id
	@Column(name = "student_id", insertable=true, updatable=true)
	private String student_id;
	
	@Column(name = "branch_id", insertable=true, updatable=true)
	private String branch_id;
	
	@Column(name = "roll_no", insertable=true, updatable=true)
	private String roll_no;
	
	@Column(name = "gender", insertable=true, updatable=true)
	private String gender;
	
	@Column(name = "nationality", insertable=true, updatable=true)
	private String nationality;
	
	@Column(name = "placeOfBirth", insertable=true, updatable=true)
	private Date placeOfBirth;
	
	@Column(name = "dayScholar", insertable=true, updatable=true)
	private String dayScholar;

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	 
	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Date date) {
		this.placeOfBirth = date;
	}

	public String getDayScholar() {
		return dayScholar;
	}

	public void setDayScholar(String dayScholar) {
		this.dayScholar = dayScholar;
	}
	
	
}
