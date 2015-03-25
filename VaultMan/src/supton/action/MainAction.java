package supton.action;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import supton.entity.Organization;
import supton.entity.User;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class MainAction {

	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@RequestMapping("/MainPage")
	public String ManagerPage() {
		// 返回查询的数量
		//System.out.println("old:" + userService.lookUser());
		// 保存一个新的对象
		//userService.saveUser(new User());
		//System.out.println("new:" + userService.lookUser());
		return "MainPage";
	}
	
	@RequestMapping("/Role")
	public String Role() {
		
		return "Role";
	}

}