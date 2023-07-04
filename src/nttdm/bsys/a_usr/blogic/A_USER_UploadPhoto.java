package nttdm.bsys.a_usr.blogic;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class A_USER_UploadPhoto
 */
public class A_USER_UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sessionDirectory = request.getParameter("sessionDirectory");
		ServletOutputStream out = response.getOutputStream();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		for(FileItem item : items) {
			if(!item.isFormField()) {
				String fileName = ((int) (Math.random() * 1000)) + item.getName();
				try {
					String filePath = sessionDirectory + File.separator + fileName;
					File file = new File(filePath);
					item.write(file);
					out.write(filePath.getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;//upload only 1 image
			}
		}
		out.flush();
		out.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}