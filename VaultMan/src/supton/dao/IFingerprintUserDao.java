package supton.dao;


import java.util.List;

import supton.entity.Fingerprintuser;
import supton.entity.Role;

public interface IFingerprintUserDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Fingerprintuser transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Fingerprintuser persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Fingerprintuser findById(Integer id);
	 
	 public List<Fingerprintuser> findByTID(String tid);

	public List<Fingerprintuser> findAllByTId(String tid);
	
	public Fingerprintuser getFingerprintuserByTUserCode(String tusercode,String tid);
}
