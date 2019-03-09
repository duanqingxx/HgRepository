<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/eltag1" prefix="el" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/index.js"></script>
<script type="text/javascript">
var CarNum="${BuyCarList.size()}";
</script>
</head>
<body>
<div id="welcomeImage">
    <img width="100%" height="150" src="images/banner.jpg" alt="welcome">
</div>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
	<c:if test="${user!=null }">
	<a href="shopping.jsp" id="shoppingBag" class="shopping">购物车X件</a>
	</c:if>
	<a href="login.jsp">登录</a>
	<c:if test="${user!=null }">
	<a class="button" id="logout" href="javascript:void(0);">注销</a>
	</c:if>
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
<%@include file="childNav.jsp" %>
<div id="main" class="wrap">
	<div class="lefter">
		<%@include file="LeftBox.jsp" %>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix1">
          <c:forEach items="${cookie}" var="item">
          <c:if test="${ fn:startsWith(item.key, 'CHANCE_PRODUCT')}">
          <c:set var="ep_id_num" value="${fn:substring(item.value.value,0,fn:indexOf(item.value.value,',')) }"/>
          <c:set var="pName" value="${fn:substring(item.value.value,fn:indexOf(item.value.value,',')+1,fn:indexOf(item.value.value,'|')) }"/>
          <c:set var="epc_id_num" value="${fn:substringAfter(item.value.value,'|')}"/>
          <dt><img src="images/product/${ep_id_num}_small.jpg" /></dt>
		  <dd>
		  <a href="EProductServlet?type=detailPro&ep_id=${ep_id_num}&epc_id=${ epc_id_num}"  target="_self">
		  ${el:urlDecode(pName)}</a>
		  </dd>
          </c:if>
          </c:forEach>
		  </dl>  
	  </div>
	</div>
	<div class="main">
		<div class="price-off">
            <div class="slideBox">
                <ul id="slideBox">
                    <li><img src="images/product/banner_1.jpg"/></li>
                    <li><img src="images/product/banner_2.jpg"/></li>
                    <li><img src="images/product/banner_3.jpg"/></li>
                    <li><img src="images/product/banner_4.jpg"/></li>
                </ul>
            </div>
			<h2>商品列表</h2>
			<ul class="product clearfix" id="hPI">
			
			</ul>
		</div>
		<div class="side">			
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<div id="rollDiv">
				<ul id="rollul">
				</ul>
				</div>
			</div>
		</div>
		<div class="spacer clear"></div>
    </div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
