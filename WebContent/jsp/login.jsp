
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ecPr/css/login.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>ログイン</h1>
		<%
			if (request.getAttribute("notEntered") != null) {
				out.println("入力してください");
			} else if (request.getAttribute("disagree") != null) {
				out.println("名前かパスワードが不一致です");
			}
		%>
		<form action="http://localhost:8080/ecPr/loginServlet" method="post">
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
			<input type="submit" value="LOGIN">
		</form>
	</div>


</body>
</html>