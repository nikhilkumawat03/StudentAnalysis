package entites;

import javax.persistence.*;


@NamedQuery(name="ComputerNetworksPerformance", query="SELECT subjectData "
													+ "FROM ComputerNetwork subjectData "
													+ "WHERE subjectData.student_id LIKE :student_id")
@Entity
@Table (name="computernetworks")
public class ComputerNetwork {

	@Id
	@Column(name="student_id", insertable=true, updatable=false)
	private String student_id;
	
	@Column(name="branch_id", insertable=true, updatable=false)
	private String branch_id;
	
	@Column(name="visited_resources", insertable=true, updatable=false)
	private int visited_resources;
	
	@Column(name="doubt_asked", insertable=true, updatable=false)
	private int doubt_asked;
	
	@Column(name="attendence", insertable=true, updatable=false)
	private String attendence;
	
	@Column(name="discipline_score", insertable=true, updatable=false)
	private int discipline_score;
	
	@Column(name="interest_level", insertable=true, updatable=false)
	private int interest_level;
	
	@Column(name="external_marks", insertable=true, updatable=false)
	private String external_marks;
	
	@Column(name="internal_marks", insertable=true, updatable=false)
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

	public String getExternal_marks() {
		return external_marks;
	}

	public void setExternal_marks(String external_marks) {
		this.external_marks = external_marks;
	}

	public String getInternal_marks() {
		return internal_marks;
	}

	public void setInternal_marks(String internal_marks) {
		this.internal_marks = internal_marks;
	}
	
	
	
}
