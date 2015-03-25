package supton.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;

import supton.entity.Exclusionrole;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.User;
import supton.entity.pseudo.RoleInfomation;
import supton.entity.pseudo.UserInfomation;
import supton.service.IExclusionroleService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class ExclusionRoleAction {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IExclusionroleService exclusionroleService;
	
	@RequestMapping("/GetExclusionroleList")
	public void   GetExclusionroleList(HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 10;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo =Integer.valueOf(request.getParameter("pageNo"));
		}
		PageInfo pageInfo= exclusionroleService.PagedQuery(pageNo,pageSize);

		request.setAttribute("pageInfo",pageInfo);
		try {
			request.getRequestDispatcher("/WEB-INF/view/ExclusionRole.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/DeleteExclusionrole")
	public void   DeleteExclusionrole(int sn,HttpServletRequest request,HttpServletResponse response) {
		
		int userNum = exclusionroleService.deleteExclusionrole(sn);
		//删除关联表
		
		GetExclusionroleList(request,response);
	}
	
	@RequestMapping("/DeleteExclusionroleList")
	public void   DeleteExclusionroleList(HttpServletRequest request,HttpServletResponse response) {
		// 新建人员，测试
		String[] sns = request.getParameterValues("sn");
		String loginMessage = "";
		for(int i=0;i<sns.length;i++)
		{
			int userNum = exclusionroleService.deleteExclusionrole(Integer.parseInt(sns[i]));
			//删除关联的表信息
		}
		GetExclusionroleList(request,response);
	}
	
	@RequestMapping("/SaveExclusionrole")
	public void SaveExclusionrole(HttpServletRequest request,HttpServletResponse response,Exclusionrole exclusionrole) {
		//保存岗位
		exclusionroleService.saveExclusionrole(exclusionrole);

		GetExclusionroleList(request,response);
		
	}
	
	@ResponseBody
	@RequestMapping(value="AddExclusionrole",produces={"application/json;charset=UTF-8"})
	public String AddExclusionrole(HttpServletRequest request) {
		
		RoleInfomation roleInfomation = new RoleInfomation();
		List<Role> roleList = roleService.findAll();
		String roleAllName = "";
		for(Role role :roleList)
		{
			roleAllName += "," + role.getName();
		}
		roleInfomation.setRoleAllName(roleAllName);
		String roleJson = JsonTool.objectToJsonString(roleInfomation);
		return roleJson;
	}
	
	@ResponseBody
	@RequestMapping(value="EditExclusionrole",produces={"application/json;charset=UTF-8"})
	public String EditExclusionrole(HttpServletRequest request) {
		String sn = request.getParameter("sn");
		List<Exclusionrole> exclusionroleList = exclusionroleService.findBySn(Integer.parseInt(sn));
		Exclusionrole exclusionrole = exclusionroleList.stream().findFirst().orElse(null);
		//String roleJson = JsonTool.objectToJsonString(exclusionrole);
		
		RoleInfomation roleInfomation = new RoleInfomation();
		List<Role> roleList = roleService.findAll();
		String roleAllName = "";
		for(Role role :roleList)
		{
			roleAllName += "," + role.getName();
		}
		roleInfomation.setRoleAllName(roleAllName);
		roleInfomation.setExclusionroleName(exclusionrole.getExclusionrole());
		roleInfomation.setSn(Integer.parseInt(sn));
		String roleJson = JsonTool.objectToJsonString(roleInfomation);
		request.setAttribute("message","message");
		request.setAttribute("error","error");
		return roleJson;
	}
}