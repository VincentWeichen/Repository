package supton.dao;


import java.util.List;

import supton.entity.Bulletin;
import supton.entity.Controlltimeercisedate;
import supton.entity.Role;

public interface IControlltimeercisedateDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Controlltimeercisedate transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Controlltimeercisedate persistentInstance);
      
    /** 
     * 添加数据 
     * @param Controlltimeercisedate 
     */  
	 public Controlltimeercisedate findById(Integer id);
	 
	 public List<Controlltimeercisedate> findByControlltimedetailid(Integer controlltimedetailid);
	 
	 public List<Controlltimeercisedate> findByOrganizationcode(String organizationcode);

	 public int deleteByOrganizationcode(String organizationcode,String type,Integer controlltimeid);
	 
	 public int delete(Integer id);
	 
	 public int deleteByControlltimeId(Integer controlltimeid);
}
