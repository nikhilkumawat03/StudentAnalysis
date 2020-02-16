package fileuploadhandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entites.StudentEntity;
import entitymanager.InsertDataManager;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/AdminPage/uploadFile")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private File file;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	filePath = getServletContext().getInitParameter("file-upload");
    	System.out.println("File Path is:" + filePath);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String fileType = request.getParameter("fileType"); // Retrieves <input type="text" name="description">
		 Part filePart = request.getPart("filename"); // Retrieves <input type="file" name="file">
		 String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		 System.out.println("File name is:" + fileName);
		 System.out.println("File type is:" + fileType);
		 InputStream fileContent = filePart.getInputStream();
		 
		 byte[] buffer = new byte[fileContent.available()];
		 fileContent.read(buffer);
		 
		 File targetFile = new File(filePath + File.separator + fileName);
		 
		 OutputStream outStream = new FileOutputStream(targetFile);
		 outStream.write(buffer);
		 
		 System.out.println("File uploaded successfully");
			
			InsertDataManager insertDataManager = new InsertDataManager();
			
			try {
				insertDataManager.insertData("E:\\SparkProject\\StudentCSVFile\\" + fileName, fileType);
			} catch (ParseException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*if(ServletFileUpload.isMultipartContent(request)) {
			
			
			try{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for(FileItem item : multiparts) {
					if(!item.isFormField()) {
						String name = new File(item.getName()).getName();
						System.out.println("name of uploaded file is: " + name);
						
						System.out.println("Field name is: " + item.getFieldName());
						
						item.write(new File(filePath + File.separator + name));
						
						System.out.println("File uploaded successfully");
						
						InsertDataManager insertDataManager = new InsertDataManager();
						//System.out.println("File Type is: " + fileType);
						
						//insertDataManager.insertData("E:\\SparkProject\\StudentCSVFile\\" + name, fileType);
						//insertDataManager.insertStudentData("E:\\SparkProject\\StudentCSVFile\\" + name);
						//If faculty data is to be inserted then 
						
						//insertDataManager.insertFacultyData("E:\\SparkProject\\FacultyCSVFile\\" + name);
						
					}
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}	*/
	}
}
