
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ログイン</h1>
	<%
        if (request.getAttribute("notEntered") != null){
            out.println("入力してください");
        }
        else if(request.getAttribute("disagree") != null) {
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
		 <input type="submit" value="LOGIN" >
	</form>


</body>
</html>