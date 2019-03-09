<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="http://localhost:8080/EasyBuyProject/css/style.css" />
<script type="text/javascript" src="http://localhost:8080/EasyBuyProject/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/EasyBuyProject/scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="http://localhost:8080/EasyBuyProject/images/logo.gif" /></div>
	<div class="help"><a href="http://localhost:8080/EasyBuyProject/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="manage/index.jsp">首页</a></li>
			<li class="current"><a href="EUserServlet?type=userList">用户</a></li>
			<li><a href="EProductServlet?type=proList">商品</a></li>
			<li><a href="EOrderDetailServlet?type=eodList">订单</a></li>
			<li><a href="ECommServlet?type=commList2">留言</a></li>
			<li><a href="ENewsServlet?type=newsList">新闻</a></li>
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
	您现在的位置：<a href="manage/index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<%@ include file="leftBox2.jsp" %>
	</div>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userPage.newsList }" var="item" >
				<tr>
					<td class="first w4 c">${item.eu_user_id }</td>
					<td class="w1 c">${item.eu_user_name }</td>
					<c:if test="${item.eu_sex=='M' }">
					<td class="w2 c">男</td>
					</c:if>
					<c:if test="${item.eu_sex=='F' }">
					<td class="w2 c">女</td>
					</c:if>
					<td>${item.eu_email }</td>
					<td class="w4 c">${item.eu_mobile }</td>
					<td class="w1 c">
					<a href="manage/user-modify.jsp?eu_id=${item.eu_user_id}&
					eu_password=${item.eu_passWord}&eu_name=${item.eu_user_name}&
					eu_sex=${item.eu_sex}&eu_email=${item.eu_email }&
					eu_mobile=${item.eu_mobile}&eu_birthday=${item.eu_birthday}&
					eu_address=${item.eu_address}">修改</a>
					<a class="manageDel" href="javascript:void(0)"
					url="EUserServlet?type=delUser&eu_id=${item.eu_user_id}">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="clear"></div>
     <div class="pager">
               <ul class="clearfix">
					<li><a href="EUserServlet?type=userList&pageIndex=1">首页</a></li>
					<c:if test="${userPage.currPageNo==1 }">
					</c:if>
					<c:if test="${userPage.currPageNo!=1 }">
					<li>...</li>
					<li><a href="EUserServlet?type=userList&pageIndex=${userPage.currPageNo-1 }" >${userPage.currPageNo-1 }</a></li>
					</c:if>
					<li class="current">${userPage.currPageNo }</li>
                    <c:if test="${userPage.currPageNo!=userPage.totalPageCount }">
					<li><a href="EUserServlet?type=userList&pageIndex=${userPage.currPageNo+1 }" >${userPage.currPageNo+1 }</a></li>
                    <li>...</li>
					</c:if>
					<c:if test="${userPage.currPageNo==userPage.totalPageCount }">
					</c:if>
					<li><a href="EUserServlet?type=userList&pageIndex=${userPage.totalPageCount }">尾页</a></li>
				</ul>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
