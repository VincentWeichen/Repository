package supton.dao;


import supton.entity.Operationlog;

public interface IOperationlogDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Operationlog transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Operationlog persistentInstance);
      
    /** 
     * 添加数据 
     * @param Operationlog 
     */  
	 public Operationlog findById(Integer id);
}
