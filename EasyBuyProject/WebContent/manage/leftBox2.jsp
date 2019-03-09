<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="box">
	<dl>
		<dt>用户管理</dt>
		<dd><a href="EUserServlet?type=userList">用户管理</a></dd>
	    <dt>商品信息</dt>
		<dd><em><a href="manage/productClass-add.jsp">新增</a></em>
		<a href="EProCateServlet?type=procateList">分类管理</a></dd>
		<dd><em><a href="manage/product-add.jsp">新增</a></em>
		<a href="EProductServlet?type=proList">商品管理</a></dd>
		<dt>订单管理</dt>
		<dd><a href="EOrderDetailServlet?type=eodList">订单管理</a></dd>
		<dt>留言管理</dt>
		<dd><a href="ECommServlet?type=commList2">留言管理</a></dd>
		<dt>新闻管理</dt>
		<dd><em><a href="manage/news-add.jsp">新增</a></em>
		<a href="ENewsServlet?type=newsList">新闻管理</a></dd>
	</dl>
</div>