package supton.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;
import supton.service.IFingerprintUserService;
import supton.service.IOrganiztionUserService;
import supton.service.ISubgroupService;
import supton.service.ISubgrouparrangeService;
import supton.service.IUserService;
import supton.tools.JsonTool;

@Controller
public class TimeIntervalAction {

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
	

	@RequestMapping("/GetTimeIntervalList")
	public void   GetTimeIntervalList(String organizationCode,String tid,HttpServletRequest request,HttpServletResponse response) {
		List<Subgroup> groupList = subgroupService.findByOrganizationcode(organizationCode);
		groupList = groupList.stream().filter(p->p.getType().equals("操作组")).collect(Collectors.toList());
		List<Subgrouparrange> subgrouparrangeList = subgrouparrangeService.findByOrganizationcode(organizationCode);
		List<Subgrouparrange> controllTimeList = subgrouparrangeList.stream().filter(p->p.getType().equals("3")).collect(Collectors.toList());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(Subgrouparrange item : controllTimeList)
		{
			item.setBegindateStr(formatter.format(item.getBegindate()));
			item.setEnddateStr(formatter.format(item.getEnddate()));
		}
		request.setAttribute("organizationCode",organizationCode);
		request.setAttribute("controllTimeList",controllTimeList);
		request.setAttribute("groupList",groupList);
		if(tid.isEmpty())
			tid = "0";
		request.setAttribute("tid",tid);
		String groupJson = JsonTool.listTojsonString(groupList);
		request.setAttribute("groupJson",groupJson);
		try {
			request.getRequestDispatcher("/WEB-INF/view/TimeInterval.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/SaveTimeInterval")
	public void SaveTimeInterval(HttpServletRequest request,
			HttpServletResponse response) {
		String organizationCode = request.getParameter("organizationCode");
		String tid = request.getParameter("tid");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 保存之前删除原有数据
		subgrouparrangeService.deleteByOrganizationcode(organizationCode, "3");
		int timeIntervalNum = Integer.parseInt(request
				.getParameter("timeIntervalNum"));

		for (int i = 1; i <= timeIntervalNum; i++) {
			Date begindate = new Date();
			Date enddate = new Date();
			String subgroupcode = request.getParameter("subgroupcode" + i);
			try {
				// begindate = formatter.parse("2014-12-12 11:13:15");
				// enddate = formatter.parse("2014-12-12 16:11:16");
				begindate = formatter.parse(request.getParameter("begindate"
						+ i));
				enddate = formatter.parse(request.getParameter("enddate" + i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Subgrouparrange subgrouparrange = new Subgrouparrange();
			subgrouparrange.setTid(tid);
			subgrouparrange.setVersion("1");
			// 1为执行组，2为预约组,3为时段预约
			subgrouparrange.setType("3");
			subgrouparrange.setSequence(i);
			subgrouparrange.setEnddate(enddate);
			subgrouparrange.setBegindate(begindate);
			subgrouparrange.setOrganizationcode(organizationCode);
			subgrouparrange.setSubgroupcode(subgroupcode);
			subgrouparrangeService.save(subgrouparrange);

		}

		//更新Taskexport（时段保存，type=3）
		Taskexport taskexport = subgrouparrangeService.findTaskExport(tid, 3);
		if (taskexport == null) {
			taskexport = new Taskexport();
			taskexport.setTid(tid);
			taskexport.setVersion("1");
			taskexport.setType(3);
		}
		taskexport.setTimes(0);
		taskexport.setStatus(1);
		
		subgrouparrangeService.SetTaskExport(taskexport);
		
		GetTimeIntervalList(organizationCode,tid, request, response);

	}
}