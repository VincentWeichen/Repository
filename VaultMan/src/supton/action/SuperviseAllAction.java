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
import supton.entity.Lookupoption;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Terminal;
import supton.entity.User;
import supton.entity.Vaultlog;
import supton.entity.pseudo.GroupUserInfomation;
import supton.entity.pseudo.OrganizationTree;
import supton.entity.pseudo.UserInfomation;
import supton.service.IFingerprintUserService;
import supton.service.ILookupOptionService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IRoleUserService;
import supton.service.ISubgroupService;
import supton.service.ISubgrouparrangeService;
import supton.service.ITerminalService;
import supton.service.IUserService;
import supton.service.IVaultlogService;
import supton.tools.JsonTool;

@Controller
public class SuperviseAllAction {

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
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private ITerminalService terminalService;
	
	@Autowired
	private IVaultlogService vaultlogService;
	
	@Autowired
	private ILookupOptionService lookupOptionService;
	
	public List<Terminal> terminalList;
	
	@RequestMapping("/GetTerminalAllList")
	public void   GetTerminalAllList(String organizationCode, HttpServletRequest request,HttpServletResponse response) {
		
		GetTerminals(organizationCode);
//		List<Lookupoption> lookupoptionList = lookupOptionService.findByClass_(3);
//		Lookupoption lookupoption = lookupoptionList.stream().findFirst().orElse(null);
//		if(lookupoption != null && lookupoption.getOptionvalue() != null && !lookupoption.getOptionvalue().isEmpty())
//		{
//			terminalList = terminalList.stream().filter(p -> !lookupoption.getOptionvalue().contains(p.getStatus().toString())).collect(Collectors.toList());
//		}
		request.setAttribute("terminalList",terminalList);
		request.setAttribute("organizationCode",organizationCode);
		try {
			request.getRequestDispatcher("/WEB-INF/view/SuperviseAll.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void GetTerminals(String organizationCode)
	{
		terminalList = new ArrayList<Terminal>();
		List<Organization> organizationList = organiztionService.findAll();
		Organization organization  = organizationList.stream().filter(p -> p.getCode().equals(organizationCode)).findFirst().orElse(null);
		if(organization != null && organization.getType()>1 && organization.getTid() != null && !organization.getTid().isEmpty())
		{
			Terminal terminal = terminalService.findById(organization.getTid());
			if (terminal == null)
			{
				terminal = new Terminal();		
				terminal.setOrganizationCode(organization.getCode());
				terminal.setTid(organization.getTid());
				terminal.setStatus(2);
				terminalList.add(terminal);
			}else
			{
				terminalList.add(terminal);
			}
		}
		
		GetTerminaListTree(organizationList,organization.getCode());
	}

	public void GetTerminaListTree(List<Organization> organizationList,String Parentcode)
	{
		List<Organization> organizationChildrenList = organiztionService.findByParentcode(Parentcode);
		for(Organization organization : organizationChildrenList)
		{
			if(organization != null && organization.getType()>1 && organization.getTid() != null && !organization.getTid().isEmpty())
			{
				Terminal terminal = terminalService.findById(organization.getTid());
				if (terminal == null)
				{
					terminal = new Terminal();		
					terminal.setOrganizationCode(organization.getCode());
					terminal.setTid(organization.getTid());
					terminal.setStatus(2);
					terminalList.add(terminal);
				}else
				{
					terminalList.add(terminal);
				}
			}
			GetTerminaListTree(organizationList,organization.getCode());
		}
	}
	
	@ResponseBody
	@RequestMapping(value="GetTerminalAndVaultlogAll",produces={"application/json;charset=UTF-8"})
	public String   GetTerminalAndVaultlogAll(String tid, HttpServletRequest request,HttpServletResponse response) {
		List<Terminal> terminalList = new ArrayList<Terminal>();
		Terminal terminal = new Terminal();
		terminal = terminalService.findById(tid);
		if(terminal == null)
		{
			terminal = new Terminal();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Vaultlog> vaultlogList = vaultlogService.findByTidDate(tid,format.format(new Date()));
		List<Organization> organizationList = organiztionService.findByTid(tid);
		String vaultListStr = JsonTool.listTojsonString(vaultlogList);
		terminal.setOrganizationList(organizationList);
		terminal.setVaultlogList(vaultlogList);
		terminalList.add(terminal);
		String terminalStr="";
		terminalStr = JsonTool.listTojsonString(terminalList);
		return terminalStr;
	}
	
	@ResponseBody
	@RequestMapping(value="GetTerminalAllListJson",produces={"application/json;charset=UTF-8"})
	public String  GetTerminalAllListJson(String organizationCode,HttpServletRequest request,HttpServletResponse response) {
		GetTerminals(organizationCode);
//		List<Lookupoption> lookupoptionList = lookupOptionService.findByClass_(3);
//		Lookupoption lookupoption = lookupoptionList.stream().findFirst().orElse(null);
//		if(lookupoption != null && lookupoption.getOptionvalue() != null && !lookupoption.getOptionvalue().isEmpty())
//		{
		terminalList = terminalList.stream().collect(Collectors.toList());
//		}
		String terminalListStr = "";
		terminalListStr = JsonTool.listTojsonString(terminalList);
		return terminalListStr;
	}
	
	@ResponseBody
	@RequestMapping(value="GetVaultlogListNowDateAll",produces={"application/json;charset=UTF-8"})
	public String   GetVaultlogListNowDateAll(String tid,String logIndex, HttpServletRequest request,HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Vaultlog> vaultlogList = vaultlogService.findByTidLogIndex(tid,logIndex,format.format(new Date()));
		String vaultListStr = "";
		vaultListStr = JsonTool.listTojsonString(vaultlogList);
		return vaultListStr;
	}
	
	
}