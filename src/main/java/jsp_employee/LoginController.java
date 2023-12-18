package jsp_employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		EmployeeCRUD crud = new EmployeeCRUD();
		
		String dbPassword = null;
		try {
			dbPassword = crud.getEmployee(email);

			RequestDispatcher dispatcher = null;
			if (dbPassword != null) {
				if (dbPassword.equals(password)) {
					
					//Starting http session
					HttpSession httpSession=req.getSession();
					httpSession.setAttribute("session", email);
					System.out.println(httpSession);
					
					//creating cookie
					Cookie cookie=new Cookie("userinfo",email);
					resp.addCookie(cookie);
					List<Employee> list = crud.getAllEmployees();
					req.setAttribute("list", list);


					dispatcher = req.getRequestDispatcher("success.jsp");
				} else {

					req.setAttribute("message", "Login Unsuccessful,Please check your password");
					dispatcher = req.getRequestDispatcher("login.jsp");
				}
			} else {
				req.setAttribute("message", "User not found please register");
				dispatcher = req.getRequestDispatcher("signup.jsp");
			}
			dispatcher.forward(req, resp);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
