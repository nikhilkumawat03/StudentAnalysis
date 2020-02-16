<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FacultyData</title>
</head>

	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>
	<%@page import="entites.FacultyEntity" %>
<body>
	<h3>Faculty Information</h3>
	<% 
		//List<FacultyEntity> FacultysData = (ArrayList<FacultyEntity>)request.getAttribute("FacultyGenralInfo");
		FacultyEntity facultyData = (FacultyEntity)request.getAttribute("facultyGenralInfo");
	 //for(FacultyEntity data : FacultysData) {
		System.out.println("In JSP for loop");
		out.println("Faculty_Id: " + facultyData.getFaculty_id()+"+\n");
		out.println("Contact_no: " + facultyData.getContact_no()+"\n");
		out.println("Degree: " + facultyData.getDegree()+"\n");
		out.println("Subject: " + facultyData.getSubject());
		
	 //}
	 
	%>
	
</body>
</html>