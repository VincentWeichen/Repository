package supton.dao;


import java.util.List;

import supton.entity.Controlltimedetail;
import supton.entity.Role;

public interface IControlltimedetailDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Controlltimedetail transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Controlltimedetail persistentInstance);
      
    /** 
     * 添加数据 
     * @param Controlltimedetail 
     */  
	 public Controlltimedetail findById(Integer id);
	 
	 public List<Controlltimedetail> findByOrganizationcode(String organizationcode);
	 
	 public List<Controlltimedetail> findByType(String type);
	 
	 public int delete(Integer id);
	 
	 public List<Controlltimedetail> findByOrganizationcode(String organizationcode,String type);
	 
	 public int deleteByControlltimeercisedateId(Integer controlltimeercisedateid);
	 
	 public int deleteByControlltimeId(Integer controlltimeid);
}
