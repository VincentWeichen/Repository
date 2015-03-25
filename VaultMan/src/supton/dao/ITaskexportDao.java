package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Organization;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Taskexport;
import supton.entity.Terminal;
import supton.entity.User;

/**
 	* A data access object (DAO) providing persistence and search support for Role entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Role
  * @author MyEclipse Persistence Tools 
 */
public interface ITaskexportDao {
	/** 
     * 查看条数 
     * @return 
     */  
	 public void save(Taskexport transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Taskexport persistentInstance);
      
  
	 public Taskexport findById(Integer id);
	 
}