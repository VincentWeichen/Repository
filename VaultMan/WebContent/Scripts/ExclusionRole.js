$(document).ready(function(){
	//alert("a")
	/*
	$("#dataList .edit").each(function(i, object) {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$(object).bind("click", function() {
			var allRole = ",应急密码管理员,排班管理员,管制管理员";
			$("#AllRole ul li").remove();
			$("#ExclusionRole ul li").remove();
			
			currentObj = $(this).parents("tr");
			var GroupCol = $(this).parents("tr").find("td");
			var roleItems = GroupCol.eq(3).text().split(',');
			$.each(roleItems, function(j, obj) {
				allRole = allRole.replace("," + obj, "");
			});
			var allRoleArr = (allRole.substring(1)).split(',');
			
			$.each(allRoleArr, function(j, obj) {
				$("#AllRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
			});
			$.each(roleItems, function(j, obj) {
				$("#ExclusionRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
			});
			
			bindArgs();
			
			
			$.blockUI({
				message: $("#UserInfo"),
				css: { border:'none', width: '760', top : wTop, left : wLeft, textAlign : 'left' },
				growlCSS: { width: '760px'}
			});
			
		});
	});
	
	bindArgs();
	*/
	
	$("#moveToRight").bind("click", function() {moveItemTo("toRight");})
	$("#moveToLeft").bind("click", function() {moveItemTo("toLeft");})
		
});

function moveItemTo(flg)
{
	var currentObj = $("#AllRole");
	var target = $()
	if (flg == "toRight")
	{
		currentObj = $("#AllRole ul");
		target = $("#ExclusionRole ul");
	}
	else
	{
		currentObj = $("#ExclusionRole ul");
		target = $("#AllRole ul");
	}
	
	currentObj.find("li a[class='checked']").each(function(i, obj) {
		$(obj).removeClass("checked");
		target.append("<li>" + $(obj).parent().html() + "<\/li>");
		target.find("li a:last").bind("click", function() {
			$(this).toggleClass("checked");
		}).bind("dblclick", function() {
			var flg;
			$(this).closest("div.ExclusionGroup").attr("id") == "AllRole" ? flg = "toRight" : flg = "toLeft";
			moveItemTo(flg);
		})
		$(obj).parent().remove();
	});
}

function bindArgs()
{
	var lists = $(".ExclusionGroup li a");
	lists.each(function(i, obj) {
		var objItem = $(obj);
		objItem.bind("click", function() {
			$(this).toggleClass("checked");
			//$(this).attr("class") == "checked" ? $(this).removeClass("checked") : $(this).addClass("checked");
		}).bind("dblclick", function() {
			var flg;
			objItem.closest("div.ExclusionGroup").attr("id") == "AllRole" ? flg = "toRight" : flg = "toLeft";
			moveItemTo(flg);
		})
	});
}

