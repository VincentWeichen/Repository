package supton.service;

import java.util.List;

import supton.entity.Bulletin;


public interface IBulletinService {  

	 public void save(Bulletin transientInstance);
      
	 public void delete(Bulletin persistentInstance);
      
	 public Bulletin findById(Integer id);
	 
	 
}  