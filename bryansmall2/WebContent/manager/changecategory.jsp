<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改分类页面</title>
</head>
<body background="./images/background.jpg">
    <br/><br/>
    <form action="${pageContext.request.contextPath}/manager/CategoryServlet?method=changec" method="post">
    	<table width="500px;">
	    	<tr>
	    		<td>分类名称：</td>
	    		<td><input type="text" name="name" style="width: 200px"></td>
	    	<tr>
	    	<tr>
	    		<td>分类描述：</td>
	    		<td><textarea rows="4" cols="40" name="description"></textarea></td>
	    	</tr>
	    	<tr>
	    		<td></td><td><input type="submit" value="修改分类"></td>
	    	</tr>
    	</table>
    </form>
  </body>
</html>