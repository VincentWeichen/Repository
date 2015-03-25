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

import supton.entity.Controlltime;
import supton.entity.Controlltimedetail;
import supton.entity.Controlltimeercisedate;
import supton.entity.Fingerprintuser;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Rolefunction;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.User;
import supton.entity.pseudo.GroupUserInfomation;
import supton.entity.pseudo.UserInfomation;
import supton.service.IControlltimeService;
import supton.service.IControlltimedetailService;
import supton.service.IControlltimeercisedateService;
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
public class ControllTimeAction {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private IControlltimeService controlltimeService;
	
	@Autowired
	private IControlltimedetailService controlltimedetailService;
	
	@Autowired
	private IControlltimeercisedateService controlltimeercisedateService;

	@RequestMapping("/GetControllTimeList")
	public void   GetControllTimeList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		List<Controlltime> controlltimeList = controlltimeService.findByOrganizationcode(organizationCode);
		List<Controlltimedetail> controlltimedetailList = controlltimedetailService.findByOrganizationcode(organizationCode);
		List<Controlltimeercisedate> controlltimeercisedateList = controlltimeercisedateService.findByOrganizationcode(organizationCode);
		//周循环列表
		List<Controlltime> controlltimeZhouList = controlltimeList.stream().filter(p->p.getType().equals("1")).collect(Collectors.toList());
		if(controlltimeZhouList !=null && controlltimeZhouList.size() > 0)
		{
			for(Controlltime controlltime : controlltimeZhouList)
			{
				List<Controlltimedetail> controlltimedetailZhouWorkList = controlltimedetailList.stream().filter(p->p.getType().equals("1") && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				List<Controlltimeercisedate> controlltimeercisedateZhouWorkList = controlltimeercisedateList.stream().filter(p->p.getType().equals("1")  && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				controlltime.setControlltimedetailWorkList(controlltimedetailZhouWorkList);
				controlltime.setControlltimeercisedateWorkList(controlltimeercisedateZhouWorkList);
			}
			request.setAttribute("controlltimeZhouList",controlltimeZhouList);
		}
		
		request.setAttribute("organizationCode",organizationCode);
		try {
			request.getRequestDispatcher("/WEB-INF/view/ControlTime.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/GetControllTimeByYearList")
	public void GetControllTimeByYearList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		List<Controlltime> controlltimeList = controlltimeService.findByOrganizationcode(organizationCode);
		List<Controlltimedetail> controlltimedetailList = controlltimedetailService.findByOrganizationcode(organizationCode);
		List<Controlltimeercisedate> controlltimeercisedateList = controlltimeercisedateService.findByOrganizationcode(organizationCode);
		
		//年循环列表
		List<Controlltime> controlltimeYearList = controlltimeList.stream().filter(p->p.getType().equals("2")).collect(Collectors.toList());
		if(controlltimeYearList !=null && controlltimeYearList.size() > 0)
		{
			for(Controlltime controlltime : controlltimeYearList)
			{
				List<Controlltimedetail> controlltimedetailYearWorkList = controlltimedetailList.stream().filter(p->p.getType().equals("1") && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				List<Controlltimeercisedate> controlltimeercisedateYearWorkList = controlltimeercisedateList.stream().filter(p->p.getType().equals("1")  && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				controlltime.setControlltimedetailWorkList(controlltimedetailYearWorkList);
				controlltime.setControlltimeercisedateWorkList(controlltimeercisedateYearWorkList);
			}
			request.setAttribute("controlltimeYearList",controlltimeYearList);
		}
		request.setAttribute("organizationCode",organizationCode);
		try {
			request.getRequestDispatcher("/WEB-INF/view/ControlTimeByYear.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/GetControllTimeHolidayList")
	public void GetControllTimeHolidayList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		List<Controlltime> controlltimeList = controlltimeService.findByOrganizationcode(organizationCode);
		List<Controlltimedetail> controlltimedetailList = controlltimedetailService.findByOrganizationcode(organizationCode);
		List<Controlltimeercisedate> controlltimeercisedateList = controlltimeercisedateService.findByOrganizationcode(organizationCode);
		//周循环列表
		List<Controlltime> controlltimeHolidayList = controlltimeList.stream().filter(p->p.getType().equals("3")).collect(Collectors.toList());
		if(controlltimeHolidayList !=null && controlltimeHolidayList.size() > 0)
		{
			for(Controlltime controlltime : controlltimeHolidayList)
			{
				List<Controlltimedetail> controlltimedetailZhouWorkList = controlltimedetailList.stream().filter(p->p.getType().equals("1") && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				List<Controlltimeercisedate> controlltimeercisedateZhouWorkList = controlltimeercisedateList.stream().filter(p->p.getType().equals("1")  && p.getControlltimeid().equals(controlltime.getId())).collect(Collectors.toList());
				controlltime.setControlltimedetailWorkList(controlltimedetailZhouWorkList);
				controlltime.setControlltimeercisedateWorkList(controlltimeercisedateZhouWorkList);
			}
			request.setAttribute("controlltimeHolidayList",controlltimeHolidayList);
		}
		
		request.setAttribute("organizationCode",organizationCode);
		try {
			request.getRequestDispatcher("/WEB-INF/view/ControlTimeHoliday.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/DeleteControllTime")
	public void   DeleteControllTime(String groupCode,HttpServletRequest request,HttpServletResponse response) {
		// 新建人员，测试
		
//		int groupNum = subgroupService.deleteGroup(groupCode);
//		int groupUserNum = subgroupUserService.deleteGroupUser(groupCode);
		
		String corganizationCode = request.getParameter("corganizationCode");
		GetControllTimeList(corganizationCode,request,response);
	}
	
	@RequestMapping("/DeleteControllTimeList")
	public void   DeleteControllTimeList(HttpServletRequest request,HttpServletResponse response) {
		String[] groupcodes = request.getParameterValues("groupCode");
		String loginMessage = "";
		for(int i=0;i<groupcodes.length;i++)
		{
//			int groupNum = subgroupService.deleteGroup(groupcodes[i]);
//			int groupUserNum = subgroupUserService.deleteGroupUser(groupcodes[i]);
		}
		String corganizationCode = request.getParameter("corganizationCode");
		GetControllTimeList(corganizationCode,request,response);
	}
	
	@RequestMapping("/SaveControlltime")
	public void SaveControlltime(HttpServletRequest request,HttpServletResponse response) {
		String organizationCode = request.getParameter("organizationCode");
		String controlltimeType = request.getParameter("controlltimeType");
		
		Integer id = null;
		
		//1：周循环；
		if(controlltimeType.equals("1"))
		{
			String controlltimeId = request.getParameter("controlltimeId");
			if(controlltimeId != null && !controlltimeId.isEmpty() && !controlltimeId.equals("0"))
			{
				id = Integer.parseInt(request.getParameter("controlltimeId"));
			}
			else
			{
				Controlltime controlltime = new Controlltime();
				controlltime.setOrganizationcode(organizationCode);
				controlltime.setStatus(1);
				controlltime.setType(controlltimeType);
				controlltime.setSequence(1);
				controlltimeService.save(controlltime);
				//需要测试能否直接取到
				id=controlltime.getId();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String begindate = request.getParameter("begindate");
			String enddate = request.getParameter("enddate");
			//1：工作日管制；2：休休息日管制
			String controlltimedetailZhouType = request.getParameter("controlltimedetailZhouType");
			String controlltimedetailId = request.getParameter("controlltimedetailId");
			String weekday = request.getParameter("weekday");
			
			List<Controlltimedetail> controlltimedetailList = controlltimedetailService.findByOrganizationcode(organizationCode,controlltimedetailZhouType);
			Controlltimedetail controlltimedetail = new Controlltimedetail();
			if(controlltimedetailId !=null &&  !controlltimedetailId.isEmpty())
			{
				controlltimedetail = controlltimedetailService.findById(Integer.parseInt(controlltimedetailId));
			}else
			{
				controlltimedetail.setControlltimeid(id);
				controlltimedetail.setOrganizationcode(organizationCode);
				controlltimedetail.setSequence(controlltimedetailList.size()+1);
				controlltimedetail.setStatus(1);
				//1：工作日管制；2：休息日管制
				controlltimedetail.setType("1");
			}
			controlltimedetail.setBegindate(begindate);
			controlltimedetail.setEnddate(enddate);
			
			controlltimedetailService.save(controlltimedetail);
			//执行日保存
			controlltimeercisedateService.deleteByOrganizationcode(organizationCode,controlltimedetailZhouType,id);
			Controlltimeercisedate controlltimeercisedate = new Controlltimeercisedate();
			controlltimeercisedate.setControlltimeid(id);
			controlltimeercisedate.setSequence(1);
			controlltimeercisedate.setWeekday(weekday);
			controlltimeercisedate.setOrganizationcode(organizationCode);
			//1：工作日管制；2：休休息日管制
			controlltimeercisedate.setType("1");
			controlltimeercisedateService.save(controlltimeercisedate);
			
			GetControllTimeList(organizationCode, request, response);
		}
		//2：年循环
		if(controlltimeType.equals("2"))
		{
			String controlltimeYearId = request.getParameter("controlltimeYearId");
			if(controlltimeYearId != null && !controlltimeYearId.isEmpty() && !controlltimeYearId.equals("0"))
			{
				id = Integer.parseInt(request.getParameter("controlltimeYearId"));
			}
			else
			{
				Controlltime controlltime = new Controlltime();
				controlltime.setOrganizationcode(organizationCode);
				controlltime.setStatus(1);
				controlltime.setType(controlltimeType);
				controlltime.setSequence(1);
				controlltimeService.save(controlltime);
				//需要测试能否直接取到
				id=controlltime.getId();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//controlltimedetailYearZhixingType:1为工作日执行；2为休息日执行
			//controlltimedetailYearGuanzhiType:1为工作日管制；2为休息日管制
			String controlltimedetailYearZhixingType = request.getParameter("controlltimedetailYearZhixingType");
			String controlltimedetailYearGuanzhiType = request.getParameter("controlltimedetailYearGuanzhiType");
			
			if(controlltimedetailYearGuanzhiType != null && !controlltimedetailYearGuanzhiType.isEmpty())
			{
				String controlltimedetailYearGuanzhiId = request.getParameter("controlltimedetailYearGuanzhiId");
				String begindate = request.getParameter("begindateYearGuanzhi");
				String enddate = request.getParameter("enddateYearGuanzhi");
				List<Controlltimedetail> controlltimedetailList = controlltimedetailService.findByOrganizationcode(organizationCode,controlltimedetailYearGuanzhiType);
				Controlltimedetail controlltimedetail = new Controlltimedetail();
				if(controlltimedetailYearGuanzhiId !=null &&  !controlltimedetailYearGuanzhiId.isEmpty())
				{
					controlltimedetail = controlltimedetailService.findById(Integer.parseInt(controlltimedetailYearGuanzhiId));
				}else
				{
					controlltimedetail.setControlltimeid(id);
					controlltimedetail.setOrganizationcode(organizationCode);
					controlltimedetail.setSequence(controlltimedetailList.size()+1);
					controlltimedetail.setStatus(1);
					//1：工作日管制；2：休息日管制
					controlltimedetail.setType("1");
				}
				controlltimedetail.setBegindate(begindate);
				controlltimedetail.setEnddate(enddate);
				
				controlltimedetailService.save(controlltimedetail);
			}
			if(controlltimedetailYearZhixingType != null && !controlltimedetailYearZhixingType.isEmpty())
			{
				String begindate = request.getParameter("begindateYear");
				String enddate = request.getParameter("enddateYear");
				String controlltimedetailYearZhixingId = request.getParameter("controlltimedetailYearZhixingId");
				//执行日保存
				Controlltimeercisedate controlltimeercisedate = new Controlltimeercisedate();
				if(controlltimedetailYearZhixingId != null && !controlltimedetailYearZhixingId.isEmpty())
				{
					controlltimeercisedate = controlltimeercisedateService.findById(Integer.parseInt(controlltimedetailYearZhixingId));
				}
				controlltimeercisedate.setControlltimeid(id);
				controlltimeercisedate.setSequence(1);
				controlltimeercisedate.setOrganizationcode(organizationCode);
				//1：工作日管制；2：休休息日管制
				controlltimeercisedate.setType("1");
				controlltimeercisedate.setBegindate(begindate);
				controlltimeercisedate.setEnddate(enddate);
				controlltimeercisedateService.save(controlltimeercisedate);
			}
			
			GetControllTimeByYearList(organizationCode, request, response);
		}
		//3：假期/调班
		if(controlltimeType.equals("3"))
		{
			String controlltimeId = request.getParameter("controlltimeId");
			if(controlltimeId != null && !controlltimeId.isEmpty() && !controlltimeId.equals("0"))
			{
				id = Integer.parseInt(request.getParameter("controlltimeId"));
			}
			else
			{
				Controlltime controlltime = new Controlltime();
				controlltime.setOrganizationcode(organizationCode);
				controlltime.setStatus(1);
				controlltime.setType(controlltimeType);
				controlltime.setSequence(1);
				controlltimeService.save(controlltime);
				//需要测试能否直接取到
				id=controlltime.getId();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String begindate = request.getParameter("begindate");
			String enddate = request.getParameter("enddate");
			String controlltimedetailId = request.getParameter("controlltimedetailId");
			Controlltimedetail controlltimedetail = new Controlltimedetail();
			if(controlltimedetailId !=null &&  !controlltimedetailId.isEmpty())
			{
				controlltimedetail = controlltimedetailService.findById(Integer.parseInt(controlltimedetailId));
			}else
			{
				controlltimedetail.setControlltimeid(id);
				controlltimedetail.setOrganizationcode(organizationCode);
//				controlltimedetail.setSequence(controlltimedetailList.size()+1);
				controlltimedetail.setStatus(1);
				//1：工作日管制；
				controlltimedetail.setType("1");
			}
			controlltimedetail.setBegindate(begindate);
			controlltimedetail.setEnddate(enddate);
			
			controlltimedetailService.save(controlltimedetail);
			//执行日保存
			String begindateHoliday = request.getParameter("begindateHoliday");
			String enddateHoliday = request.getParameter("enddateHoliday");
			String description = request.getParameter("description");
			controlltimeercisedateService.deleteByOrganizationcode(organizationCode,"1",id);
			Controlltimeercisedate controlltimeercisedate = new Controlltimeercisedate();
			controlltimeercisedate.setControlltimeid(id);
			controlltimeercisedate.setSequence(1);
			controlltimeercisedate.setBegindate(begindateHoliday);
			controlltimeercisedate.setEnddate(enddateHoliday);
			controlltimeercisedate.setOrganizationcode(organizationCode);
			controlltimeercisedate.setDescription(description);
			//1：工作日管制；2：休休息日管制
			controlltimeercisedate.setType("1");
			controlltimeercisedateService.save(controlltimeercisedate);
			
			GetControllTimeHolidayList(organizationCode, request, response);
		}

	}
	
	@ResponseBody
	@RequestMapping(value="EditcontrolltimeZhou",produces={"application/json;charset=UTF-8"})
	public String EditcontrolltimeZhou(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Controlltimedetail controlltimedetail = controlltimedetailService.findById(Integer.parseInt(id));
		String tcontrolltimeZhouJson = "";
		if(controlltimedetail != null)
		{
			tcontrolltimeZhouJson = JsonTool.objectToJsonString(controlltimedetail);
		}	
		return tcontrolltimeZhouJson;
	}
	
	@RequestMapping("/DeletecontrolltimeZhou")
	public void   DeletecontrolltimeZhou(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String organizationCode = request.getParameter("organizationCode");
		int userNum = controlltimedetailService.delete(Integer.parseInt(id));
		GetControllTimeList(organizationCode, request, response);
	}
	
	@RequestMapping("/DeleteControlltimeercisedate")
	public void   DeleteControlltimeercisedate(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String organizationCode = request.getParameter("organizationCode");
		int userNum = controlltimeercisedateService.delete(Integer.parseInt(id));
		GetControllTimeByYearList(organizationCode, request, response);
	}
	
	@RequestMapping("/DeletecontrolltimeByYear")
	public void  DeletecontrolltimeByYear(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String organizationCode = request.getParameter("organizationCode");
		int Num = controlltimedetailService.delete(Integer.parseInt(id));
		GetControllTimeByYearList(organizationCode, request, response);
	}
	
	@RequestMapping("/DeleteControlltimeHoliday")
	public void   DeleteControlltimeHoliday(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String organizationCode = request.getParameter("organizationCode");
//		Controlltimeercisedate controlltimeercisedate = controlltimeercisedateService.findById(Integer.parseInt(id));
//		controlltimeercisedateService.delete(Integer.parseInt(id));
		int Num = controlltimedetailService.delete(Integer.parseInt(id));

		GetControllTimeHolidayList(organizationCode, request, response);
	}
	
	@ResponseBody
	@RequestMapping(value="EditcontrolltimeZhixingYear",produces={"application/json;charset=UTF-8"})
	public String EditcontrolltimeZhixingYear(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Controlltimeercisedate controlltimeercisedate = controlltimeercisedateService.findById(Integer.parseInt(id));
		String controlltimeercisedateJson = "";
		if(controlltimeercisedate != null)
		{
			controlltimeercisedateJson = JsonTool.objectToJsonString(controlltimeercisedate);
		}	
		return controlltimeercisedateJson;
	}
	
	@ResponseBody
	@RequestMapping(value="EditcontrolltimeGuanzhiYear",produces={"application/json;charset=UTF-8"})
	public String EditcontrolltimeGuanzhiYear(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Controlltimedetail controlltimedetail = controlltimedetailService.findById(Integer.parseInt(id));
		String tcontrolltimeYearJson = "";
		if(controlltimedetail != null)
		{
			tcontrolltimeYearJson = JsonTool.objectToJsonString(controlltimedetail);
		}	
		return tcontrolltimeYearJson;
	}
	
	@ResponseBody
	@RequestMapping(value="CheckControllTimeCode",produces={"application/json;charset=UTF-8"})
	public String CheckControllTimeCode(HttpServletRequest request) {
		String groupCode = request.getParameter("groupCode");

			return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="EditcontrolltimeHoliday",produces={"application/json;charset=UTF-8"})
	public String EditcontrolltimeHoliday(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Controlltimedetail controlltimedetail = controlltimedetailService.findById(Integer.parseInt(id));
		String tcontrolltimeHolidayJson = "";
		if(controlltimedetail != null)
		{
			tcontrolltimeHolidayJson = JsonTool.objectToJsonString(controlltimedetail);
		}	
		return tcontrolltimeHolidayJson;
	}
	
	@RequestMapping("/DeleteControlltime")
	public void   DeleteControlltime(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String organizationCode = request.getParameter("organizationCode");
		controlltimeService.deleteByControlltimeId(Integer.parseInt(id));
		controlltimeercisedateService.deleteByControlltimeId(Integer.parseInt(id));
		controlltimedetailService.deleteByControlltimeId(Integer.parseInt(id));
		if(type != null && type.equals("1"))
		{
			GetControllTimeList(organizationCode, request, response);
		}else if(type != null && type.equals("2"))
		{
			GetControllTimeByYearList(organizationCode, request, response);
		}else
		{
			GetControllTimeHolidayList(organizationCode, request, response);
		}
		
	}
	
}