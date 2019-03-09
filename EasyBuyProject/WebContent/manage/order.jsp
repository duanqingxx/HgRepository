<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.duan.m.entity.*"%>
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
<script type="text/javascript" src="http://localhost:8080/EasyBuyProject/scripts/order.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="http://localhost:8080/EasyBuyProject/images/logo.gif" /></div>
	<div class="help"><a href="http://localhost:8080/EasyBuyProject/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="manage/index.jsp">首页</a></li>
			<li><a href="EUserServlet?type=userList">用户</a></li>
			<li><a href="EProductServlet?type=proList">商品</a></li>
			<li class="current"><a href="EOrderDetailServlet?type=eodList">订单</a></li>
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
		<h2>订单管理</h2>
		<div class="manage">
			<div class="search">				
			</div>
			<div class="spacer"></div>
            <form id="orderForm" method="post"  action="EOrderDetailServlet?type=findOrder">
                 订单号：<input type="text" class="text" name="entityId" id="entityId" />
                 订货人：<input type="text" class="text" name="userName" />
                 <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
            </form>
            
		<table class="list">
				<c:forEach items="${eodPage.newsList}" var="item">
				<tr>
					<th colspan="2">单号：${item.eo_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					                                         时间：<fmt:formatDate value="${item.eo_create_time }" pattern="yyyy-MM-dd"/>
					</th>					
					<th colspan="2">
					状态:
					<select class="orderStatus" orderId="${item.eo_id }" orderStatus="${item.eo_status}" name="status">						    
								<option value="1"  >待审核</option>
								<option value="2"  >审核通过</option>
								<option value="3"  >配货</option>
								<option value="4"  >发货</option>
								<option value="5"  >收货确认</option>
					</select>
					</th>
				<c:set var="Num" value="${0 }"/>
				<c:forEach items="${item.eodList }">
				<c:set var="Num" value="${Num+1 }"/>
				</c:forEach>
				<c:forEach items="${item.eodList }" var="item1" varStatus="status">
				<tr>
					<td class="first w4 c">
					<img src="http://localhost:8080/EasyBuyProject
					/images/product/${item1.product.ep_id}.jpg" />
					${item1.product.ep_name}
					</td>
					<td >单价：${item1.product.ep_price }</td>
					<td>数量：${item1.eod_quantity}</td>
					<c:if test="${status.index==0 }">
					
				    <td class="w1 c" rowspan="${Num }">总计：${item.eo_cost }</td>
					</c:if>
				</tr>
				</c:forEach>
				
				</tr>
				</c:forEach>							
		</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="EOrderDetailServlet?type=eodList&pageIndex=1">首页</a></li>
					<c:if test="${eodPage.currPageNo==1 }">
					</c:if>
					<c:if test="${eodPage.currPageNo!=1 }">
					<li>...</li>
					<li><a href="EOrderDetailServlet?type=eodList&pageIndex=${eodPage.currPageNo-1 }" >${eodPage.currPageNo-1 }</a></li>
					</c:if>
					<li class="current">${eodPage.currPageNo }</li>
                    <c:if test="${eodPage.currPageNo!=eodPage.totalPageCount }">
					<li><a href="EOrderDetailServlet?type=eodList&pageIndex=${eodPage.currPageNo+1 }" >${eodPage.currPageNo+1 }</a></li>
                    <li>...</li>
					</c:if>
					<c:if test="${eodPage.currPageNo==eodPage.totalPageCount }">
					</c:if>
					<li><a href="EOrderDetailServlet?type=eodList&pageIndex=${eodPage.totalPageCount }">尾页</a></li>
				</ul>
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
