package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exceptions.InvalidParameterException;
import model.Person;
import model.Post;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String firstName=(String)session.getAttribute("first_name");
			String lastName=(String) session.getAttribute("last_name");
			String email=(String)session.getAttribute("email");
			String gender=(String) session.getAttribute("isMale");
			String about= (String)session.getAttribute("about");
			String phone = (String)session.getAttribute("phone");
			String location =(String)session.getAttribute("location");
			List<Post> posts= (List<Post>) session.getAttribute("posts");	
			Person user= new Person();
			try {
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setGender(gender);
				if(about!=null){
					user.setAbout(about);
				}
				if(phone!=null){
					user.setPhone(phone);
				}
				if(location!=null){
					user.setLocation(location);
				}
				if(posts!=null){
					
					user.setPosts(posts);
				}
				Gson gson= new Gson();
				String text=gson.toJson(user);
				request.setAttribute("gson",text);
				
				RequestDispatcher dispacher=request.getRequestDispatcher("./view/services.jsp");
				dispacher.forward(request, response);
				
			} catch (InvalidParameterException e) {
				e.printStackTrace();
			}
	}

}
