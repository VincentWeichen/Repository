package supton.action;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import supton.entity.Organization;
import supton.entity.pseudo.OrganizationTree;
import supton.entity.pseudo.UserInfomation;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.tools.JsonTool;

@Controller
public class OrganizationAction {

	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@ResponseBody
	@RequestMapping(value="getOrganizationTrees",produces={"application/json;charset=UTF-8"})
	//public String submitLogin(@RequestBody String params)
	public String getOrganizationTrees(HttpServletRequest request)
	{
		
		//String returnString = loginUserInfo != null ? "{\"returnVal\":\"True\"}" : "{\"returnVal\":\"False\"}";
		return GetOrganizationTree(request);
	}

	@RequestMapping("/OrganizationTree")
	public String GetOrganizationTree(HttpServletRequest request)
	{
		String organizationCode = (String) (request.getSession().getAttribute("organizationCode"));
		List<OrganizationTree> organizationTreeList = new ArrayList<OrganizationTree>();
		OrganizationTree organizationTree = new OrganizationTree();
		List<Organization> organizationList = organiztionService.findAll();
		Organization organizationFirst  = organizationList.stream().filter(p -> p.getParentcode() == null || p.getParentcode().isEmpty()).findFirst().orElse(null);
		if(organizationCode != null && !organizationCode.isEmpty())
		{
			organizationFirst  = organizationList.stream().filter(p -> p.getCode().equals(organizationCode)).findFirst().orElse(null);
		}
		organizationTree.setNodeId(organizationFirst.getCode());
		organizationTree.setNodeName(organizationFirst.getName());
		organizationTree.setNodeTId(organizationFirst.getTid());
		organizationTree.setNodeOrgType(organizationFirst.getType());
		organizationTree.setChildrenTreeList(GetOrganizationchildrenTree(organizationList,organizationFirst.getCode()));
		organizationTreeList.add(organizationTree);
		//String jsonTree = JsonTool.objectToJsonString(organizationTree);
		String jsonTree = JsonTool.listTojsonString(organizationTreeList);
		return jsonTree;
	}

	
	public List<OrganizationTree> GetOrganizationchildrenTree(List<Organization> organizationList,String Parentcode)
	{
		
		List<OrganizationTree> OrganizationchildrenTreeList = new ArrayList<OrganizationTree>();
//		List<Organization> organizationChildrenList = organizationList.parallelStream().filter(p ->p.getParentcode() == Parentcode).collect(Collectors.toList());
		List<Organization> organizationChildrenList = organiztionService.findByParentcode(Parentcode);
		for(Organization organization : organizationChildrenList)
		{
			OrganizationTree organizationTree = new OrganizationTree();
			organizationTree.setNodeId(organization.getCode());
			organizationTree.setNodeName(organization.getName());
			organizationTree.setNodeTId(organization.getTid());
			organizationTree.setNodeOrgType(organization.getType());
			organizationTree.setChildrenTreeList(GetOrganizationchildrenTree(organizationList,organization.getCode()));
			OrganizationchildrenTreeList.add(organizationTree);
		}
		return OrganizationchildrenTreeList;
	}
	
	public String checkIsDelete(String organizationCode) {
			String errorMessage = "";
			List<Organization>  organizationList = organiztionService.getOrganizationListByCode(organizationCode);
			UserInfomation userInfomation= organiztionUserService.getUserInfomationByOrCode(organizationCode);
			//boolean flag = true;
			if(organizationList != null && organizationList.size() > 0)
			{
				errorMessage = "当该机构下还有子机构，无法删除！";
				//flag = false;
			}
			if(userInfomation != null && userInfomation.getUserList().size() > 0)
			{
				errorMessage = "该机构下还有人员，无法删除！";
				//flag = false;
			}
			//return flag;
			return errorMessage;
    } 
	
	@RequestMapping("/Organization")
	public String Organization(Model model, @RequestParam("organizationCode") String organizationCode) {
		Organization organization = new Organization();
		
		if (!organizationCode.isEmpty())
			organization = organiztionService.getOrganizationByCode(organizationCode);
		
		model.addAttribute("OrganizationInfo", organization);
		return "Organization";
		
	}
	
	@ResponseBody
	@RequestMapping(value="getOrganizationByCode",produces={"application/json;charset=UTF-8"})
	public String getOrganizationByCode(@RequestParam("code") String code)
	{
		
		Organization organization = new Organization();
		organization = organiztionService.getOrganizationByCode(code);
		
		if (organization == null)
			return "{\"returnVal\":\"null\"}";
		else
		//String returnString = loginUserInfo != null ? "{\"returnVal\":\"True\"}" : "{\"returnVal\":\"False\"}";
			return JsonTool.objectToJsonString(organization);
	}
	
	@ResponseBody
	@RequestMapping(value="organizationSave",produces={"application/json;charset=UTF-8"})
	public String organizationSave(HttpServletRequest request)
	{
		String returnString = "";
		
		Organization organization = null;
		if (request.getParameter("id") != "")
		{
			organization = organiztionService.getOrganizationById(Integer.parseInt(request.getParameter("id")));
		}
		else
		{
			organization = new Organization();
		}
		organization.setCode(request.getParameter("code"));
		organization.setName(request.getParameter("name"));
		organization.setParentcode(request.getParameter("parentcode"));
		organization.setAlias(request.getParameter("alias"));
		organization.setAddress(request.getParameter("address"));
		organization.setTelephone(request.getParameter("telephone"));
		organization.setManager(request.getParameter("manager"));
		organization.setCellphone(request.getParameter("cellphone"));
		organization.setTid(request.getParameter("tid"));
		int typeVal = Integer.valueOf(request.getParameter("type"));
		organization.setType(typeVal);
		
		//Organization organization = new Organization();
		//organization = organiztionService.getOrganizationByCode(code);
		//String returnString = loginUserInfo != null ? "{\"returnVal\":\"True\"}" : "{\"returnVal\":\"False\"}";
		
		organiztionService.SaveOrganization(organization);
		returnString = "{\"returnVal\":\"True\"}"; 
		
		return returnString;
	}
	
	@ResponseBody
	@RequestMapping(value="organizationDelete",produces={"application/json;charset=UTF-8"})
	public String organizationDelete(@RequestParam("code") String code)
	{
		String returnString = "";
		boolean returnFlg = false;
		String errorMessage = checkIsDelete(code);
		if (errorMessage == "")
		{
			returnFlg = deleteOrganization(code);
			if (!returnFlg)
				errorMessage = "删除失败，请稍后重试！";
		}
		
		returnString = "{\"returnVal\":" + returnFlg + ", \"errorMessage\":\"" + errorMessage + "\"}";
		return returnString;
	}
	
	public boolean deleteOrganization(String organizationCode)
	{
		boolean returnVal = true;
		
		returnVal = organiztionService.deleteOrganization(organizationCode);
		
		return returnVal;
	}
	
	@RequestMapping("/addOrganization")
	public String addOrganization(Model model, @RequestParam("organizationParentcode") String organizationParentcode) {
		Organization organization = new Organization();
		
		organization.setCode("");
		organization.setParentcode(organizationParentcode);
		
		model.addAttribute("OrganizationInfo", organization);
		return "Organization";
		
	}

}