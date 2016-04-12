package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import exceptions.InvalidParameterException;
import model.PictureDAO;
import model.PersonDAO;

@WebServlet("/uploadCover")
@MultipartConfig
public class CoverUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMG_FOLDER_PATH = "C:\\Users\\Ivan\\Desktop\\Project\\WebContent\\view\\coverPhotos\\";
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = fileUpload(request, response);
		System.out.println("Cover photo name"+filePath);
		PersonDAO dao = new PersonDAO();
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.println(email);
		System.out.println(filePath);
		try {
			dao.updateCoverPhoto(filePath, email);
		} catch (InvalidParameterException e) {
			
			e.printStackTrace();
		}
		
		//session.setAttribute("uploadedCover", "Uploaded successfully");
		
		RequestDispatcher rd = request.getRequestDispatcher("./GetPersonInfo");
		rd.forward(request, response);
		return;
	}

	private String fileUpload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file

		final Part filePart = request.getPart("coverPhoto");
		
		System.out.println("[CoverPhotoUpload] --- filePart " + filePart);

		final String fileName = getFileName(filePart);
		
		OutputStream out = null;
		InputStream filecontent = null;

		String filePath = null;
		
		try {
			System.out.println("[CoverPhotoUpload] ---  Full path " + IMG_FOLDER_PATH + fileName);
			// ------------------------------------------------

			out = new FileOutputStream(new File(IMG_FOLDER_PATH + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			filePath =  fileName;
			return  filePath;
			
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}

		}
		return filePath;
	}

	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String result = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");

				System.out.println(result);
				return result;
			}
		}
		return null;
	}

}
