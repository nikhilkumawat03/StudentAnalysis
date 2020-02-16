package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name="currentcomputernetwork")
public class CurrentComputerNetwork {
	@Id
	@Column(name="student_id", insertable=false, updatable=false)
	private String student_id;
	
	@Column(name="branch_id", insertable=false, updatable=false)
	private String branch_id;
	
	@Column(name="visited_resources", insertable=false, updatable=false)
	private int visited_resources;
	
	@Column(name="doubt_asked", insertable=false, updatable=false)
	private int doubt_asked;
	
	@Column(name="attendence", insertable=false, updatable=false)
	private String attendence;
	
	@Column(name="discipline_score", insertable=false, updatable=false)
	private int discipline_score;
	
	@Column(name="interest_level", insertable=false, updatable=false)
	private int interest_level;
	
	@Column(name="predicted_marks", insertable=false, updatable=false)
	private String predicted_marks;
	
	@Column(name="internal_marks", insertable=false, updatable=false)
	private String internal_marks;

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

	public int getVisited_resources() {
		return visited_resources;
	}

	public void setVisited_resources(int visited_resources) {
		this.visited_resources = visited_resources;
	}

	public int getDoubt_asked() {
		return doubt_asked;
	}

	public void setDoubt_asked(int doubt_asked) {
		this.doubt_asked = doubt_asked;
	}

	public String getAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}

	public int getDiscipline_score() {
		return discipline_score;
	}

	public void setDiscipline_score(int discipline_score) {
		this.discipline_score = discipline_score;
	}

	public int getInterest_level() {
		return interest_level;
	}

	public void setInterest_level(int interest_level) {
		this.interest_level = interest_level;
	}

	public String getPredicted_marks() {
		return predicted_marks;
	}

	public void setPredicted_marks(String predicted_marks) {
		this.predicted_marks = predicted_marks;
	}

	public String getInternal_marks() {
		return internal_marks;
	}

	public void setInternal_marks(String internal_marks) {
		this.internal_marks = internal_marks;
	}

}