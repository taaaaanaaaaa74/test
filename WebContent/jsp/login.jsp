<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="http://localhost:8080/ecPr/loginServlet" method="post">
	<%
        if (request.getAttribute("notEntered") != null ){
            out.println("入力してください");
        }
        %>
		<table>
		<tr>
		 <th>名前</th>
		 <td><input type="text" name="userName"></td>
		 </tr>
		<tr>
		<th>パスワード</th>
		 <td><input type="text" name="userPassword"></td>
		 </tr>
		 </table>
		 <input type="submit" value="LOGIN" >
	</form>


</body>
</html>