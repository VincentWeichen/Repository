package supton.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;

import supton.entity.Bulletin;
import supton.entity.Bulletinfile;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.User;
import supton.entity.pseudo.UserInfomation;
import supton.service.IBulletinService;
import supton.service.IBulletinfileService;
import supton.service.IOrganiztionService;
import supton.service.IOrganiztionUserService;
import supton.service.IRoleService;
import supton.service.IUserService;
import supton.tools.CommonController;
import supton.tools.JsonTool;

@Controller
public class BulletinAction {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrganiztionService organiztionService;
	
	@Autowired
	private IOrganiztionUserService organiztionUserService;
	
	@Autowired
	private IBulletinService bulletinService;
	
	@Autowired
	private IBulletinfileService bulletinfileService;
	
	@RequestMapping("/GetBulletinList")
	public void   GetBulletinList(HttpServletRequest request,HttpServletResponse response) {

		List<Bulletinfile> bulletinfileList = bulletinfileService.findAll();
		request.setAttribute("bulletinfileList",bulletinfileList);
		try {
			request.getRequestDispatcher("/WEB-INF/view/Bulletin.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/DeleteBulletin")
	public void   DeleteBulletin(String roleCode,HttpServletRequest request,HttpServletResponse response) {
		int userNum = roleService.deleteRole(roleCode);
		//删除关联表
		
		GetBulletinList(request,response);
	}
	
	@RequestMapping("/DeleteBulletinList")
	public void   DeleteBulletinList(HttpServletRequest request,HttpServletResponse response) {
		// 新建人员，测试
		String[] codes = request.getParameterValues("roleCodeStr");
		String loginMessage = "";
		for(int i=0;i<codes.length;i++)
		{
			int userNum = roleService.deleteRole(codes[i]);
			//删除关联的表信息
		}
		GetBulletinList(request,response);
	}
	
	
	@RequestMapping("/SaveBulletin")
	public void SaveBulletin(HttpServletRequest request,HttpServletResponse response,Bulletin bulletin) {
		
		bulletinService.save(bulletin);
		//这里需要添加角色信息，并且保存到数据库中

		GetBulletinList(request,response);
		
	}

	@ResponseBody
	@RequestMapping(value="EditBulletin",produces={"application/json;charset=UTF-8"})
	public String EditBulletin(HttpServletRequest request) {
		String roleCode = request.getParameter("roleCode");
		List<Role> roleList = roleService.findByCode(roleCode);
		Role role = roleList.stream().findFirst().orElse(null);
		String roleJson = JsonTool.objectToJsonString(role);
		return roleJson;
	}
	@RequestMapping("/UploadFileBullet")
	public void UploadFileBullet(HttpServletRequest request,HttpServletResponse response) {
		CommonController commonController = new CommonController();
		try {
			String bulletinsn = request.getParameter("sn");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String releasetime = request.getParameter("releasetime");
			Bulletin bulletin = new Bulletin();
			if(bulletinsn != null && !bulletinsn.isEmpty())
			{
				bulletin.setSn(Integer.parseInt(bulletinsn));
			}	
			bulletin.setTitle(title);
			bulletin.setContent(content);
//			bulletin.setReleasetime(releasetime);
			bulletinService.save(bulletin);
			
			String linkFile = commonController.doFileUpload(request,response);
			String[] files = linkFile.split(",");
			Bulletinfile bulletinfile = new Bulletinfile();
			
			if(bulletin.getSn() != null)
			{
				bulletinsn = bulletin.getSn().toString();
			}
			if(bulletinsn != null && !bulletinsn.isEmpty() & files.length>0)
			{
				bulletinfile.setBulletinsn(Integer.parseInt(bulletinsn));
				bulletinfile.setFilepath(files[0]);
				bulletinfile.setFilename(files[1]);
				bulletinfile.setFilealias(files[2]);
				bulletinfile.setFiletsize(files[3]);
				bulletinfileService.save(bulletinfile);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GetBulletinList(request,response);
	}

	@RequestMapping("/DownFile")
	public void DownFile(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Bulletinfile bulletinfile = bulletinfileService.findById(Integer
				.parseInt(id));
		try {
			String pathsavefile = bulletinfile.getFilepath();
			String fileName = bulletinfile.getFilename();
			response.reset();
			response.setContentType("APPLICATION/OCTET-STREAM");
			/*
			 * 要显示到客户端的文件名转码是必需的，特别是中文名，
			 * 
			 * 否则可能出现文件名乱码甚至是浏览器显示无法下载的问题
			 */
			fileName = response.encodeURL(new String(fileName.getBytes(),
					"ISO8859_1"));// 转码
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			ServletOutputStream out = response.getOutputStream();
			InputStream inStream = new FileInputStream(pathsavefile);
			// 循环取出流中的数据
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) > 0)
				out.write(b, 0, len);
			response.setStatus(response.SC_OK);
			response.flushBuffer();
			out.close();
			inStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}