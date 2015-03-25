package supton.service;

import java.util.List;

import supton.entity.Flowinstance;
import supton.entity.PageInfo;
import supton.entity.Role;


public interface IFlowinstanceService {  
	public void save(Flowinstance transientInstance);
	public void delete(Flowinstance persistentInstance);
	public Flowinstance findById(Integer id);
}  