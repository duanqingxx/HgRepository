
$(document).ready(function () {
	$(".orderStatus").each(function (){
		var status=$(this).attr("orderStatus");
		$(this).val(status);
	});
	$(".orderStatus").change(function () {
		var status=$(this).val();
		var orderId=$(this).attr("orderId");
		$.ajax({
			url:"EOrderDetailServlet?type=updEo",
			type:"post",
			data:"eo_id="+orderId+"&eo_status="+status,
			dataType:"text",
			success:function(result){
				if(result=="true")
				alert("订单状态修改成功");
			},
			error:function(){}
		});
	});
	
	});