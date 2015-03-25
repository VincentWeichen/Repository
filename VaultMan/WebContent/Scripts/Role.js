$(document).ready(function(){
	//alert($("#functionList").height());
	
});

var currentObj;
function msgDeleteList(delItems)
{
	var roleInfo = "," + currentObj.find("td").eq(2).text();
	$.each(delItems.split('▲'), function(key,val) {
		roleInfo = roleInfo.replace(val, "");
	});
	currentObj.find("td").eq(2).text(roleInfo.substring(1));
	
	$("#MessageBox p:first").html("删除成功！");
	$("#MsgOK").hide();
	setTimeout(function() {$.unblockUI();$("#MsgOK").show()}, 2000);
}
