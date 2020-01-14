package ecPr;

import java.io.IOException;
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
	int count = 0;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//o名前とパスワードを受け取る
		String loginCode = request.getParameter("userName");
		String loginPass = request.getParameter("userPassword");


		//dbに接続
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
									//com.mysql.cj.jdbc.Driver (MySQL8.0以降)
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			String url = "jdbc:mysql://localhost:3306/st";
							//jdbc:mysql://localhost:3306/st?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true （mysql8.0以降）
			String id = "root";
			String pass = "password";
			conn = DriverManager.getConnection(url, id, pass);

			stmt = conn.createStatement();

			//oテーブルuserを参照
			String query = "select * from user";
			rs = stmt.executeQuery(query);


			while (rs.next() ) {
				//String useName = rs.getString("user_name");
				String loginCd = rs.getString("login_cd");
				String loginPw = rs.getString("login_pw");
				//loginCode loginPassが一致するか判定
				//一致すればsession開始、検索画面に遷移
				if (loginCode.equals("") || loginPass.equals("") ){
					//oログインコードかログインパスワードのいずれかが空白の場合の処理
					//HttpSession session = request.getSession(true); //これたぶん必要ない
					request.setAttribute("notEntered", "no");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
					dispatcher.forward(request, response);
					count += 1;
					break;
				}//if
				else if (loginCode.equals(loginCd) && loginPass.equals(loginPw) ) {
					HttpSession session = request.getSession(true);
					session.setAttribute("user", loginCode);
					session.setAttribute("password", loginPass);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
					rd.forward(request, response);
					count += 1;
					break;

					}//else if
				}//while
				//oログインコードとログインパスワードのいずれかが間違っている場合の処理
				if(count < 1) {
				request.setAttribute("disagree", "no");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
				dispatcher.forward(request, response);
				}

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

			} catch (Exception ex) {
				// TODO: handle exception
			}
		}

//		out.println("<html><head><title>aa</title></head>");
//		out.println("<body>");
//		out.println("loginCode:" + loginCode + "<br>");
//		out.println("loginPass;" + loginPass);
//		out.println("<br>");

			/*
			 * 空白がある場合に元のjspで入力をうながす
			 * servletで空白の判定をするのでlogin.jspではパラメーターを受け取ったら「入力してください」でいい
			 */
//		//--o実装可能-----------------------------
//		if (loginCode.equals("") || loginPass.equals("") ){
//			/* jspに渡す表示の処理 */
//			HttpSession session = request.getSession(true);
//			request.setAttribute("notEntered", "no");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
//			dispatcher.forward(request, response);
//		}
//		//
			//loginCode,loginPasswordがあっているか判定
//		//oログインテスト開始------------------------
//		else if(loginCode.equals("aa") && loginPass.equals("aa") ) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("loginCode", loginCode);
//			session.setAttribute("loginPass", loginPass);
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/dispatch.jsp");
//			rd.forward(request, response);
//		}
//		//ログインテスト終点----------------------------------

//		else if (loginCode.equals("aa") && loginPass.equals("aa")) {
//			//ログインコードとログインパスワードが一致した場合の処理
//			HttpSession session = request.getSession(true);
//			session.setAttribute("loginCode", loginCode);
//			session.setAttribute("loginPass", loginPass);
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/dispatch.jsp");
//			rd.forward(request, response);
//		}

//		//実装可能--------------------------------------
//		//loginCodeかloginPasswordが不一致の場合
//		else {
//			request.setAttribute("disagree", "no");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
//			dispatcher.forward(request, response);
//
//		}
//		//----------------------------------------------------


		//------------------------
//		//request.setAttribute("result", "aaaaaaaaaaaa");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Included");
//			if(loginPass == null) {
//				rd.include(request, response);
//				}//if
		//---------------------------

//		out.println("<br></body></html>");

	}

}
