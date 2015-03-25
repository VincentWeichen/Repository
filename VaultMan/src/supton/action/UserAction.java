package supton.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;

import com.sdicons.json.mapper.MapperException;

import supton.entity.Fingerprintuser;
import supton.entity.Lookupoption;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Rolefunction;
import supton.entity.Taskexport;
import supton.entity.User;
import supton.entity.pseudo.FingerprintuserForm;
import supton.entity.pseudo.UserInfomation;
import supton.service.IFingerprintUserService;
import supton.service.ILookupOptionService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IRoleUserService;
import supton.service.IRolefunctionService;
import supton.service.ISubgrouparrangeService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class UserAction {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IRoleUserService roleUserService;
	
	@Autowired
	private ILookupOptionService lookupOptionService;
	
	@Autowired
	private IFingerprintUserService fingerprintUserService;
	
	@Autowired
	private ISubgrouparrangeService subgrouparrangeService;
	
	@Autowired
	private IRolefunctionService rolefunctionService;

	@RequestMapping("/")
	public String getUser() {
		// 返回查询的数量
		System.out.println("old:" + userService.lookUser());
		// 保存一个新的对象
		//userService.saveUser(new User());
		System.out.println("new:" + userService.lookUser());
		return "Login";
	}
	
	@RequestMapping("/Index")
	public String Index() {
		// 返回查询的数量
		//System.out.println("old:" + userService.lookUser());
		// 保存一个新的对象
		//userService.saveUser(new User());
		//System.out.println("new:" + userService.lookUser());
		return "index";
	}

	@RequestMapping("/Register")
	public String registerUser(Model model,
			@RequestParam("code") String code,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		// 返回查询的数量
		System.out.println("old:" + userService.lookUser());
		// 新建人员，测试
		User user = new User();
		user.setCode(code);
		user.setName(username);
//		user.setUsername(username);
		user.setPassword(password);
		userService.saveUser(user);
//		try {
//			model.addAttribute(new String(username.getBytes("GBK"),"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		model.addAttribute("code",code);
		model.addAttribute(username);
		model.addAttribute("password", password);
		System.out.println("new:" + userService.lookUser());
		return "registerSuccess";
	}
	
	@RequestMapping("/getUserList")
	public String ABC(Model model)
	{
		//userService
		//model.addAttribute("aa", userService.getUserLists().size());
		//model.addAllAttributes(userService.getUserLists());
		model.addAttribute("UserList",userService.getUserLists());
		return "registerSuccess";
	}
	
	//@RequestMapping("/SubmitLogin")  @RequestParam("username") String username,
	//@RequestParam("password") String password)
	@ResponseBody
	@RequestMapping(value="SubmitLogin",produces={"application/json;charset=UTF-8"})
	//public String submitLogin(@RequestBody String params)
	public String submitLogin(@RequestParam("username") String username, @RequestParam("userpassword") String userpassword, HttpServletRequest request)
	{
		//String userName = JsonTool.getObjectFromJson(params, "username");
		//String userPassword = JsonTool.getObjectFromJson(params, "userpassword");
		
		User loginUserInfo = userService.validateLogin(username, userpassword);
		List<OrganizationUser> organizationUserList = organiztionUserService.findByUsercode(loginUserInfo.getCode());
		if(organizationUserList != null && organizationUserList.size() > 0)
		{
			request.getSession().setAttribute("organizationCode", organizationUserList.get(0).getOrganizationcode());
		}
		request.getSession().setAttribute("role", loginUserInfo.getRole());
		List<RoleUser> roleUserList = roleUserService.findByUsercode(loginUserInfo.getCode());
		String roles = "";
		if(roleUserList != null && roleUserList.size() > 0)
		{
			for(RoleUser roleUser : roleUserList)
			{
				roles += "'"+roleUser.getRolecode()+"',";
			}
			roles = roles.substring(0, roles.length()-1);
		}
		List<Rolefunction>  rolefunctionAllList = rolefunctionService.findByRoles(roles);
		List<String> functionCodes = rolefunctionService.findCodeByRoles(roles);
		List<Rolefunction> rolefunctionList = new ArrayList<Rolefunction>();
		for(String item : functionCodes)
		{
			//查询出拥有item的数据列表
			List<Rolefunction>  functionAllList = rolefunctionAllList.stream().filter(p->p.getFunctioncode().equals(item)).collect(Collectors.toList());
			List<Rolefunction>  functionWrites = functionAllList.stream().filter(p->p.getFunwrite().equals("1")).collect(Collectors.toList());
			List<Rolefunction>  functionReads = functionAllList.stream().filter(p->p.getFunread().equals("1")).collect(Collectors.toList());
			if(functionAllList != null && functionAllList.size()>0)
			{
				if(functionWrites != null && functionWrites.size()>0)
				{
					Rolefunction rolefunction = new Rolefunction();
					rolefunction = functionWrites.get(0);
					rolefunction.setFunread("1");
					rolefunctionList.add(rolefunction);
				}
				else if((functionWrites == null || functionWrites.size() == 0) && (functionReads != null && functionReads.size() > 0) )
				{
					rolefunctionList.add(functionReads.get(0));
				}
				else
				{
					rolefunctionList.add(functionAllList.get(0));
				}
			}
		}
		//处理重复功能列表数据
		String rolefunctionJson = JsonTool.listTojsonString(rolefunctionList);
		request.getSession().setAttribute("rolefunctionJson", rolefunctionJson);
		/*
		String returnString = "";
		if (loginUserInfo == null)
		{
			returnString = "{\"returnFlg\":" + false + ", \"returnObj\": " + loginUserInfo + "}";
		}
		else
		{
			returnString = "{\"returnFlg\":" + true + ", \"returnObj\": " + loginUserInfo + "}";
		}
		*/
		//userService
		//model.addAttribute("aa", userService.getUserLists().size());
		//model.addAllAttributes(userService.getUserLists());
		//model.addAttribute("UserList",userService.getUserLists());
		//String returnString = JsonTool.objectToJsonString("true");
		//String returnString = JsonTool.getObjectFromJson("True", "Aa");
		
		request.setAttribute("userName", loginUserInfo.getName());
		
		if (loginUserInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginFlg", "1");
			session.setAttribute("user", loginUserInfo);
		}
		
		String returnString = loginUserInfo != null ? "{\"returnVal\":\"True\"}" : "{\"returnVal\":\"False\"}";
		//String returnString = "{\"a\":\"True\"}";
		return returnString;
	}
	
	// @RequestMapping("/registerSuccess")
	// public ModelAndView registerSuccess(Map<String,Object> model) {
	// // model.addAttribute(username);
	// model.put("username", username);
	// return new ModelAndView("registerSuccess");
	// }
	
	@RequestMapping("/GetUserList")
	public void   GetUserList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 10;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo =Integer.valueOf(request.getParameter("pageNo"));
		}
		List<Role> roleList= roleService.findAll();
		List<Lookupoption> lookupoptionList = lookupOptionService.findByClass_(1);
		List<Organization> organiztionList= organiztionService.findAll();
		UserInfomation userInfomation= organiztionUserService.getUserInfomationByOrCode(organizationCode);
		PageInfo pageInfo= organiztionUserService.PagedQuery(organizationCode,pageNo,pageSize);
		request.setAttribute("organizationCode",organizationCode);
		request.setAttribute("lookupoptionList",lookupoptionList);
		request.setAttribute("userInfomation",userInfomation);
		request.setAttribute("organiztionList",organiztionList);
		request.setAttribute("roleList",roleList);
		request.setAttribute("pageInfo",pageInfo);
		try {
			request.getRequestDispatcher("/WEB-INF/view/UserInfomation.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/DeleteUser")
	public void   DeleteUser(String userCode,HttpServletRequest request,HttpServletResponse response) {
		// 新建人员，测试
		int userNum = userService.deleteUser(userCode);
		int organiztionUserNum = organiztionUserService.deleteOrganizationUser(userCode);
		
		String organizationCode = request.getParameter("organizationCode");
		GetUserList(organizationCode,request,response);
	}
	
	@RequestMapping("/DeleteUserList")
	public void   DeleteUserList(HttpServletRequest request,HttpServletResponse response) {
		String[] codes = request.getParameterValues("userCodeStr");
		String loginMessage = "";
		
		for(int i=0;i<codes.length;i++)
		{
			int userNum = userService.deleteUser(codes[i]);
			int organiztionUserNum = organiztionUserService.deleteOrganizationUser(codes[i]);
		}
		String organizationCode = request.getParameter("organizationCode");
		GetUserList(organizationCode,request,response);
	}
	
	@RequestMapping("/SaveUser")
	public void SaveUser(HttpServletRequest request,HttpServletResponse response,User user) {
		user.setType(1);
		OrganizationUser organizationUser = new OrganizationUser();
		if(user.getId() != null)
		{
			String aa = request.getParameter("userCode");
			List<OrganizationUser> organizationUserList = organiztionUserService.findByUsercode(request.getParameter("userCode"));
			organizationUser = organizationUserList.stream().findFirst().orElse(null);
		}
		organizationUser.setOrganizationcode(request.getParameter("organizationCode"));
		organizationUser.setUsercode(request.getParameter("code"));
		
		String roleNameString = request.getParameter("rolenames");
		String roleCodeString = request.getParameter("rolecodes");
		String userCodeString = user.getCode();
		
		userService.saveUser(user);
		organiztionUserService.save(organizationUser);
		
		//删除原用户岗位关系
		roleUserService.deleteByUserCode(userCodeString);
		//创建新的用户岗位关系
		
		if (roleNameString != null && !roleNameString.isEmpty()) {
			String[] roleNameArr = roleNameString.split(",");
			String[] roleCodeArr = roleCodeString.split(",");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		 	Date expired = new Date();
			try {
				expired = simpleDateFormat.parse("2099-12-31 23:59:59");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < roleNameArr.length; i++) {
				RoleUser roleUser = new RoleUser();
				roleUser.setRolecode(roleCodeArr[i]);
				roleUser.setUsercode(userCodeString);
				roleUser.setExpired(expired);
				roleUserService.save(roleUser);
			}
		}
		
		
		GetUserList(request.getParameter("organizationCode"),request,response);
		
	}
	
	@ResponseBody
	@RequestMapping(value="EditUser",produces={"application/json;charset=UTF-8"})
	//@RequestMapping("/EditUser")
	public String EditUser(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		List<User> userList = userService.findByCode(userCode);
		User user = userList.stream().findFirst().orElse(null);
		if(user != null)
		{
			List<Role> roleList = roleUserService.getRoleByUsercode(user.getCode());
			Role role = roleList.stream().findFirst().orElse(null);
			user.setRole(role);
		}
		String userJson = "";
		try {
			userJson = JsonTool.objectToJsonStr(user,true);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		List<OrganizationUser> organizationUserList = organiztionUserService.findByUsercode(userCode);
//		OrganizationUser organizationUser = organizationUserList.stream().findFirst().orElse(null);
//		request.setAttribute("sn",organizationUser.getSn());
		return userJson;
	}
	@ResponseBody
	@RequestMapping(value="CheckUserCode",produces={"application/json;charset=UTF-8"})
	public String CheckUserCode(HttpServletRequest request) {
		String userCode = request.getParameter("code");
		String id = request.getParameter("id");
		List<User> userList = userService.findByCode(userCode);
		User user = userList.stream().findFirst().orElse(null);
		if((id == null && userList != null && userList.size()>0) || (id != null && user !=null && !id.equals(user.getId().toString())))
		{
			return "0";
		}
		else
		{
			return "1";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="GetRoleUserByUserCode",produces={"application/json;charset=UTF-8"})
	public String GetRoleUserByUserCode(String usercode) {
		List<RoleUser> roleuserList = roleUserService.findByUsercode(usercode);
		return JsonTool.listTojsonString(roleuserList);
	}
	
	@RequestMapping("/GetFingerprintUserList")
	public void GetFingerprintUserList(String organizationCode, String tid, HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 10;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo =Integer.valueOf(request.getParameter("pageNo"));
		}
		
		
		
		List<Fingerprintuser> fingerprintuserList = fingerprintUserService.findAllByTId(tid);
		
		UserInfomation userInfomation= organiztionUserService.getUserInfomationByOrCode(organizationCode);
		
		request.setAttribute("fingerprintuserList",fingerprintuserList);
		request.setAttribute("userInfomation",userInfomation);
		request.setAttribute("organizationCode",organizationCode);
		if(tid.isEmpty())
			tid = "0";
		request.setAttribute("tid",tid);
		try {
			request.getRequestDispatcher("/WEB-INF/view/FingerprintUser.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/SaveFingerprint")
	public void SaveFingerprint(Fingerprintuser fingerprintuser, HttpServletRequest request, HttpServletResponse response) {
		String[] tusercodeArr = fingerprintuser.getTusercode().split(",");
		String usercodesysString = fingerprintuser.getUsercodesys();
		String usernamesysString = fingerprintuser.getUsernamesys();
		String[] usercodesysArr = usercodesysString.split(",");
		if (tusercodeArr.length > usercodesysArr.length) {
			usercodesysString += "#";
			usernamesysString += "#";
		}
		usercodesysArr = usercodesysString.split(",");
		String[] usernamesysArr = usernamesysString.split(",");
		String tid = request.getParameter("tid");
		for(int i = 0; i< tusercodeArr.length; i++) {
			Fingerprintuser newFingerprintuser = new Fingerprintuser();
			newFingerprintuser = fingerprintUserService.getFingerprintuserByTUserCode(tusercodeArr[i],tid);
			
			String usercodesys = usercodesysArr[i].replace("#", "");
			String usernamesys = usernamesysArr[i].replace("#", "");
			newFingerprintuser.setUsercodesys(usercodesys);
			newFingerprintuser.setUsernamesys(usernamesys);
			
			fingerprintUserService.save(newFingerprintuser);
		}
		
		GetFingerprintUserList(request.getParameter("organizationCode"),request.getParameter("tid"), request,response);
	}
	
	@ResponseBody
	@RequestMapping(value="Synchronization",produces={"application/json;charset=UTF-8"})
	public String Synchronization(String tid) {
		//更新Taskexport（指纹机用户同步，type=5）
		Taskexport taskexport = subgrouparrangeService.findTaskExport(tid, 5);
		if (taskexport == null) {
			taskexport = new Taskexport();
			taskexport.setTid(tid);
			taskexport.setVersion("1");
			taskexport.setType(5);
		}
		taskexport.setTimes(0);
		taskexport.setStatus(1);
		
		subgrouparrangeService.SetTaskExport(taskexport);
		
		return "{\"returnVal\":\"True\"}";
	}
}