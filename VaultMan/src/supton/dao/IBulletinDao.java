package supton.dao;


import supton.entity.Bulletin;
import supton.entity.Role;

public interface IBulletinDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Bulletin transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Bulletin persistentInstance);
      
    /** 
     * 添加数据 
     * @param Bulletin 
     */  
	 public Bulletin findById(Integer id);
}
