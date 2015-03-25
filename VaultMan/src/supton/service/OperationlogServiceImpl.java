package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IOperationlogDao;
import supton.entity.Operationlog;

@Service
public class OperationlogServiceImpl implements IOperationlogService {

	@Autowired
	private IOperationlogDao operationlogDao;

	public void save(Operationlog transientInstance) {
		operationlogDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Operationlog persistentInstance) {
		operationlogDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Operationlog
	 */
	public Operationlog findById(Integer id) {
		return operationlogDao.findById(id);
	}
	
	 
}