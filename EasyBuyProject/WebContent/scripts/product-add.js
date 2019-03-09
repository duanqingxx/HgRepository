
$(document).ready(function () {
	$.getJSON("../EProCateServlet?type=findeAllCate","",
			function(json){
				var $select=$("select[name='parentId']");
				var option="";
				for(var i=0;i<json.length;i++){
					if(json[i].epc_id2==0){
					option+="<option value=\""+json[i].epc_id+"\">"+json[i].epc_name;
					option+="</option>";
					}
					else if((i+1)!=json.length&&json[i].epc_id2!=json[i+1].epc_id2){
						option+="<option value=\""+json[i].epc_id+"\">"+"└ "+json[i].epc_name;
						option+="</option>";
					}else if((i+1)==json.length){
						option+="<option value=\""+json[i].epc_id+"\">"+"└ "+json[i].epc_name;
						option+="</option>";
					}else{
				
						option+="<option value=\""+json[i].epc_id+"\">"+"├ "+json[i].epc_name;
						option+="</option>";
					}
		
				}
				$select.html(option);
			});
	$.getJSON("../EProCateServlet?type=findeAllCate","",
			function(json){
				var $select=$("select[name='parentId2']");
				var epc_id=$select.attr("epc_id");
				var option="";
				for(var i=0;i<json.length;i++){
					if(json[i].epc_id2==0){
					option+="<option value=\""+json[i].epc_id+"\">"+json[i].epc_name;
					option+="</option>";
					}
					else if((i+1)!=json.length&&json[i].epc_id2!=json[i+1].epc_id2){
						option+="<option value=\""+json[i].epc_id+"\">"+"└ "+json[i].epc_name;
						option+="</option>";
					}else if((i+1)==json.length){
						option+="<option value=\""+json[i].epc_id+"\">"+"└ "+json[i].epc_name;
						option+="</option>";
					}else{
				
						option+="<option value=\""+json[i].epc_id+"\">"+"├ "+json[i].epc_name;
						option+="</option>";
					}
		
				}
				$select.html(option);
				$select.val(epc_id);
			});
});