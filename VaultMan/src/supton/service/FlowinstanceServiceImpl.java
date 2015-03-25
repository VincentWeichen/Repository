package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IFlowinstanceDao;
import supton.dao.IRoleDao;
import supton.entity.Flowinstance;
import supton.entity.PageInfo;
import supton.entity.Role;

@Service
public class FlowinstanceServiceImpl implements IFlowinstanceService {

	@Autowired
	private IFlowinstanceDao flowinstancedao;

	public void save(Flowinstance transientInstance) {
		flowinstancedao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Flowinstance persistentInstance) {
		flowinstancedao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public Flowinstance findById(Integer id) {
		return flowinstancedao.findById(id);
	}
	
	
}