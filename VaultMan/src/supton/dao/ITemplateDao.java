package supton.dao;


import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Template;

public interface ITemplateDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Template transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Template persistentInstance);
      
    /** 
     * 添加数据 
     * @param Template 
     */  
	 public Template findById(Integer id);
	 
	 /** 
	  * 分页查询
	  * @param List<Template> 
	  */
	 public PageInfo PagedQuery(int pageNo, int pageSize);
	 
	 public List<Template> findByTemplateId(Object templateId);
}
