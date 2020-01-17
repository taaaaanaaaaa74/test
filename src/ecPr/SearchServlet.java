package ecPr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DetailsServlet
 */
public class SearchServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		//テキストエリアに入力された値を受け取る
		String text = request.getParameter("categoryText");
		//select内からvalueを受け取る
		String select = request.getParameter("category");
		//詳細ボタンからのリクエストを受け取る
		String details = request.getParameter("cat_id");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		//テキストエリアが空白かつセレクトボックスで選択されていない場合
		if(text.equals("") && select.equals("notSelect")) {
			request.setAttribute("notEntered", "no");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
			rd.forward(request, response);
		}
		else {
			try {
				/*
				 * ドライバ名を記述
				 */
				Class.forName("com.mysql.jdbc.Driver");
				//mysql 8.0以前なら "com.mysql.jdbc.Driver" ？
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			try {
				/*
				 * url, id, passをそれぞれ入力
				 */
				String url = "jdbc:mysql://localhost:3306/st"; //?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
				String id = "root";
				String pass = "password";
				conn = DriverManager.getConnection(url, id, pass);
				stmt = conn.createStatement();

				//セレクトボックスで選択があった場合
				if(text.equals("") && !(select.equals("")) ) {
					String query = "select cat_name from category ";
					rs = stmt.executeQuery(query);

					while (rs.next()) {
						String categoryName = rs.getString("cat_name");
						//セレクトボックスとcat_name from categoryが一致する場合
						if(categoryName.equals(select)) {
							//指定したcat_nameと同じレコードのcat_idを取り出す
							String query2 = "SELECT cat_id from category where cat_name= ?";
							PreparedStatement pstmt = conn.prepareStatement(query2);
							pstmt.setString(1, select);
							rs = pstmt.executeQuery();
						}
					}
				}//セレクトボックスで選択があった場合---
				else {
					//テキストエリアに入力があり、セレクトボックスでも選択があった場合
					if( !(text.equals("")) && !(select.equals("notSelect")) ){

						String query = "SELECT * from product where pro_name = ?";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, text);
						rs = pstmt.executeQuery();
						//とりあえずテキストエリアの文字列で全件一致させる
					}
					//テキストエリアで入力があった場合
					else if( !(text.equals("")) && select.equals("notSelect") ) {

						//入力された文字と完全一致する商品名を取り出す
						String query = "SELECT * from product where pro_name = ?";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, text);
						rs = pstmt.executeQuery();
					}

					while (rs.next()) {
						String productName = rs.getString("pro_name");

						out.println("<html><head><title>aa</title></head>");
						out.println("<body>");
						out.println("検索文字:"+ productName + "<br>");
						out.println("</body></html>");
					}//while
				}//else

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
		} //else



	}

}
