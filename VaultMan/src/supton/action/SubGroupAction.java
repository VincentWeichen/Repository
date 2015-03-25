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

import com.sdicons.json.mapper.MapperException;

import supton.dao.IOrganizationDao;
import supton.entity.Fingerprintuser;
import supton.entity.Lookupoption;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Subgroup;
import supton.entity.Taskexport;
import supton.entity.User;
import supton.entity.pseudo.GroupUserInfomation;
import supton.entity.pseudo.UserInfomation;
import supton.service.IFingerprintUserService;
import supton.service.ILookupOptionService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IRoleUserService;
import supton.service.ISubgroupService;
import supton.service.ISubgrouparrangeService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class SubGroupAction {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private ISubgroupService subgroupService;
	
	@Autowired
	private IFingerprintUserService fingerprintUserService;
	
	@Autowired
	private ILookupOptionService lookupOptionService;
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private ISubgrouparrangeService subgrouparrangeService;
	
	@RequestMapping("/GetGroupList")
	public void   GetGroupList(String organizationCode,String tid,HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 40;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo =Integer.valueOf(request.getParameter("pageNo"));
		}
		//序列
		String[] numGroup={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};
		UserInfomation userInfomation= organiztionUserService.getUserInfomationByOrCode(organizationCode);
		PageInfo pageInfo= subgroupService.PagedQuery(organizationCode,pageNo,pageSize);
		List<Fingerprintuser>  fingerprintuserList = fingerprintUserService.findByTID(tid);
		Organization organization = organiztionService.getOrganizationByCode(organizationCode);
		request.setAttribute("userNum",organization.getTusernum());
		request.setAttribute("organizationCode",organizationCode);
		if(tid.isEmpty())
			tid = "0";
		request.setAttribute("tid",tid);
		request.setAttribute("userInfomation",userInfomation);
		request.setAttribute("fingerprintuserList",fingerprintuserList);
		request.setAttribute("numGroup",numGroup);
		request.setAttribute("pageInfo",pageInfo);
		try {
			request.getRequestDispatcher("/WEB-INF/view/Group.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/DeleteGroup")
	public void   DeleteGroup(String groupCode,HttpServletRequest request,HttpServletResponse response) {
		String corganizationCode = request.getParameter("corganizationCode");
		String tid = request.getParameter("tid");
		int groupNum = subgroupService.deleteGroup(groupCode,corganizationCode);
		GetGroupList(corganizationCode,tid,request,response);
	}
	
	@RequestMapping("/DeleteGroupList")
	public void   DeleteGroupList(HttpServletRequest request,HttpServletResponse response) {
		String[] groupcodes = request.getParameterValues("groupCode");
		String loginMessage = "";
		String corganizationCode = request.getParameter("corganizationCode");
		String tid = request.getParameter("tid");
		for(int i=0;i<groupcodes.length;i++)
		{
			int groupNum = subgroupService.deleteGroup(groupcodes[i],corganizationCode);
		}
		
		GetGroupList(corganizationCode,tid,request,response);
	}
	
	@RequestMapping("/SaveGroup")
	public void SaveGroup(HttpServletRequest request,HttpServletResponse response) {
		String organizationcode = request.getParameter("organizationcode");
		String tid = request.getParameter("tid");
		List<Fingerprintuser> fingerprintuserList = fingerprintUserService.findByTID(tid);
		//保存之前删除原有数据
		subgroupService.deleteByOrganizationcode(organizationcode);
		
		for(int i=1;i<=20;i++)
		{
			Subgroup group = new Subgroup();
			group.setVersion("1");
			String id = request.getParameter(i+"_id");
			String code = request.getParameter(i+"_code");
			String name = request.getParameter(i+"_name");
			String type = request.getParameter(i+"_type");
			
//			String groupauthorize = request.getParameter(i+"_groupauthorize");
			String tusercode1 = request.getParameter(i+"_user1");
			String tusercode2 = request.getParameter(i+"_user2");
			String tusercode3 = request.getParameter(i+"_user3");
			String tusercode4 = request.getParameter(i+"_user4");
			String tusercode5 = request.getParameter(i+"_user5");
			if( !id.equals(""))
				group.setId(Integer.parseInt(id));
			group.setCode(code);
			group.setName(name);
			group.setType(type);
			group.setTid(tid);
			group.setVersion("1");
			group.setOrganizationcode(organizationcode);
//			group.setGroupauthorize(groupauthorize);
			String usernames = "";
			if(!tusercode1.equals(""))
			{
				usernames += tusercode1.substring(
						tusercode1.indexOf(";") + 1, tusercode1.length())+ ",";
				String tucode1 = tusercode1.substring(0, tusercode1.indexOf(";"));
				String userName1 = tusercode1.substring(tusercode1.indexOf(";") + 1, tusercode1.length());
				Fingerprintuser fingerprintuser1 = fingerprintuserList.stream().filter(p -> p.getTusercode().equals(tucode1)).findFirst().orElse(null);
				String usercodeSys1 = fingerprintuser1.getUsercodesys();
				group.setUsercodesys1(usercodeSys1);
				group.setUsernamesys1(userName1);
				group.setTusercode1(tucode1);
			}else{
				continue;
			}
			if(!tusercode2.equals(""))
			{
				usernames += tusercode2.substring(
						tusercode2.indexOf(";") + 1, tusercode2.length())+ ",";
				String tucode2 = tusercode2.substring(0, tusercode2.indexOf(";"));
				String userName2 = tusercode2.substring(tusercode2.indexOf(";") + 1, tusercode2.length());
				Fingerprintuser fingerprintuser2 = fingerprintuserList.stream().filter(p -> p.getTusercode().equals(tucode2)).findFirst().orElse(null);
				String usercodeSys2 = fingerprintuser2.getUsercodesys();
				group.setUsercodesys2(usercodeSys2);
				group.setUsernamesys2(userName2);
				group.setTusercode2(tucode2);
			}
			if(!tusercode3.equals(""))
			{
				usernames += tusercode3.substring(
						tusercode3.indexOf(";") + 1, tusercode3.length())+ ",";
				String tucode3 = tusercode3.substring(0, tusercode3.indexOf(";"));
				String userName3 = tusercode3.substring(tusercode3.indexOf(";") + 1, tusercode3.length());
				Fingerprintuser fingerprintuser3 = fingerprintuserList.stream().filter(p -> p.getTusercode().equals(tucode3)).findFirst().orElse(null);
				String usercodeSys3 = fingerprintuser3.getUsercodesys();
				group.setUsercodesys3(usercodeSys3);
				group.setUsernamesys3(userName3);
				group.setTusercode3(tucode3);
			}
			if(!tusercode4.equals(""))
			{
				usernames += tusercode4.substring(
						tusercode4.indexOf(";") + 1, tusercode4.length())+ ",";
				String tucode4 = tusercode4.substring(0, tusercode4.indexOf(";"));
				String userName4 = tusercode4.substring(tusercode4.indexOf(";") + 1, tusercode4.length());
				Fingerprintuser fingerprintuser4 = fingerprintuserList.stream().filter(p -> p.getTusercode().equals(tucode4)).findFirst().orElse(null);
				String usercodeSys4 = fingerprintuser4.getUsercodesys();
				group.setUsercodesys4(usercodeSys4);
				group.setUsernamesys4(userName4);
				group.setTusercode4(tucode4);
			}
			if(!tusercode5.equals(""))
			{
				usernames += tusercode5.substring(
						tusercode5.indexOf(";") + 1, tusercode5.length())+ ",";
				String tucode5 = tusercode5.substring(0, tusercode5.indexOf(";"));
				String userName5 = tusercode5.substring(tusercode5.indexOf(";") + 1, tusercode5.length());
				Fingerprintuser fingerprintuser5 = fingerprintuserList.stream().filter(p -> p.getTusercode().equals(tucode5)).findFirst().orElse(null);
				String usercodeSys5 = fingerprintuser5.getUsercodesys();
				group.setUsercodesys5(usercodeSys5);
				group.setUsernamesys5(userName5);
				group.setTusercode5(tucode5);
			}
			// 用于一般情况班组人员显示
			if (!usernames.equals("")) {
				group.setUsernames(usernames.substring(0,usernames.lastIndexOf(",")));
			}
			//保存组信息
			subgroupService.save(group);
		}
		
		//更新Taskexport（分组保存，type=1）
		Taskexport taskexport = subgrouparrangeService.findTaskExport(tid, 1);
		if (taskexport == null) {
			taskexport = new Taskexport();
			taskexport.setTid(tid);
			taskexport.setVersion("1");
			taskexport.setType(1);
		}
		taskexport.setTimes(0);
		taskexport.setStatus(1);
		
		subgrouparrangeService.SetTaskExport(taskexport);
		
		GetGroupList(request.getParameter("organizationcode"),tid,request,response);
		
	}
	
	@ResponseBody
	@RequestMapping(value="EditGroup",produces={"application/json;charset=UTF-8"})
	//@RequestMapping("/EditUser")
	public String EditGroup(HttpServletRequest request) {
		String organizationcode = request.getParameter("organizationcode");
		List<Subgroup> groupList = subgroupService.findByOrganizationcode(organizationcode);
		List<GroupUserInfomation> groupUserInfomationList = new ArrayList<GroupUserInfomation>();
		GroupUserInfomation groupUserInfomation = new GroupUserInfomation();
		groupUserInfomation.setGroupList(groupList);
		groupUserInfomationList.add(groupUserInfomation);
		String groupListStr = "";
		groupListStr = JsonTool.listTojsonString(groupList);
		return groupListStr;
	}
	@ResponseBody
	@RequestMapping(value="CheckGroupCode",produces={"application/json;charset=UTF-8"})
	public String CheckGroupCode(HttpServletRequest request) {
		String groupCode = request.getParameter("groupCode");
		List<Subgroup> groupList = subgroupService.findByCode(groupCode);
		if(groupList != null && groupList.size()>0)
		{
			return "false";
		}
		else
		{
			return "success";
		}
	}
	
	@RequestMapping("/GetGroupauthorizeList")
	public void   GetGroupauthorizeList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 40;
		String tid = request.getParameter("tid");
		UserInfomation userInfomation= organiztionUserService.getUserInfomationByOrCode(organizationCode);
		List<Subgroup> groupList= subgroupService.GetGroupListBytype(organizationCode,"操作组");	
		
		request.setAttribute("organizationCode",organizationCode);
		request.setAttribute("userInfomation",userInfomation);
		request.setAttribute("groupList",groupList);
		if(tid.isEmpty())
			tid = "0";
		request.setAttribute("tid",tid);
		try {
			request.getRequestDispatcher("/WEB-INF/view/Groupauthorize.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/SaveGroupauthorize")
	public void SaveGroupauthorize(HttpServletRequest request,HttpServletResponse response) {
		String organizationcode = request.getParameter("organizationcode");
		String tid = request.getParameter("tid");
		//保存之前修改原有数据
		Subgroup groupAuthorize = subgroupService.GetGroupByAuthorize(organizationcode,"操作组","true");
		if(groupAuthorize != null)
		{
			groupAuthorize.setGroupauthorize("false");
			subgroupService.save(groupAuthorize);
		}
		//保存授权
		String groupId = request.getParameter("groupId");
		if(groupId != null && !groupId.isEmpty())
		{
			Subgroup group = subgroupService.findById(Integer.parseInt(groupId));
			group.setGroupauthorize("true");
			subgroupService.save(group);
		}
		
		//更新Taskexport（组授权保存，type=1）
		Taskexport taskexport = subgrouparrangeService.findTaskExport(tid, 1);
		if (taskexport == null) {
			taskexport = new Taskexport();
			taskexport.setTid(tid);
			taskexport.setVersion("1");
			taskexport.setType(1);
		}
		taskexport.setTimes(0);
		taskexport.setStatus(1);
		
		subgrouparrangeService.SetTaskExport(taskexport);
		
		GetGroupauthorizeList(organizationcode,request,response);
		
	}
}