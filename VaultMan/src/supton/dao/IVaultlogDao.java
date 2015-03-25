package supton.dao;


import java.util.List;

import supton.entity.Vaultlog;

public interface IVaultlogDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Vaultlog transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Vaultlog persistentInstance);
      
    /** 
     * 添加数据 
     * @param Vaultlog 
     */  
	 public Vaultlog findById(Integer id);
	 
	 public List<Vaultlog> findByTid(String tid);
	 
	 public List<Vaultlog> findByTidDate(String tid,String nowDate);
	 
	 public List<Vaultlog> findByTidNowDate(String tid,String nowDate);
	 
	 public List<Vaultlog> findByTidLogIndex(String tid,String logIndex,String nowDate);
}
