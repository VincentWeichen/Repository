$(document).ready(function(){
		
	//var checkedAllObj = $(".checkBoxAll");
	$("table.newdataList tr[class!='Info'][class!='title'][class!='Info-tr']").each(function(i, object) {
		var trClass = "";
		var thisObj = $(this);
		(i + 1)%2 == 0 ? trClass = "even" : trClass = "odd";
		
		thisObj.addClass(trClass).bind("mouseout", function() {
			thisObj.toggleClass("over");
			if (thisObj.find("input:first[type='checkbox']").attr("checked") == "checked")
				thisObj.addClass("checked");
			else
				thisObj.addClass(trClass);
		}).bind("mouseover", function() {
			thisObj.removeClass("even odd checked"); $(this).toggleClass("over");
		});
		
		thisObj.find("td:first input[type='checkbox']").click(function() {
			if ($(this).attr("checked") == "checked") {
				if ($(this).closest("table").find("tr[class!='title'] input[checked='checked']").length > 1)
					$(this).attr("checked", false);
			}
			
			/*
			var isChecked = false;
			if ($(this).attr("checked") == "checked") {
				thisObj.addClass("checked");
				if ($(this).closest("table").find("tr[class!='title'] input[checked!='checked']").length == 0)
					checkedAllObj.attr("checked", true);
			}
			else {
				thisObj.removeClass("checked");
				checkedAllObj.attr("checked", false);
			}
			*/
		});
	});
	
	/*
	checkedAllObj.bind("click", function() {
		var trs = $(this).closest("table").find("tr[class!='title']");
		var checkBoxs =trs.find("input[type='checkbox']");
		var isChecked = true;
		if (!$(this).attr("checked")) {
			isChecked = false;
			trs.removeClass("checked");
		}
		else {
			trs.addClass("checked");
		}
		checkBoxs.attr("checked", isChecked);
	});
	*/
	
	
	
});



var currentObj;
function msgDeleteList(delItems)
{
	//alert(delItems);
	var roleInfo = "," + currentObj.find("td").eq(4).text();
	$.each(delItems.split('▲'), function(key,val) {
		roleInfo = roleInfo.replace(val, "");
	});
	currentObj.find("td").eq(4).text(roleInfo.substring(1));
	
	$("#MessageBox p:first").html("删除成功！");
	$("#MsgOK").hide();
	setTimeout(function() {$.unblockUI();$("#MsgOK").show()}, 2000);
}
