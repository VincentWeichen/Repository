package supton.service;

import java.util.List;

import supton.entity.Fingerprintuser;


public interface IFingerprintUserService {  

	 public void save(Fingerprintuser transientInstance);
      
	 public void delete(Fingerprintuser persistentInstance);
      
	 public Fingerprintuser findById(Integer id);
	 
	 public List<Fingerprintuser> findByTID(String tid);
	 
	 public List<Fingerprintuser> findAllByTId(String tid);
	 
	 public Fingerprintuser getFingerprintuserByTUserCode(String tusercode,String tid);
}  