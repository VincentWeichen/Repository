package supton.dao;


import supton.entity.Messagelog;
import supton.entity.Role;

public interface IMessagelogDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Messagelog transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Messagelog persistentInstance);
      
    /** 
     * 添加数据 
     * @param Messagelog 
     */  
	 public Messagelog findById(Integer id);
}
