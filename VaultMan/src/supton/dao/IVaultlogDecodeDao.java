package supton.dao;


import java.util.List;

import supton.entity.VaultlogDecode;

public interface IVaultlogDecodeDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(VaultlogDecode transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(VaultlogDecode persistentInstance);
      
    /** 
     * 添加数据 
     * @param VaultlogDecode 
     */  
	 public VaultlogDecode findById(Integer id);
	 
	 public List<VaultlogDecode> findAll();
	 
	 public List<VaultlogDecode> findByUsercode(String usercode);
}
