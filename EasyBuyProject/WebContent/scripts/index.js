function roll() {
            $("#rollul").animate({"margin-top":"-22px"},2000,function () {
                var content=$(this).children(":first").html();
                $(this).children(":first").remove();
                $(this).css("margin-top","0px");
                var $li=$("<li>"+content+"</li>");
                $(this).append($li);
            });
            setTimeout("roll()",2000);
        }
$(document).ready(function () {

    $.getJSON("ENewsServlet?type=hotNews","",
		function(json){
		$ul=$("#rollul");
		var content="";
		for(var i=0;i<json.length;i++){
			content+="<li><a href=\"ENewsServlet?type=detailNews&newsId="
				+json[i].en_id+"\"  target=\"_self\">"+json[i].en_title+"</a></li>";
		}
		$ul.html(content);
		}); 
	    
	$.getJSON("EProductServlet?type=hotPro","",
		function(json){
		$ul=$("#hPI");
		var content="";
		for(var i=0;i<json.length;i++){
			content+="<li><dl><dt><a href=\"EProductServlet?type=detailPro&ep_id="+json[i].ep_id+"&epc_id="+json[i].epc_id+"\"  target=\"_self\">";
			content+="<img src=\"images/product/"+json[i].ep_file_name+".jpg\" /></a></dt>";
			content+="<dd class=\"title\"><a href=\"EProductServlet?type=detailPro&ep_id="+json[i].ep_id+"&epc_id="+json[i].epc_id+"\" target=\"_self\">";
			content+=json[i].ep_name+"</a></dd>";
			content+="<dd class=\"price\">￥"+json[i].ep_price+"</dd></dl></li>";
		}
		$ul.html(content);
		});	
	roll();
	});