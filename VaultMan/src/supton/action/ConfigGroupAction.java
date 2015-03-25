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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdicons.json.mapper.MapperException;

import supton.entity.Fingerprintuser;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;
import supton.entity.User;
import supton.entity.pseudo.GroupUserInfomation;
import supton.entity.pseudo.UserInfomation;
import supton.service.IFingerprintUserService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IRoleUserService;
import supton.service.ISubgroupService;
import supton.service.ISubgrouparrangeService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class ConfigGroupAction {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private ISubgrouparrangeService grouparrangeService;
	
	@Autowired
	private ISubgroupService subgroupService;
	
	@Autowired
	private IFingerprintUserService fingerprintUserService;
	
	@Autowired
	private ISubgrouparrangeService subgrouparrangeService;
	

	@RequestMapping("/GetConfigGroupList")
	public void   GetConfigGroupList(String organizationCode,String tid,HttpServletRequest request,HttpServletResponse response) {
		List<Subgroup> groupList = subgroupService.findByOrganizationcode(organizationCode);
		groupList = groupList.stream().filter(p->p.getType().equals("操作组")).collect(Collectors.toList());
		List<Subgrouparrange> subgrouparrangeList = subgrouparrangeService.findByOrganizationcode(organizationCode);
		List<Subgrouparrange> zhixingdataList = subgrouparrangeList.stream().filter(p->p.getType().equals("1")).collect(Collectors.toList());
		List<Subgrouparrange> yuyuedataList = subgrouparrangeList.stream().filter(p->p.getType().equals("2")).collect(Collectors.toList());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(zhixingdataList !=null && zhixingdataList.size()>0)
		{
			//时间设置，执行
			request.setAttribute("begindate_Zhixing",formatter.format(zhixingdataList.get(0).getBegindate()));
			request.setAttribute("switchtime_Zhixing_H",zhixingdataList.get(0).getSwitchtime().substring(0, zhixingdataList.get(0).getSwitchtime().indexOf(":")));
			request.setAttribute("switchtime_Zhixing_M",zhixingdataList.get(0).getSwitchtime().substring(zhixingdataList.get(0).getSwitchtime().indexOf(":")+1));
		}
		if(yuyuedataList !=null && yuyuedataList.size()>0)
		{
			//时间设置，预约
			request.setAttribute("begindate_Yuyue",formatter.format(yuyuedataList.get(0).getBegindate()));
			request.setAttribute("switchtime_Yuyue_H",yuyuedataList.get(0).getSwitchtime().substring(0, yuyuedataList.get(0).getSwitchtime().indexOf(":")));
			request.setAttribute("switchtime_Yuyue_M",yuyuedataList.get(0).getSwitchtime().substring(yuyuedataList.get(0).getSwitchtime().indexOf(":")+1));
		}
		request.setAttribute("organizationCode",organizationCode);
		request.setAttribute("zhixingdataNum",zhixingdataList.size());
		request.setAttribute("yuyuedataNum",yuyuedataList.size());
		request.setAttribute("zhixingdataList",zhixingdataList);
		request.setAttribute("yuyuedataList",yuyuedataList);
		request.setAttribute("groupList",groupList);
		if(tid.isEmpty())
			tid = "0";
		request.setAttribute("tid",tid);
		String groupJson = JsonTool.listTojsonString(groupList);
		request.setAttribute("groupJson",groupJson);
		try {
			request.getRequestDispatcher("/WEB-INF/view/ConfigGroup.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@RequestMapping("/DeleteConfigGroup")
	public void   DeleteConfigGroup(String groupCode,HttpServletRequest request,HttpServletResponse response) {
		String corganizationCode = request.getParameter("corganizationCode");
		String tid = request.getParameter("tid");
		int groupNum = subgroupService.deleteGroup(groupCode,corganizationCode);
		GetConfigGroupList(corganizationCode,tid,request,response);
	}
	
	@RequestMapping("/SaveConfigGroup")
	public void SaveConfigGroup(HttpServletRequest request,HttpServletResponse response) {
		String organizationcode = request.getParameter("organizationCode");
		String tid = request.getParameter("tid");
		String type = request.getParameter("type");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		int zhixingdataNum = Integer.parseInt(request.getParameter("zhixingdataNum"));
		int yuyuedataNum = Integer.parseInt(request.getParameter("yuyuedataNum"));
		
		//淇濆瓨涔嬪墠鍒犻櫎鍘熸湁鏁版嵁
		subgrouparrangeService.deleteByOrganizationcode(organizationcode,"1");
		//鎵ц
		//if(type.equals("1"))
		//{
			//int zhixingdataNum = Integer.parseInt(request.getParameter("zhixingdataNum"));
			String switchtime_Zhixing_H = request.getParameter("switchtime_Zhixing_H");
			String switchtime_Zhixing_M = request.getParameter("switchtime_Zhixing_M");
			String switchtime_Zhixing = switchtime_Zhixing_H+":"+switchtime_Zhixing_M;
			Date begindate_Zhixing = new Date();
			try {
				begindate_Zhixing = formatter.parse(request.getParameter("begindate_Zhixing"));	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			for(int i=1;i<=zhixingdataNum;i++)
			{
				String subgroupcode = request.getParameter("subgroupcode_Zhixing"+i);
				int timeduration = Integer.parseInt(request.getParameter("timeduration_Zhixing"+i));
				Subgrouparrange subgrouparrange = new Subgrouparrange();
				subgrouparrange.setTid(tid);
				subgrouparrange.setVersion("1");
				//1涓烘墽琛岀粍锛�2涓洪绾︾粍
				subgrouparrange.setType("1");
				subgrouparrange.setSequence(i);
				subgrouparrange.setSwitchtime(switchtime_Zhixing);
				subgrouparrange.setBegindate(begindate_Zhixing);
				subgrouparrange.setOrganizationcode(organizationcode);
				subgrouparrange.setSubgroupcode(subgroupcode);
				subgrouparrange.setTimeduration(timeduration);
				subgrouparrangeService.save(subgrouparrange);
			}
		//}
		//棰勭害
		
			subgrouparrangeService.deleteByOrganizationcode(organizationcode,"2");
		//if(type.equals("2"))
		//{
			//int yuyuedataNum = Integer.parseInt(request.getParameter("yuyuedataNum"));
			String switchtime_Yuyue_H = request.getParameter("switchtime_Yuyue_H");
			String switchtime_Yuyue_M = request.getParameter("switchtime_Yuyue_M");
			String switchtime_Yuyue = switchtime_Yuyue_H+":"+switchtime_Yuyue_M;
			Date begindate_Yuyue = new Date();
			try {
				begindate_Yuyue = formatter.parse(request.getParameter("begindate_Yuyue"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for(int i=1;i<=yuyuedataNum;i++)
			{
				String subgroupcode = request.getParameter("subgroupcode_Yuyue"+i);
				int timeduration = Integer.parseInt(request.getParameter("timeduration_Yuyue"+i));
				Subgrouparrange subgrouparrange = new Subgrouparrange();
				subgrouparrange.setTid(tid);
				subgrouparrange.setVersion("1");
				//2涓洪绾︾粍
				subgrouparrange.setType("2");
				subgrouparrange.setSequence(i);
				subgrouparrange.setSwitchtime(switchtime_Yuyue);
				subgrouparrange.setBegindate(begindate_Yuyue);
				subgrouparrange.setOrganizationcode(organizationcode);
				subgrouparrange.setSubgroupcode(subgroupcode);
				subgrouparrange.setTimeduration(timeduration);
				subgrouparrangeService.save(subgrouparrange);
			}
		//}
			
			//更新Taskexport（排班保存，type=2）
			Taskexport taskexport = subgrouparrangeService.findTaskExport(tid, 2);
			if (taskexport == null) {
				taskexport = new Taskexport();
				taskexport.setTid(tid);
				taskexport.setVersion("1");
				taskexport.setType(2);
			}
			taskexport.setTimes(0);
			taskexport.setStatus(1);
			
			subgrouparrangeService.SetTaskExport(taskexport);
			
			
		GetConfigGroupList(organizationcode,tid,request,response);
	}
	
	@ResponseBody
	@RequestMapping(value="EditConfigGroup",produces={"application/json;charset=UTF-8"})
	//@RequestMapping("/EditUser")
	public String EditConfigGroup(HttpServletRequest request) {
		String organizationcode = request.getParameter("organizationcode");
		
		List<Subgroup> groupList = subgroupService.findByOrganizationcode(organizationcode);
		List<GroupUserInfomation> groupUserInfomationList = new ArrayList<GroupUserInfomation>();
		GroupUserInfomation groupUserInfomation = new GroupUserInfomation();
		groupUserInfomation.setGroupList(groupList);

		groupUserInfomationList.add(groupUserInfomation);
		String groupuser = "";
		groupuser = JsonTool.listTojsonString(groupUserInfomationList);
		return groupuser;
	}
	@ResponseBody
	@RequestMapping(value="CheckConfigGroupCode",produces={"application/json;charset=UTF-8"})
	public String CheckConfigGroupCode(HttpServletRequest request) {
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
}