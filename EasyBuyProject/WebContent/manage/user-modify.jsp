<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<%
String strTime=request.getParameter("eu_birthday");
SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
Date date=formatter.parse(strTime);
%>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li class="current"><a href="../EUserServlet?type=userList">用户</a></li>
			<li><a href="../EProductServlet?type=proList">商品</a></li>
			<li><a href="../EOrderDetailServlet?type=eodList">订单</a></li>
			<li><a href="../ECommServlet?type=commList2">留言</a></li>
			<li><a href="../ENewsServlet?type=newsList">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${user.eu_user_id}您好，今天是
<fmt:formatDate value="${cTime}" pattern="yyyy-MM-dd"/>
，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<%@include file="leftBox.jsp" %>
	</div>
	<div class="main">
		<h2>修改用户</h2>
		<div class="manage">
			<form action="../EUserServlet?type=updUser&eu_id=${param.eu_id}" method="post">
				<table class="form">
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input type="text" class="text" name="userName" value="${param.eu_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">真实姓名(*)：</td>
						<td><input type="text" class="text" name="name" value="${param.eu_name }" /></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${param.eu_password }" /></td>
					</tr>
                    <tr>
						<td class="field">确认密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${param.eu_password }" /></td>
					</tr>
					<tr>
						<td class="field">性别(*)：</td>
						<td>
						<c:if test="${param.eu_sex=='M' }">
						<input type="radio" name="sex" value="M" checked="checked" />男 
						<input type="radio" name="sex" value="F" />女
						</c:if>
						<c:if test="${param.eu_sex=='F' }">
						<input type="radio" name="sex" value="M" />男 
						<input type="radio" name="sex" value="F" checked="checked"/>女
						</c:if>
						
						</td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<input type="text" class="text" name="birthday" 
							value="<%=date.getYear()+1900%>-<%=date.getMonth()+1%>-<%=date.getDate()%>">
						</td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>
						<td><input type="text" class="text" name="mobile" value="${param.eu_mobile }" /></td>
					</tr>
					<tr>
						<td class="field">地址(*)：</td>
						<td><input type="text" class="text" name="address" value="${param.eu_address }" /></td>
					</tr>					
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
