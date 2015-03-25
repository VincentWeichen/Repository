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
import supton.entity.Flowinstance;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
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
public class InstanceAction {

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
	

	@RequestMapping("/GetInstanceList")
	public void   GetInstanceList(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		List<Subgroup> groupList = subgroupService.findByOrganizationcode(organizationCode);
		groupList = groupList.stream().filter(p->p.getType().equals("操作组")).collect(Collectors.toList());
		List<Subgrouparrange> subgrouparrangeList = subgrouparrangeService.findByOrganizationcode(organizationCode);
		List<Subgrouparrange> zhixingdataList = subgrouparrangeList.stream().filter(p->p.getType().equals("1")).collect(Collectors.toList());
		List<Subgrouparrange> yuyuedataList = subgrouparrangeList.stream().filter(p->p.getType().equals("2")).collect(Collectors.toList());
		if(zhixingdataList !=null && zhixingdataList.size()>0)
		{
			//时间设置，执行
			request.setAttribute("begindate_Zhixing",zhixingdataList.get(0).getBegindate().toString());
			request.setAttribute("switchtime_Zhixing",zhixingdataList.get(0).getSwitchtime().toString());
		}
		if(yuyuedataList !=null && yuyuedataList.size()>0)
		{
			//时间设置，预约
			request.setAttribute("begindate_Yuyue",yuyuedataList.get(0).getBegindate().toString());
			request.setAttribute("switchtime_Yuyue",yuyuedataList.get(0).getSwitchtime().toString());
		}
		request.setAttribute("organizationCode",organizationCode);
		request.setAttribute("zhixingdataNum",zhixingdataList.size());
		request.setAttribute("yuyuedataNum",yuyuedataList.size());
		request.setAttribute("zhixingdataList",zhixingdataList);
		request.setAttribute("yuyuedataList",yuyuedataList);
		request.setAttribute("groupList",groupList);
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

//    /// <summary>
//    /// 将XML流程模板插入数据库
//    /// </summary>
//    /// <param name="templateId">模板编号</param>
//    /// <param name="path">模板路径</param>
//    /// <param name="createUser">创建人</param>
//    /// <returns>流程模板实体</returns>
//    public Template PublishTemplate(string templateId, string path, string createUser)
//    {
//        try
//        {
//            if (File.Exists(path))
//            {
//                using (StreamReader sr = new StreamReader(path))
//                {
//                    Template template = new Template();
//                    template.TemplateId = templateId;
//                    template.TemplateXmlString = sr.ReadToEnd();
//                    template.CreateUser = createUser;
//                    template.CreateTime = DateTime.Now;
//
//                    ctx.Template.InsertOnSubmit(template);
//                    ctx.SubmitChanges();
//                    sr.Close();
//                    return template;
//                }
//            }
//            else
//                return null;
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.PublishTemplate", ex.Message, ex.StackTrace);
//            return null;
//        }
//
//    }
//
//    /// <summary>
//    /// 创建流程实例
//    /// </summary>
//    /// <param name="templateId">模板编号</param>
//    /// <param name="createUser">创建人</param>
//    /// <returns>流程实例</returns>
//    public Flowinstance CreateInstance(String templateId, String createUser)
//    {
//            try
//            {
//            	ctx.Template.Where(p => p.TemplateId == templateId).OrderByDescending(p => p.CreateTime).First().TemplateXmlString;
//            	Flowinstance instance = new Flowinstance();
//            	instance.setTemplateId(Integer.parseInt(templateId));
//            	instance.TemplateXmlString = 
//            	instance.CreateUser = createUser;
//            	instance.CreateTime = DateTime.Now;
//            	instance.State = 1;
//                ctx.Instance.InsertOnSubmit(instance);
//                ctx.SubmitChanges();
//
//                var templateXmlString = instance.TemplateXmlString;
//                XDocument xdoc = XDocument.Parse(templateXmlString);
//                var startActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == "开始状态").SingleOrDefault();
//                if (startActivity != null)
//                {
//                    // 插入开始节点
//                    InstanceState startState = new InstanceState();
//                    startState.ParentState = null;
//                    startState.InstanceId = instance.Id;
//                    startState.StateId = "开始状态";
//                    startState.TransactUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                    startState.CreateTime = DateTime.Now;
//                    
//                    startState.State = 2;
//                    startState.SubmitUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                    startState.SubmitOption = startActivity.Attribute("SubmitOption").Value;
//                    startState.SubmitResult = startActivity.Attribute("SubmitOption").Value;
//                    startState.NextUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                    ctx.InstanceState.InsertOnSubmit(startState);
//                    ctx.SubmitChanges();
//
//                    // 下一办理节点
//                    var targetActivity = startActivity.Elements().FirstOrDefault();
//                    if (targetActivity != null)
//                    {
//                        var targetStateName = targetActivity.Attribute("TargetStateName").Value;
//                        var nextStateActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == targetStateName).SingleOrDefault();
//
//                        // 创建下一办理节点
//                        CreateState(instance.Id, nextStateActivity, startState, "0", "", (HttpContext.Current.Session["UserInfo"] as V_User).UserId);
//                    }
//
//                    return instance;
//                }
//                else
//                    return null;
//            }
//            catch (Exception ex)
//            {
//                InserError("WFService.CreateInstance", ex.Message, ex.StackTrace);
//                return null;
//            }
//    }
//
//    public InstanceState CreateNextState(int instanceId, InstanceState startState, string submitStatus, string DrawingPicStatus,string nextUser)
//    {
//        InstanceState nexState = null;
//        var templateXmlString = ctx.Instance.Where(p => p.Id == instanceId).Single().TemplateXmlString;
//                XDocument xdoc = XDocument.Parse(templateXmlString);
//                var startActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == startState.StateId).SingleOrDefault();
//                // 下一办理节点
//                var targetActivity = startActivity.Elements().FirstOrDefault();
//                if (targetActivity != null)
//                {
//                  //  var targetStateName = targetActivity.Attribute("TargetStateName").Value;
//                  //  var targetStateName = targetActivity.Elements("ConditionItemActivity").Where(p => p.Attribute("Name").Value == "setStateActivity"+status).SingleOrDefault().Attribute("TargetStateName").Value;
//
//                    var targetStateName = targetActivity.Elements("ConditionItemActivity").Elements("SetStateActivity").Where(p => p.Attribute("Name").Value == "setStateActivity" + submitStatus).SingleOrDefault().Attribute("TargetStateName").Value;
//                    var nextStateActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == targetStateName).SingleOrDefault();
//
//                    // 创建下一办理节点
//                    nexState = CreateState(instanceId, nextStateActivity, startState,submitStatus,DrawingPicStatus,nextUser);
//                }
//                return nexState;
//    }
//        
//
//
//    /// <summary>
//    /// 得到当前办理节点
//    /// </summary>
//    /// <param name="instanceId">流程编号</param>
//    /// <returns>当前节点</returns>
//    public InstanceState GetCurrentState(int instanceId)
//    {
//        var currentState = ctx.InstanceState.Where(p => p.InstanceId == instanceId).OrderByDescending(p => p.Id).FirstOrDefault();
//        if (currentState != null)
//        {
//            Console.WriteLine("得到当前办理节点:" + currentState.StateId);
//        }
//        return currentState;
//    }
//
//    /// <summary>
//    /// 提交办理节点
//    /// </summary>
//    /// <param name="instanceState">流程节点</param>
//    /// <returns>当前节点</returns>
//    public InstanceState SubmitState(InstanceState instanceState)
//    {
//        // 验证不能为空项
//        if (instanceState == null ||
//            string.IsNullOrEmpty(instanceState.StateId) ||
//            string.IsNullOrEmpty(instanceState.SubmitUser) ||
//            string.IsNullOrEmpty(instanceState.SubmitResult))
//        {
//            return null;
//        }
//
//        try
//        {
//            // 验证提交节点是否为等待提交节点
//            var instanceStateList = ctx.InstanceState.Where(p => p.InstanceId == instanceState.InstanceId).ToList();
//            var currentState = instanceStateList.Where(p => p.State == 1).SingleOrDefault();
//            if (currentState == null || currentState.StateId != instanceState.StateId)
//                return null;
//
//            // 更新节点信息
//            currentState.SubmitUser = instanceState.SubmitUser;
//            currentState.SubmitResult = instanceState.SubmitResult;
//            currentState.CompleteTime = DateTime.Now;
//            currentState.State = 2;
//          //  currentState.NextUser = string.IsNullOrEmpty(instanceState.NextUser) ? string.Empty : instanceState.NextUser;
//         
//            ctx.SubmitChanges();
//
//            Console.WriteLine("提交办理节点,更新节点信息成功！");
//
//            // 节点路由
//            ActivityRouter(currentState);
//
//            return currentState;
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.SubmitState", ex.Message, ex.StackTrace);
//            return null;
//        }
//    }
//
//    /// <summary>
//    /// 得到流程实例
//    /// </summary>
//    /// <param name="instanceId">流程实例编号</param>
//    /// <returns>流程实例</returns>
//    public Instance GetInstance(int instanceId)
//    {
//        return ctx.Instance.Where(p => p.Id == instanceId).SingleOrDefault();
//    }
//
//    /// <summary>
//    /// 得到流程节点列表
//    /// </summary>
//    /// <param name="instanceId">流程实例编号</param>
//    /// <returns>流程节点列表</returns>
//    public List<InstanceState> GetInstanceStateList(int instanceId)
//    {
//        return ctx.InstanceState.Where(p => p.InstanceId == instanceId).OrderBy(p => p.CreateTime).ToList();
//    }
//
//    /// <summary>
//    /// 通过审核者编号得到流程编号列表
//    /// </summary>
//    /// <param name="AuditorUserId">审核者编号</param>
//    /// <returns>流程编号列表</returns>
//    public List<int> GetInstanceIdListByAuditorUserId(string auditorUserId)
//    {
//        return ctx.InstanceState.Where(p => p.SubmitUser == auditorUserId).Select(p => p.InstanceId).Distinct().ToList();
//    }
//
//    #endregion
//
//    #region 私有方法
//
//    /// <summary>
//    /// 创建节点
//    /// </summary>
//    /// <param name="instanceId">流程实例编号</param>
//    /// <param name="stateActivity">当前节点模板</param>
//    private InstanceState CreateState(int instanceId, XElement stateActivity, InstanceState currentState, string submitStatus, string DrawingPicStatus,string nextUser)
//    {
//        InstanceState state = new InstanceState();
//        try
//        {
//            if ((HttpContext.Current.Session["UserInfo"] as V_User).DeptName == "销售支持" && submitStatus == "2" && DrawingPicStatus == "方 案")
//            {
//                stateActivity.Attribute("Name").Value = "结束状态";  //销售支持 ，且是标准图纸通过的时候。
//            }
//            if (stateActivity.Attribute("Name").Value == "结束状态")
//            {
//                Console.WriteLine("流程已到结束状态！");
//                var instance = ctx.Instance.SingleOrDefault(p => p.Id == instanceId);
//                if (instance != null)
//                {
//                    instance.ComplateTime = DateTime.Now;
//                    instance.State = 2;
//                }
//                state.StateId = "结束状态";
//                state.TransactUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//            }
//            else
//            {
//                state = new InstanceState();
//                state.ParentState = currentState.Id;
//                state.InstanceId = instanceId;
//                state.StateId = stateActivity.Attribute("Name").Value;
//                state.TransactUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                state.CreateTime = DateTime.Now;
//                state.State = 1;
//                state.SubmitUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                state.SubmitOption = stateActivity.Attribute("SubmitOption").Value;
//                state.SubmitResult = string.Empty;
//             //   state.NextUser = currentState.NextUser;
//
//
//                //如果流程表中，已经流经过该部门了，则直接送给曾经处理过该合同的人员。
//                var workFlowObj = ctx.V_Record.Where(p => p.InstanceId == instanceId).Where(p => p.StateId.Contains(stateActivity.Attribute("Name").Value.Substring(0,3))).OrderByDescending(p => p.Id);
//                //V_Record vRecord = workFlowObj.First();
//                if (workFlowObj.Count()>0 )
//                {
//                    V_Record vRecord = workFlowObj.First();
//                    state.NextUser = vRecord.NextUser;
//                    SetWorkFlowMessage(vRecord.ElevatorType, submitStatus, null, vRecord.NextUser);
//                }
//                else
//                {
//                    state.NextUser = nextUser;
//                    //string specificType = ctx.V_Record.Where(p => p.InstanceId == instanceId).Distinct().SingleOrDefault().SpecificType;
//                    V_Record record = ctx.V_Record.Where(p => p.InstanceId == instanceId && p.SubmitResult == "启动").SingleOrDefault();
//                    string specificType = record != null ? record.ElevatorType : "AEP-C";
//                    //specificType = string.IsNullOrEmpty(specificType) ? "AEP-C" : specificType;
//                    SetWorkFlowMessage(specificType, submitStatus, "notEmpty", nextUser);
//                }
//                ctx.InstanceState.InsertOnSubmit(state);
//                Console.WriteLine("创建\"" + state.StateId + "\"节点成功！");
//
//                ctx.SubmitChanges();
//            }
//           
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.CreateState", ex.Message, ex.StackTrace);
//        }
//        return state;
//    }
//
//     private void SetWorkFlowMessage(string elevatorType, string submitState, string nextDuty, string nextUserId) {
//         //nextDuty = string.IsNullOrEmpty(nextDuty) ? null : nextDuty;
//         V_User userInfo = HttpContext.Current.Session["UserInfo"] as V_User;
//         if (!string.IsNullOrEmpty(nextDuty))
//         {
//             if (userInfo.DutyId == "Eastern_Saler")
//                 nextDuty = "Eastern_SaleSupport";
//             else if (userInfo.DutyId == "Eastern_SaleSupport")
//                 nextDuty = "Eastern_Technical";
//         }
//         LDSService LDSservice = new LDSService();
//         switch (submitState)
//         {
//             case "2":
//             case "4":
//                 LDSservice.SetMessage(elevatorType, "审核", nextDuty, nextUserId, userInfo.UserName);
//                 break;
//             case "3":
//                 LDSservice.SetMessage(elevatorType, "驳回", null, nextUserId, userInfo.UserName);
//                 break;
//             //case "4":
//             //    LDSservice.SetMessage(elevatorType, "审核", nextDuty, nextUserId, userInfo.UserName);
//             //    break;
//         }
//     }
//
//    /// <summary>
//    /// 流程节点路由器
//    /// </summary>
//    /// <param name="currentState">当前流程节点</param>
//    private void ActivityRouter(InstanceState currentState)
//    {
//        try
//        {
//            var xdoc = GetXDocument(currentState.InstanceId);
//            if (xdoc != null)
//            {
//                var currentActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == currentState.StateId).SingleOrDefault();
//                if (currentActivity != null)
//                {
//                    var targetActivity = currentActivity.Elements().FirstOrDefault();
//                    if (targetActivity != null)
//                    {
//                        if (targetActivity.Name == "SetStateActivity")
//                            SetStateActivityHandler(currentState, xdoc, targetActivity);
//                        else if (targetActivity.Name == "ConditionActivity")
//                            ConditionActivityHandler(currentState, xdoc, targetActivity);
//                    }
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.ActivityRouter", ex.Message, ex.StackTrace);
//        }
//    }
//
//    /// <summary>
//    /// SetStateActivity处理器
//    /// </summary>
//    /// <param name="currentState">当前节点</param>
//    /// <param name="xdoc">流程实例模板</param>
//    /// <param name="targetActivity">任务节点模板</param>
//    private void SetStateActivityHandler(InstanceState currentState, XDocument xdoc, XElement targetActivity)
//    {
//        try
//        {
//            var nextStateActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == targetActivity.Attribute("TargetStateName").Value).SingleOrDefault();
//            if (nextStateActivity != null)
//                CreateState(currentState.InstanceId, nextStateActivity, currentState,"0","","");
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.SetStateActivityHandler", ex.Message, ex.StackTrace);
//        }
//
//    }
//
//    /// <summary>
//    /// ConditionActivity处理器
//    /// </summary>
//    /// <param name="currentState">当前节点</param>
//    /// <param name="xdoc">流程实例模板</param>
//    /// <param name="targetActivity">任务节点模板</param>
//    private void ConditionActivityHandler(InstanceState currentState, XDocument xdoc, XElement targetActivity)
//    {
//        try
//        {
//            var submitCondition = targetActivity.Elements("ConditionItemActivity").Where(p => p.Attribute("条件").Value == currentState.SubmitResult).SingleOrDefault();
//            if (submitCondition != null)
//            {
//                var branchTargetActivity = submitCondition.Elements().FirstOrDefault();
//                if (branchTargetActivity != null)
//                {
//                    var branchTargetStateName = branchTargetActivity.Attribute("TargetStateName").Value;
//                    var nextStateActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == branchTargetStateName).SingleOrDefault();
//                    if (nextStateActivity != null)
//                        CreateState(currentState.InstanceId, nextStateActivity, currentState,"0","","");
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.ConditionActivityHandler", ex.Message, ex.StackTrace);
//        }
//    }
//
//    /// <summary>
//    /// 得到流程实例模板
//    /// </summary>
//    /// <param name="instanceId">流程编号</param>
//    /// <returns>流程实例模板</returns>
//    private XDocument GetXDocument(int instanceId)
//    {
//        try
//        {
//            var instance = ctx.Instance.Where(p => p.Id == instanceId).SingleOrDefault();
//            if (instance == null)
//                return null;
//            else
//            {
//                var templateXmlString = instance.TemplateXmlString;
//                return XDocument.Parse(templateXmlString);
//            }
//        }
//        catch (Exception ex)
//        {
//            InserError("WFService.GetXDocument", ex.Message, ex.StackTrace);
//            return null;
//        }
//    }
//
//    /// <summary>
//    /// 插入错误信息
//    /// </summary>
//    /// <param name="errorMethod">错误方法</param>
//    /// <param name="errorMessage">错误信息</param>
//    /// <param name="errorStackTrace">错误堆栈</param>
//    private void InserError(string errorMethod, string errorMessage, string errorStackTrace)
//    {
//        try
//        {
//            var error = new Error() { ErrorMethod = errorMethod, ErrorMessage = errorMessage, ErrorStackTrace = errorStackTrace };
//            ctx.Error.InsertOnSubmit(error);
//            ctx.SubmitChanges();
//        }
//        catch (Exception)
//        {
//        }
//    }
//
//    /// <summary>
//    /// 删除流程节点
//    /// </summary>
//    /// <param name="Id">流程Id</param>
//     public void DelInstanceById(int Id) {
//         Instance instance = GetInstance(Id);
//         if (instance != null) {
//             ctx.Instance.DeleteOnSubmit(instance);
//             ctx.SubmitChanges();
//         }
//     }
//
//    /// <summary>
//    /// 删除流程状态表
//    /// </summary>
//    /// <param name="instanceId">流程Id</param>
//     public void DelInstanceStateByInstanceId(int instanceId) {
//         List<InstanceState> instanceState = GetInstanceStateList(instanceId);
//         ctx.InstanceState.DeleteAllOnSubmit(instanceState);
//         ctx.SubmitChanges();
//     }
//
//     //用于处理撤销领取图纸（非标B）
//     public InstanceState CreateNextStateCancelReceive(int instanceId, InstanceState startState, string submitStatus, string DrawingPicStatus, string nextUser)
//     {
//         InstanceState nexState = null;
//         var templateXmlString = ctx.Instance.Where(p => p.Id == instanceId).Single().TemplateXmlString;
//         XDocument xdoc = XDocument.Parse(templateXmlString);
//         var startActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == startState.StateId).SingleOrDefault();
//         // 下一办理节点
//         var targetActivity = startActivity.Elements().FirstOrDefault();
//         if (targetActivity != null)
//         {
//             //  var targetStateName = targetActivity.Attribute("TargetStateName").Value;
//             //  var targetStateName = targetActivity.Elements("ConditionItemActivity").Where(p => p.Attribute("Name").Value == "setStateActivity"+status).SingleOrDefault().Attribute("TargetStateName").Value;
//
//             var targetStateName = targetActivity.Elements("ConditionItemActivity").Elements("SetStateActivity").Where(p => p.Attribute("Name").Value == "setStateActivity" + submitStatus).SingleOrDefault().Attribute("TargetStateName").Value;
//             var nextStateActivity = xdoc.Root.Elements("StateActivity").Where(p => p.Attribute("Name").Value == targetStateName).SingleOrDefault();
//
//             // 创建下一办理节点
//             nexState = CreateStateCancelReceive(instanceId, nextStateActivity, startState, submitStatus, DrawingPicStatus, nextUser);
//         }
//         return nexState;
//     }
//
//     /// <summary>
//     /// 创建节点(用于撤销领取图纸的特殊流程)
//     /// </summary>
//     /// <param name="instanceId">流程实例编号</param>
//     /// <param name="stateActivity">当前节点模板</param>
//     private InstanceState CreateStateCancelReceive(int instanceId, XElement stateActivity, InstanceState currentState, string submitStatus, string DrawingPicStatus, string nextUser)
//     {
//         InstanceState state = new InstanceState();
//         try
//         {
//             if ((HttpContext.Current.Session["UserInfo"] as V_User).DeptName == "销售支持" && submitStatus == "2" && DrawingPicStatus == "方 案")
//             {
//                 stateActivity.Attribute("Name").Value = "结束状态";  //销售支持 ，且是标准图纸通过的时候。
//             }
//             if (stateActivity.Attribute("Name").Value == "结束状态")
//             {
//                 Console.WriteLine("流程已到结束状态！");
//                 var instance = ctx.Instance.SingleOrDefault(p => p.Id == instanceId);
//                 if (instance != null)
//                 {
//                     instance.ComplateTime = DateTime.Now;
//                     instance.State = 2;
//                 }
//                 state.StateId = "结束状态";
//                 state.TransactUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//             }
//             else
//             {
//                 state = new InstanceState();
//                 state.ParentState = currentState.Id;
//                 state.InstanceId = instanceId;
//                 state.StateId = stateActivity.Attribute("Name").Value;
//                 state.TransactUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                 state.CreateTime = DateTime.Now;
//                 state.State = 1;
//                 state.SubmitUser = (HttpContext.Current.Session["UserInfo"] as V_User).UserId;
//                 state.SubmitOption = stateActivity.Attribute("SubmitOption").Value;
//                 state.SubmitResult = string.Empty;
//                 //   state.NextUser = currentState.NextUser;
//
//
//                 //如果流程表中，已经流经过该部门了，则直接送给曾经处理过该合同的人员。
//                 var workFlowObj = ctx.V_Record.Where(p => p.InstanceId == instanceId).Where(p => p.StateId.Contains(stateActivity.Attribute("Name").Value.Substring(0, 3))).OrderByDescending(p => p.Id);
//                 //V_Record vRecord = workFlowObj.First();
//                 if (workFlowObj.Count() > 0)
//                 {
//                     V_Record vRecord = workFlowObj.First();
//                     state.NextUser = vRecord.NextUser;
//                 }
//                 else
//                 {
//                     state.NextUser = nextUser;
//                     //string specificType = ctx.V_Record.Where(p => p.InstanceId == instanceId).Distinct().SingleOrDefault().SpecificType;
//                     V_Record record = ctx.V_Record.Where(p => p.InstanceId == instanceId && p.SubmitResult == "启动").SingleOrDefault();
//                     string specificType = record != null ? record.ElevatorType : "AEP-C";
//                     //specificType = string.IsNullOrEmpty(specificType) ? "AEP-C" : specificType;
//                 }
//                 ctx.InstanceState.InsertOnSubmit(state);
//                 Console.WriteLine("创建\"" + state.StateId + "\"节点成功！");
//
//                 ctx.SubmitChanges();
//             }
//
//         }
//         catch (Exception ex)
//         {
//             InserError("WFService.CreateState", ex.Message, ex.StackTrace);
//         }
//         return state;
//     }
}