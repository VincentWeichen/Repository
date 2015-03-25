package supton.service;

import java.util.List;

import supton.entity.Flowinstancenode;
import supton.entity.PageInfo;
import supton.entity.Role;


public interface IFlowinstancenodeService {  
	public void save(Flowinstancenode transientInstance);
	public void delete(Flowinstancenode persistentInstance);
	public Flowinstancenode findById(Integer id);
}  