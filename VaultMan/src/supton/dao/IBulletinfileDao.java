package supton.dao;


import java.util.List;

import supton.entity.Bulletinfile;
import supton.entity.Role;

public interface IBulletinfileDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Bulletinfile transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Bulletinfile persistentInstance);
      
    /** 
     * 添加数据 
     * @param Bulletinfile 
     */  
	 public Bulletinfile findById(Integer id);
	 
	 public List<Bulletinfile> findAll();
}
