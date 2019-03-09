<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
	<c:if test="${user!=null }">
	<a href="shopping.jsp" class="shopping">购物车X件</a>
	</c:if>
	<a href="login.jsp">登录</a>
	<a href="register.jsp">注册</a>
	<c:if test="${user!=null }">
	<a href="ECommServlet?type=commList">留言</a>
	</c:if>
	<c:if test="${user!=null&&user.eu_status==2 }">
	<a href="manage/index.jsp">后台管理</a>
	</c:if>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index.jsp">首页</a></li>
			<c:forEach items="${LeftCate }" var="item" varStatus="status">
			<c:if test="${item.epc_id2==0 }">
			<li><a href="EProductServlet?type=proList3&
			epc_id=${item.epc_id}">${item.epc_name }</a></li>
			</c:if>
			</c:forEach>
		</ul>
	</div>
</div>