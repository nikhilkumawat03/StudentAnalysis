<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Upload Student Or Faculty CSV file</h2>
	Select a file to upload: </br>
	<form action="uploadFile" method=post enctype="multipart/form-data">
		<input type="radio" name="fileType" value="GeneralData" checked> General Faculty/Student Data<br>
		<input type="radio" name="fileType" value="studentPerformance"> Student Performance Data<br>
		<input type = "file" name = "filename" ></br></br>
		<input type = "submit" value = "upload">
	</form>
</body>
</html>