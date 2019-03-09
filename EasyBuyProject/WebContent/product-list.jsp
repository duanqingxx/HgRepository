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
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">
	<img src="images/logo.gif" /></div>
	<div class="help">
	<c:if test="${user!=null }">
	<a href="shopping.jsp" class="shopping">购物车X件</a>
	</c:if>
	<a href="login.jsp">登录</a>
	<a href="register.jsp">注册</a>
	<c:if test="${user!=null }">
	<a href="ECommServlet?type=commList">留言</a>
	<a href="manage/index.jsp">后台管理</a>
	</c:if>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<c:forEach items="${LeftCate }" var="item" varStatus="status">
			<c:if test="${item.epc_id2==0 }">
			<c:if test="${item.epc_id==proCate1.epc_id }">
			<li class="current"><a href="EProductServlet?type=proList3&
			epc_id=${item.epc_id}">${item.epc_name }</a></li>
			</c:if>
			<c:if test="${item.epc_id!=proCate1.epc_id }">
			<li><a href="EProductServlet?type=proList3&
			epc_id=${item.epc_id}">${item.epc_name }</a></li>
			</c:if>
			</c:if>
			</c:forEach>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<c:forEach items="${LeftCate }" var="item" varStatus="status">
		    <c:if test="${item.epc_id2!=0&&item.epc_id2==proCate1.epc_id}">
		    <li><a href="EProductServlet?type=proList2&
			epc_id=${item.epc_id}">${item.epc_name }</a></li>
			</c:if>
		    </c:forEach>
		    <li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
   	您现在的位置：<a href="index.jsp">易买网</a>
	 &gt;<a href="EProductServlet?type=proList2&
			epc_id=${proCate2.epc_id}"> ${proCate1.epc_name }</a>
	 &gt; ${proCate2.epc_name}  
</div>
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
			<%-- <%
			        Cookie []cookies = request.getCookies();
			        if(cookies != null && cookies.length > 0){
			            for(Cookie c :cookies){
			                String cookieName = c.getName();
			                if(cookieName.startsWith("CHANCE_PRODUCT")){
			                	String product=c.getValue();
			                	String   ep_id=product.substring(0,product.indexOf(","));
			                	String temp_name=product.substring(product.indexOf(",")+1,product.indexOf("|"));
			                	String ep_name=URLDecoder.decode(temp_name,"utf-8");
			                	String  epc_id=product.substring(product.indexOf("|")+1);
	out.println("<dt><img src=\"images/product/"+ep_id+"_small.jpg\" /></dt>");
	out.println("<dd><a href=\"EProductServlet?type=detailPro&ep_id="+ep_id+"&epc_id="+epc_id+"\"  target=\"_self\">");
	out.println(ep_name+"</a>");
	out.println("<a href=\"EProductServlet?type=detailPro&ep_id="+ep_id+"&epc_id="+epc_id+"\"></a></dd>");
	out.println("<br>"); 
			                }
			            }
			        }
            %> --%>
		    </dl>
		</div>
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>			
			<div class="clear"></div>
			<ul class="product clearfix">
			<c:forEach items="${proPage2.newsList }" var="item">
			    <li>
				    <dl>
						<dt><a href="EProductServlet?type=detailPro&ep_id=${item.ep_id}&epc_id=${item.epc_id}" target="_self">
						<img src="images/product/${item.ep_file_name}.jpg" /></a></dt>
						<dd class="title"><a href="EProductServlet?type=detailPro&ep_id=${item.ep_id }&epc_id=${item.epc_id}" target="_self">
						${item.ep_name}</a></dd>
						<dd class="price">￥${item.ep_price }</dd>
					</dl>
				</li>
			</c:forEach>				
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				<c:if test="${proCate2.epc_name!=null}">
					<li><a href="EProductServlet?type=proList2
					&epc_id=${proPage2.newsList[0].epc_id}&pageIndex=1">首页</a></li>
					<c:if test="${proPage2.currPageNo==1 }">
					</c:if>
					<c:if test="${proPage2.currPageNo!=1 }">
					<li>...</li>
					<li><a href="EProductServlet?type=proList2
					&epc_id=${proPage2.newsList[0].epc_id}&pageIndex=${proPage2.currPageNo-1 }" >${proPage2.currPageNo-1 }</a></li>
					</c:if>
					<li class="current">${proPage2.currPageNo }</li>
                    <c:if test="${proPage2.currPageNo!=proPage2.totalPageCount }">
					<li><a href="EProductServlet?type=proList2
					&epc_id=${proPage2.newsList[0].epc_id}&pageIndex=${proPage2.currPageNo+1 }" >${proPage2.currPageNo+1 }</a></li>
                    <li>...</li>
					</c:if>
					<c:if test="${proPage2.currPageNo==proPage2.totalPageCount }">
					</c:if>
					<li><a href="EProductServlet?type=proList2
					&epc_id=${proPage2.newsList[0].epc_id}&pageIndex=${proPage2.totalPageCount }">尾页</a></li>
				</c:if>
				<c:if test="${proCate2.epc_name==null}">
				<li><a href="EProductServlet?type=proList3
					&epc_id=${proPage2.newsList[0].epc_id2}&pageIndex=1">首页</a></li>
					<c:if test="${proPage2.currPageNo==1 }">
					</c:if>
					<c:if test="${proPage2.currPageNo!=1 }">
					<li>...</li>
					<li><a href="EProductServlet?type=proList3
					&epc_id=${proPage2.newsList[0].epc_id2}&pageIndex=${proPage2.currPageNo-1 }" >${proPage2.currPageNo-1 }</a></li>
					</c:if>
					<li class="current">${proPage2.currPageNo }</li>
                    <c:if test="${proPage2.currPageNo!=proPage2.totalPageCount }">
					<li><a href="EProductServlet?type=proList3
					&epc_id=${proPage2.newsList[0].epc_id2}&pageIndex=${proPage2.currPageNo+1 }" >${proPage2.currPageNo+1 }</a></li>
                    <li>...</li>
					</c:if>
					<c:if test="${proPage2.currPageNo==proPage2.totalPageCount }">
					</c:if>
					<li><a href="EProductServlet?type=proList3
					&epc_id=${proPage2.newsList[0].epc_id2}&pageIndex=${proPage2.totalPageCount }">尾页</a></li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
