<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ecPr/css/Search.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>検索</h1>
		<%
			if(request.getAttribute("notEntered") != null) {
				out.println("入力するかセレクトボックスから選択してください");
				}
		%>

		<form action="http://localhost:8080/ecPr/searchServlet" method="post">
			<input type="text" name="categoryText">
			<p>カテゴリー</p>
			<select name="category">
				<option value="notSelect">--選択--</option>
				<option value="manga">漫画</option>
				<option value="anime">アニメ</option>
				<option value="movie">映画</option>
			</select> <input type="submit" value="検索">
		</form>
	</div>
	<br>

	<form action="http://localhost:8080/exPr/DetailsServlet" method="post">
		<table>
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>詳細</th>
			</tr>
			<tr>
				<td>ジャンプ</td>
				<td>0000</td>
				<td>
					<div style="text-align: center;">
						<input style="text-align: center;" type="submit" name="jump"
							value="詳細">
					</div>
				</td>
			</tr>
		</table>

		<table>
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>詳細</th>
			</tr>
			<tr>
				<td>ドラえもん</td>
				<td>1111</td>
				<td>
					<div style="text-align: center;">
						<input type="submit" name="dora" value="詳細">
					</div>
				</td>
			</tr>
		</table>

		<table>
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>詳細</th>
			</tr>
			<tr>
				<td>ディズニー</td>
				<td>2222</td>
				<td>
					<div style="text-align: center;">
						<input type="submit" name="disney" value="詳細">
					</div>
				</td>
			</tr>
		</table>
	</form>


</body>
</html>