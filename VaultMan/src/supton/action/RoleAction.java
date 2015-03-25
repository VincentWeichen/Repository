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

import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Rolefunction;
import supton.entity.User;
import supton.entity.pseudo.UserInfomation;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IRolefunctionService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class RoleAction {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private IRolefunctionService rolefunctionService;
	
	@RequestMapping("/GetRoleList")
	public void   GetRoleList(HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 10;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo =Integer.valueOf(request.getParameter("pageNo"));
		}
//		List<Role> roleList= organiztionUserService.getUserInfomationByOrCode(organizationCode);
		PageInfo pageInfo= roleService.PagedQuery(pageNo,pageSize);
//		request.setAttribute("userInfomation",userInfomation);
		request.setAttribute("pageInfo",pageInfo);
		try {
			request.getRequestDispatcher("/WEB-INF/view/Role.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/DeleteRole")
	public void   DeleteRole(String roleCode,HttpServletRequest request,HttpServletResponse response) {
		int userNum = roleService.deleteRole(roleCode);
		//删除关联表
		rolefunctionService.deleteByRolecode(roleCode);
		GetRoleList(request,response);
	}
	
	@RequestMapping("/DeleteRoleList")
	public void   DeleteRoleList(HttpServletRequest request,HttpServletResponse response) {
		// 新建人员，测试
		String[] codes = request.getParameterValues("roleCodeStr");
		String loginMessage = "";
		for(int i=0;i<codes.length;i++)
		{
			int userNum = roleService.deleteRole(codes[i]);
			//删除关联的表信息
			rolefunctionService.deleteByRolecode(codes[i]);
		}
		GetRoleList(request,response);
	}
	
	
	@RequestMapping("/SaveRole")
	public void SaveRole(HttpServletRequest request,HttpServletResponse response,Role role) {
		if(role.getId() == null)
		{
			role.setType(1);
		}
		//保存岗位
		roleService.saveRole(role);
		//这里需要添加角色信息，并且保存到数据库中
		String functionInfosStr = request.getParameter("functionInfo");
		String[] functionsInfos = functionInfosStr.split(";");
		rolefunctionService.deleteByRolecode(role.getCode());
		for(String item :functionsInfos)
		{
			String[] functionInfo = item.split(",");
			
			Rolefunction rolefunction = new Rolefunction();
			rolefunction.setRolecode(role.getCode());
			rolefunction.setFunctioncode(functionInfo[0]);
			rolefunction.setFunctionname(functionInfo[1]);
			rolefunction.setFunread(functionInfo[2]);
			rolefunction.setFunwrite(functionInfo[3]);
			rolefunctionService.save(rolefunction);
		}
		GetRoleList(request,response);
	}
	
	@ResponseBody
	@RequestMapping(value="EditRole",produces={"application/json;charset=UTF-8"})
	public String EditRole(HttpServletRequest request) {
		String roleCode = request.getParameter("roleCode");
		List<Role> roleList = roleService.findByCode(roleCode);
		Role role = roleList.stream().findFirst().orElse(null);
		//String roleJson = JsonTool.objectToJsonString(role);
		List<Rolefunction> rolefunctionList = rolefunctionService.findByRolecode(role.getCode());
		if(roleList != null && roleList.size()>0)
		{
			roleList.get(0).setRolefunctionList(rolefunctionList);
		}
		String roleJson = JsonTool.listTojsonString(roleList);
				
		return roleJson;
	}
}