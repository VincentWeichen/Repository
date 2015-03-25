$(document).ready(function () {
    var accountObject = $("#LoginAccount");
    var passwordObject = $("#LoginPassword");
    var submitObject = $("#LoginSubmit");
    accountObject.css("color", "#999").val("请输入登录帐号...");

    accountObject.focus(function () {
        var accountVal = accountObject.val();
        if (accountVal != "" && accountVal == "请输入登录帐号...") {
            accountObject.css("color", "#FFF");
            accountObject.attr("value", "");
        }
    }).blur(function () {
        if (accountObject.val() == "") {
            accountObject.css("color", "#999").val("请输入登录帐号...");
        }
    }).bind("keypress", function (event) {
        if (event.keyCode == "13") {
            passwordObject.focus();
        }
    });

    passwordObject.bind("keypress", function (event) {
        if (event.keyCode == "13") {
            submitObject.focus();
            LoginValidate();
        }
    });

    submitObject.bind("click", function () {
        LoginValidate();
        return false;
    });

    function LoginValidate() {
        var accountValue = $("#LoginAccount").val();
        var passwordValue = $("#LoginPassword").val();
        if (accountValue == "" || accountValue == "请输入登录帐号...") {
            $.fn.Dialog({ funcName: "Alert", message: "请输入帐号", autoClose: 3 });
        }
        else if (passwordValue == "") {
            $.fn.Dialog({ funcName: "Alert", message: "请输入密码！", autoClose: 3 });
        }
        else {
        	$.post(common.getProjectRootPath() + "/SubmitLogin.action", { username: accountValue, userpassword: passwordValue }, function (result) {
        		//alert(result)
				$.each(result, function(i, jsonItem) {
					if (jsonItem == "True")
					{
						//alert("OK")
						window.location.href = common.getProjectRootPath() + "/MainPage";
					}
					else
					{
						//alert("用户名或密码错误！");
						$.fn.Dialog({ funcName: "Alert", message: "用户名或密码错误！", autoClose: 3 });
					}
        		});
	        }, "json");
        }
    }
});