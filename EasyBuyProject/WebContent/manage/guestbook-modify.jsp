<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li><a href="../EUserServlet?type=userList">用户</a></li>
			<li><a href="../EProductServlet?type=proList">商品</a></li>
			<li><a href="../EOrderDetailServlet?type=eodList">订单</a></li>
			<li class="current"><a href="../ECommServlet?type=commList2">留言</a></li>
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
		<h2>回复留言</h2>
		<div class="manage">
			<form action="../ECommServlet?type=updComm&ec_id=${param.ec_id }" method="post">
				<table class="form">
					<tr>
						<td class="field">留言编号：</td>
						<td>${param.ec_id }</td>
					</tr>
					<tr>
						<td class="field">留言姓名：</td>
						<td>${param.ec_nick_name }</td>
					</tr>
					<tr>
						<td class="field">留言内容：</td>
						<td>${param.ec_content}</td>
					</tr>
					<tr>
						<td class="field">回复内容：</td>
						<td><textarea name="replyContent"></textarea></td>
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
