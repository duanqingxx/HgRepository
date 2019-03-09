<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box">
			<h2>商品分类</h2>
			<dl>
			<c:forEach items="${LeftCate }" var="item">
			<c:if test="${item.epc_id2==0 }">
			<dt class="MyDt">${item.epc_name }</dt>
			</c:if>
			<c:if test="${item.epc_id2!=0 }">
			<dd><a href="EProductServlet?type=proList2&
			epc_id=${item.epc_id}">${item.epc_name }</a></dd>
			</c:if>
			</c:forEach>
			</dl>
</div>