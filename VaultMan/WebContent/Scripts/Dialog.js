(function ($) {
    $.fn.extend({
        Dialog: function (options) {
            var op = $.extend({ okClick: function () { }, cancelClick: function () { }, funcName: "Alert", message: "", titleText: "提示", autoClose: 0, messageType: "alert", confirm: $.extend({ viewText: "确定", func: function() {}, isDisplay: true }, options), cancel: $.extend({ viewText: "取消", func: null, isDisplay: true }, options), close: $.extend({ viewText: "关闭", func: null, isDisplay: true }, options) }, options);
            var $this = this;
            this.funcName = op.funcName;
            this.message = op.message;
            this.titleText = op.titleText;
            this.autoClose = op.autoClose;
            this.messageType = op.messageType;
            this.Timer;
            this.confirm = op.confirm;
            this.cancel = op.cancel;
            this.close = op.close;
            this.parentFunc = "Other";
            //alert(this.confirm.isDisplay)
            $this.Open($this, event || window.event);
        },
        Open: function ($this, oEvent) {
            //alert("Open：" + $this.funcName);
        	if ($this.funcName != "Close") {
	            var popupShadeObj = $("div.PopupShade");
	            //alert(popupShadeObj.length)
            
	            if (popupShadeObj.length > 0 && !popupShadeObj.is(":visible")) {
	                $(".PopupShade").show();
	            }
	            else {
	                var popupShade = $("<div class=\"PopupShade\"><\/div>");
	                popupShade.width($(window).width()).height($(window).height());
	                popupShade.appendTo($(document.body));
	            }
            }

            switch ($this.funcName) {
                case "Alert":
                    $this.Alert($this, oEvent);
                    break;
                case "Confirm":
                    $this.Confirm($this, oEvent);
                    break;
                case "Users":
                    $this.Users($this, oEvent);
                    break;
                case "Role":
                    $this.Role($this, oEvent);
                    break;
                case "Product":
                    $this.Alert($this, oEvent);
                    break;
                case "Supplier":
                    $this.Alert($this, oEvent);
                    break;
                case "Loading":
                	$this.Loading($this, oEvent);
                	break;
                case "Close":
                	$this.Close($this, oEvent);
                	break;
            }
        },
        Loading: function($this, oEvent) {
        	clearTimeout($this.Timer);
        	
        	var loadingHtml = "<div class=\"PopupBox\">";
        	loadingHtml += "<div class=\"PopupTitle\"><h1>" + $this.titleText + "<\/h1><\/div>";
        	loadingHtml += "<div class=\"PopupContent\" style=\"padding:15px 30px 15px 15px;\"><img src=\"/VaultMan/Images/Basic/Loading.gif\" alt=\"Loading\" align=\"middle\" style=\"margin-right:15px;\" \/>正在处理请稍候...<\/div>";
        	loadingHtml += "<\/div>";
            
            var loading = $(loadingHtml);
            loading.Drag({ Handle: loading.find(".PopupTitle") })
            loading.appendTo($(document.body));
            $(".PopupShade").show();
            loading.css({ left: ($(window).width() - loading.width()) / 2 + "px", top: ($(window).height() - loading.height()) / 2 + "px", display: "block" });
        },
        Close: function ($this, oEvent) {
        	//alert("b")
        	if ($this != null) {
        		clearTimeout($this.Timer);
        	}
        	
        	//alert($this.parentFunc);
        	
        	if ($this.parentFunc == "Alert" && $this.messageType == "success") {
        		$(".PopupBox").hide();
	            $(".PopupBox").remove();
	            $(".PopupShade").hide();
	            $(".PopupShade").remove();
        	}
        	else {
	        	//alert($(".PopupBox").length)
	            $(".PopupBox:last").hide();
	            $(".PopupBox:last").remove();
	            $(".PopupShade:last").hide();
	            if ($(".PopupShade").length > 1)
	            	$(".PopupShade:last").remove();
        	}
            return false;
        },
        Alert: function ($this, oEvent) {
            //alert("Alert");
        	/*
            var alertObj = $(".PopupBox");
            if (alertObj.length > 0) {
            	alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px", display: "block" });
            	alertObj.find(".PopupContent").html($this.message);
                alertObj.show();
            }
            else {
            */
                var alertHtml = "<div class=\"PopupBox\">";
                alertHtml += "<div class=\"PopupTitle\"><h1>" + $this.titleText + "<\/h1><a href=\"#\" id=\"dialogTitleCloseButton\"><\/a><\/div>";
                alertHtml += "<div class=\"PopupContent " + $this.messageType + "\">" + $this.message + "<\/div>";
                alertHtml += "<div class=\"PopupButton\"><a href=\"#\" id=\"DialogCloseButton\" class=\"btn btnGrey\">关闭<\/a><\/div>";
                alertHtml += "<\/div>";
                var alertObj = $(alertHtml);
                alertObj.Drag({ Handle: alertObj.find(".PopupTitle") })
                alertObj.appendTo($(document.body));
                alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px", display: "block" });
                var _closeBtn = $("#DialogCloseButton, #dialogTitleCloseButton").bind("click", function () { $this.Close($this, oEvent) });
            /*}*/

            if ($this.autoClose > 0) {
            	$this.parentFunc = "Alert";
                $this.Timer = setTimeout(function () { $this.Close($this, oEvent) }, $this.autoClose * 1000);
            }
        },
        Confirm: function ($this, oEvent) {
            //alert("Confirm");
        	/*
        	var alertObj = $(".PopupBox");
            if (alertObj.length > 0) {
            	alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px", display: "block" });
            	alertObj.find(".PopupContent").html($this.message);
                alertObj.show();
            }
            else {
            */
                var alertHtml = "<div class=\"PopupBox\">";
                alertHtml += "<div class=\"PopupTitle\"><h1>" + $this.titleText + "<\/h1><a href=\"#\" id=\"dialogTitleCloseButton\"><\/a><\/div>";
                alertHtml += "<div class=\"PopupContent " + $this.messageType + "\">" + $this.message + "<\/div>";
                alertHtml += "<div class=\"PopupButton\"><a href=\"#\" id=\"DialogCloseButton\" class=\"btn btnGrey\">关闭<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"DialogCancelButton\">取消<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"DialogConfirmButton\">确定<\/a><\/div>";
                alertHtml += "<\/div>";
                var alertObj = $(alertHtml);
                
                alertObj.Drag({ Handle: alertObj.find(".PopupTitle") })
                alertObj.appendTo($(document.body));
                
                //alert($this.confirm.isDisplay)
                $this.close.isDisplay ? $("#DialogCloseButton").css("display", "block") : $("#DialogCloseButton").css("display", "none");
                $this.confirm.isDisplay ? $("#DialogConfirmButton").css("display", "block") : $("#DialogConfirmButton").css("display", "none");
                $this.cancel.isDisplay ? $("#DialogCancelButton").css("display", "block") : $("#DialogCancelButton").css("display", "none");
                
                alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px", display: "block" });
                var _closeBtn = $("#DialogCloseButton, #dialogTitleCloseButton").bind("click", function () { $this.Close($this, oEvent) })
                //alert( $this.close.isDisplay)
                
                /*
                var action = function(myaction, name){
                	eval(myaction + "(name)");
                } 
                */
                
                var confirmFunc = $this.confirm.func;
                
                $("#DialogConfirmButton").bind("click", function() {
                	//alert($this.confirm.funcName)
                	$this.Close($this, oEvent);
                	$this.Loading($this, oEvent);
                	//alert($this.confirm.func)
                	eval(confirmFunc);
                	//action($this.confirm.funcName, $this.confirm.funcParam);
                })
                
                $("#DialogCancelButton").bind("click", function() {
                	if ($this.cancel.func != null)
                		eval($this.cancel.func);
                	else
                		$this.Close($this, oEvent);
            	})
                	
                
            /*}*/
        },
        Users: function ($this, oEvent) {
            //alert("ChooseUser");
        	userHtml = $this.message;

            var alertHtml = "<div class=\"PopupBox\">";
            alertHtml += "<div class=\"PopupTitle\"><h1>" + $this.titleText + "<\/h1><a href=\"#\" id=\"dialogTitleCloseButton\"><\/a><\/div>";
            alertHtml += "<div class=\"PopupContent\">" + userHtml + "<\/div>";
            alertHtml += "<div class=\"PopupButton\"><a href=\"#\" id=\"DialogCloseButton\" class=\"btn btnGrey\">关闭<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"DialogCancelButton\">取消<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"DialogConfirmButton\">确定<\/a><\/div>";
            alertHtml += "<\/div>";

            //alert(alertHtml)
            var alertObj = $(alertHtml);
            
            
            alertObj.Drag({ Handle: alertObj.find(".PopupTitle") })


            //var userObj = $(userHtml);
            //userObj.Drag({ Handle:userObj.find(".PopupTitle") })
            //userObj.appendTo(alertObj.find(".PopupContent"));
            alertObj.appendTo($(document.body));
            $(".PopupBox").show();
            
            //alert($("#DialogCloseButton").length)
            
            $this.close.isDisplay ? $("#DialogCloseButton:last").css("display", "block") : $("#DialogCloseButton:last").css("display", "none");
            $this.confirm.isDisplay ? $("#DialogConfirmButton").css("display", "block") : $("#DialogConfirmButton").css("display", "none");
            $this.cancel.isDisplay ? $("#DialogCancelButton").css("display", "block") : $("#DialogCancelButton").css("display", "none");
            
            alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px" });
            
            //alert($("#DialogCloseButton")[])
            
            var _closeBtn = $("#DialogCloseButton, #dialogTitleCloseButton").bind("click", function () { $this.Close($this, oEvent) })
            
            var userConfirmFunc = $this.confirm.func;
            
            //alert($this.confirm.func)
            $("#DialogConfirmButton").bind("click", function() {
            	//alert($this.confirm.funcName)
            	//$this.Close($this, oEvent);
            	//$this.Loading($this, oEvent);
            	//alert($this.confirm.func)
            	//alert($this.confirm.func)
            	eval(userConfirmFunc);
            	//action($this.confirm.funcName, $this.confirm.funcParam);
            })
            
            $("#DialogCancelButton").bind("click", function() {
            	if ($this.cancel.func != null)
            		eval($this.cancel.func);
            	else
            		$this.Close($this, oEvent);
        	})
        },
        Role: function($this, oEvent) {
        	userHtml = $this.message;

            var alertHtml = "<div class=\"PopupBox\">";
            alertHtml += "<div class=\"PopupTitle\"><h1>" + $this.titleText + "<\/h1><a href=\"#\" id=\"dialogTitleCloseButton\"><\/a><\/div>";
            alertHtml += "<div class=\"PopupContent\">" + userHtml + "<\/div>";
            alertHtml += "<div class=\"PopupButton\"><a href=\"#\" id=\"RoleDialogCloseButton\" class=\"btn btnGrey\">关闭<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"RoleDialogCancelButton\">取消<\/a><a href=\"#\" class=\"btn btnGrey\" id=\"RoleDialogConfirmButton\">确定<\/a><\/div>";
            alertHtml += "<\/div>";

            //alert(alertHtml)
            var alertObj = $(alertHtml);
            
            
            alertObj.Drag({ Handle: alertObj.find(".PopupTitle") })


            //var userObj = $(userHtml);
            //userObj.Drag({ Handle:userObj.find(".PopupTitle") })
            //userObj.appendTo(alertObj.find(".PopupContent"));
            alertObj.appendTo($(document.body));
            $(".PopupBox:last").show();
            
            $this.close.isDisplay ? $("#RoleDialogCloseButton").css("display", "block") : $("#RoleDialogCloseButton").css("display", "none");
            $this.confirm.isDisplay ? $("#RoleDialogConfirmButton").css("display", "block") : $("#RoleDialogConfirmButton").css("display", "none");
            $this.cancel.isDisplay ? $("#RoleDialogCancelButton").css("display", "block") : $("#RoleDialogCancelButton").css("display", "none");
            
            //alertObj.css({ left: "0px", top: "0px" });
            alertObj.css({ left: ($(window).width() - alertObj.width()) / 2 + "px", top: ($(window).height() - alertObj.height()) / 2 + "px" });
            
            //alert($("#DialogCloseButton")[])
            
            var roleConfirmFunc = $this.confirm.func
            
            var _closeBtn = $("#RoleDialogCloseButton, #dialogTitleCloseButton").bind("click", function () { $this.Close($this, oEvent) })
            $("#RoleDialogConfirmButton").bind("click", function() {
            	//alert($this.confirm.funcName)
            	//$this.Close($this, oEvent);
            	//$this.Loading($this, oEvent);
            	//alert($this.confirm.func)
            	eval(roleConfirmFunc);
            	//action($this.confirm.funcName, $this.confirm.funcParam);
            })
            
            $("#RoleDialogCancelButton").bind("click", function() {
            	if ($this.cancel.func != null)
            		eval($this.cancel.func);
            	else
            		$this.Close($this, oEvent);
        	})
        },
        Product: function () {
            alert("Product");
        },
        Supplier: function () {
            alert("Supplier");
        }
    });
})(jQuery);
