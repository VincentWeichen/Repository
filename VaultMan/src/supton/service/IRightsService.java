package supton.service;

import java.util.List;

import supton.entity.Rights;


public interface IRightsService {  
	public void save(Rights transientInstance);
	public void delete(Rights persistentInstance);
	public Rights findById(Integer id);
	public List<Rights> findAll();
}  