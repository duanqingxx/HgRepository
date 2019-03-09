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
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="EProductBuyCarServlet?type=buyProductCar2" method="post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${BuyCarList }" var="item" varStatus="status">
				<tr id="product_id_${status.index }">
					<td class="thumb"><img src="images/product/${item.ep_file_name }_small.jpg" />
					<a href="EProductServlet?type=detailPro&ep_id=${item.ep_id}&epc_id=${item.epc_id}">
					${item.ep_name }</a>
					<input type="hidden" name="productId" value="${item.ep_id}" />
					</td>
					<td class="price" id="price_id_${status.index }">
						<span>￥${item.ep_price }</span>
						<input type="hidden" value="${item.ep_price}" />
					</td>
					<c:forEach items="${BuyCarNum }" var="item2">
					<c:if test="${item2.key==item.ep_id }">
					<td class="number">
                        <span name="del">-</span>
                        <input id="number_id_${status.index }" 
                        type="text" name="number" value="${item2.value }" />
                        <span name="add">+</span>
					</td>
					</c:if>
					</c:forEach>
					<td class="delete"><a href="javascript:void(0)">删除</a></td>
				</tr>
				</c:forEach>
			</table>
            <div class="total"><span id="total">总计：￥0</span></div>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
