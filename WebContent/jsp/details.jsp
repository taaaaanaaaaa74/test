<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>商品紹介</h2>
   <table border>
      <tr>
      <th>商品名</th>
      <th><%= request.getAttribute("jump") %></th>
      </tr>
	  <tr>
	  <td>カテゴリ</td>
	  <td><%= request.getAttribute("category") %></td>
	  </tr>
	  <tr>
	  <td>価格</td>
	  <td><%= request.getAttribute("price") %></td>
	  </tr>
	  <tr>
	  <td>在庫</td>
	  <td><%= request.getAttribute("zaiko") %>
	  </td>
	  <tr>
	  <td>商品紹介</td>
	  <td><%= request.getAttribute("syoukai") %>
   </table>
   <table>
      <tr>
      <td>個数<select name="cnt">
<% for(int i=1;i<=100;i++) { %>
<option value="<%= i %>"><%= i %></option>
<% } %>
</select>
      <td><input type="submit" value="カートへ"></td>
      <td><input type="button" onclick='<http://localhost:8080/ec/kennsaku.jsp'; value="戻る"></td>
      </table>


</body>
</html>