package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IFlowinstancenodeDao;
import supton.entity.Flowinstancenode;
import supton.entity.PageInfo;


@Service
public class FlowinstancenodeServiceImpl implements IFlowinstancenodeService {

	@Autowired
	private IFlowinstancenodeDao flowinstancenodedao;

	public void save(Flowinstancenode transientinstancenode) {
		flowinstancenodedao.save(transientinstancenode);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Flowinstancenode persistentinstancenode) {
		flowinstancenodedao.delete(persistentinstancenode);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public Flowinstancenode findById(Integer id) {
		return flowinstancenodedao.findById(id);
	}
	
}