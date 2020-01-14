package ecPr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailsServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		//テキストエリアに入力された値を受け取る
		String text = request.getParameter("categoryName");
		//select内からvalueを受け取る
		String select = request.getParameter("category");

		out.println("<html><head><title>aa</title></head>");
		out.println("<body>");
		out.println("TEXT:" + text + "<br>");
		out.println("SELECT;" + select);
		out.println("<br>");
		out.println("</body>");




	}

}
