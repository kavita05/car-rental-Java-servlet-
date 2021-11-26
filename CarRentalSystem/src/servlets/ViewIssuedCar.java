package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CarBean;
import beans.IssueCarBean;
import dao.CarDao;
@WebServlet("/ViewIssuedCar")
public class ViewIssuedCar extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Issued Car</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navowner.html").include(request, response);
		
		out.println("<div class='container'>");
		
		List<IssueCarBean> list=CarDao.viewIssuedCars();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Car Number</th><th>Customer Id</th><th>Customer Name</th><th>Customer Mobile</th><th>Issued Date</th><th>Return Status</th></tr>");
		for(IssueCarBean bean:list){
			out.println("<tr><td>"+bean.getCallno()+"</td><td>"+bean.getCustomerid()+"</td><td>"+bean.getCustomername()+"</td><td>"+bean.getCustomermobile()+"</td><td>"+bean.getIssueddate()+"</td><td>"+bean.getReturnstatus()+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
