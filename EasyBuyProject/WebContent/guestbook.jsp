<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="childNav.jsp" %>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@include file="LeftBox.jsp" %>
	</div>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
			<c:forEach items="${commPage.newsList }" var="item">
				<li>
					<dl>
						<dt>${item.ec_content }</dt>
						<dd class="author">网友：${item.ec_nick_name}
						<span class="timer">${item.ec_create_time}</span>
						</dd>
						<dd>${item.ec_reply}
						</dd>
					</dl>
				</li>
			</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="ECommServlet?type=commList&pageIndex=1">首页</a></li>
					<c:if test="${commPage.currPageNo==1 }">
					</c:if>
					<c:if test="${commPage.currPageNo!=1 }">
					<li>...</li>
					<li><a href="ECommServlet?type=commList&pageIndex=${commPage.currPageNo-1 }" >${commPage.currPageNo-1 }</a></li>
					</c:if>
					<li class="current">${commPage.currPageNo }</li>
                    <c:if test="${commPage.currPageNo!=commPage.totalPageCount }">
					<li><a href="ECommServlet?type=commList&pageIndex=${commPage.currPageNo+1 }" >${commPage.currPageNo+1 }</a></li>
                    <li>...</li>
					</c:if>
					<c:if test="${commPage.currPageNo==commPage.totalPageCount }">
					</c:if>
					<li><a href="ECommServlet?type=commList&pageIndex=${commPage.totalPageCount }">尾页</a></li>
				</ul>
			</div>
			<div id="reply-box">
				<form id="guestBook" action="ECommServlet?type=addComm&guestName2=${user.eu_user_id}" method="post">
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" disabled="disabled" value="${user.eu_user_id}"/></td>
						</tr>						
						<tr>
							<td class="field">留言内容：</td>
							<td>
							<textarea name="guestContent"></textarea>
							<span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
