package ecPr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcTest {
	public static void main(String[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//dbに接続

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			/*
			 * ドライバ名を記述
			 */
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 8.0以前なら "com.mysql.jdbc.Driver" ？
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			/*
			 * url, id, passをそれぞれ入力
			 */
			String url = "jdbc:mysql://localhost:3306/st?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
			String id = "root";
			String pass = "1qaz2wsx";
			conn = DriverManager.getConnection(url, id, pass);

			stmt = conn.createStatement();

			/*
			 * テーブルuserを参照
			 */
			String query = "select * from user";
			rs = stmt.executeQuery(query);


			while (rs.next()) {
				String useName = rs.getString("user_name");
				String loginCd = rs.getString("login_cd");
				String loginPw = rs.getString("login_pw");

				System.out.println(loginCd);
				System.out.println(loginPw);

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

			} catch (Exception ex) {
				// TODO: handle exception
			}
		}


	}

}
