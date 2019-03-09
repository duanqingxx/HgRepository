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
   	您现在的位置：<a href="index.jsp">易买网</a>
	 &gt;<a href="EProductServlet?type=proList2&
			epc_id=${proCate2.epc_id}"> ${proCate1.epc_name }</a>
	 &gt; ${proCate2.epc_name}  
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@include file="LeftBox.jsp" %>
	</div>
	<div id="product" class="main">
		<h1>${product.ep_name}</h1>
		<div class="infos">
			<div class="thumb"><img src="images/product/${product.ep_file_name}.jpg" width="110" height="106" /></div>
			<div class="buy">
				商城价：<span class="price">￥${product.ep_price}</span><br />
				库　存：${product.ep_stock }
			  <div class="button">
			  <input type="button" name="button" value="" onclick=
				  "location.href = 'EProductBuyCarServlet?type=buyProduct&ep_id=${product.ep_id}'" />
			  <a href="EProductBuyCarServlet?type=buyProductCar1&ep_id=${product.ep_id}">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				${product.ep_description}<br />
				......<br />
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
