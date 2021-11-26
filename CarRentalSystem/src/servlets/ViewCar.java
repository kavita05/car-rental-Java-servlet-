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
import beans.OwnerBean;
import dao.CarDao;
import dao.OwnerDao;
@WebServlet("/ViewCar")
public class ViewCar extends HttpServlet {
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
		out.println("<title>View Cars</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navowner.html").include(request, response);
		
		out.println("<div class='container'>");
		
		List<CarBean> list=CarDao.view();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Car Number</th><th>Car Name</th><th>Company</th><th>Condition</th><th>Quantity</th><th>Issued</th><th>Delete</th></tr>");
		for(CarBean bean:list){
			out.println("<tr><td>"+bean.getCallno()+"</td><td>"+bean.getCarname()+"</td><td>"+bean.getCompany()+"</td><td>"+bean.getCondition()+"</td><td>"+bean.getQuantity()+"</td><td>"+bean.getIssued()+"</td><td><a href='DeleteCar?callno="+bean.getCallno()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
