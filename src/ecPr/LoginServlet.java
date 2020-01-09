package ecPr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//名前とパスワードを受け取る
		String loginCode = request.getParameter("userName");
		String loginPass = request.getParameter("userPassword");

//		out.println("<html><head><title>aa</title></head>");
//		out.println("<body>");
//		out.println("loginCode:" + loginCode + "<br>");
//		out.println("loginPass;" + loginPass);
//		out.println("<br>");

		//空白がある場合に入力をうながす
	//----------------未完
//		if (loginCode == null || loginPass == null){
//			/* jspに渡す表示の処理 */
//			HttpSession session = request.getSession(true);
//			request.setAttribute("code", "test");
//			request.setAttribute("pass", "test");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
//			dispatcher.forward(request, response);
	//---------------------

//		}
		//判定してforward, aaはmysqlから持ってくる
//		if(loginCode.equals("aa") && loginPass.equals("aa")) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("loginCode", loginCode);
//			session.setAttribute("loginPass", loginPass);
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/dispatch.jsp");
//			rd.forward(request, response);
//		}//if

		//dbに接続
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			String url = "jdbc:mysql://localhost/st";
			String id = "root";
			String pass = "password";
			conn = DriverManager.getConnection(url, id, pass);

			stmt = conn.createStatement();

			//テーブルuserを参照
			String query = "select * from user";
			rs = stmt.executeQuery(query);


			while (rs.next()) {
				int id1 = rs.getInt(1);
				String useName = rs.getString("user_name");
				String loginCd = rs.getString("login_cd");
				String loginPw = rs.getString("login_pw");
				//loginCode loginPassが一致するか判定
				//session開始、検索画面に遷移

				if (loginCode.equals(loginCd) && loginPass.equals(loginPw) ) {
					HttpSession session = request.getSession(true);
					session.setAttribute("user", loginCode);
					session.setAttribute("password", loginPass);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
					rd.forward(request, response);
					//リダイレクト?
					//response.sendRedirect("");

					//break;
				}

			}//while
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}


//		//request.setAttribute("result", "aaaaaaaaaaaa");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Included");
//			if(loginPass == null) {
//				rd.include(request, response);
//				}//if
//		out.println("<br></body></html>");

	}

}
