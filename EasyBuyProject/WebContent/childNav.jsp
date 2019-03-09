<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
		    <c:forEach items="${LeftCate }" var="item" varStatus="status">
		    <c:if test="${item.epc_id2!=0&&status.index<20}">
		    <li><a href="EProductServlet?type=proList2&
			epc_id=${item.epc_id}">${item.epc_name }</a></li>
			</c:if>
		    </c:forEach>
		    <li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>