
$(document).ready(function () {
	$.getJSON("../EProCateServlet?type=fatherCate","",
	function(json){
		var $select=$("select[name='parentId']");
		if($select.attr("pid")!=null){
		var option="";
		for(var i=0;i<json.length;i++){
			option+="<option value=\""+json[i].epc_id+"\">"+json[i].epc_name;
			option+="</option>";
		}
		$select.html(option);
		$select.val($select.attr("pid"));
		}
	});
	
	$.getJSON("../EProCateServlet?type=fatherCate","",
			function(json){
				var $select=$("select[name='parentId2']");
				var option="<option value=\"0\" selected=\"selected\">根栏目";
				for(var i=0;i<json.length;i++){
					option+="<option value=\""+json[i].epc_id+"\">"+json[i].epc_name;
					option+="</option>";
				}
				option+="</option>";
				$select.html(option);
			});
	
});