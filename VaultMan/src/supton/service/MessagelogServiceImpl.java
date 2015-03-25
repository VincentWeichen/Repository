package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IMessagelogDao;
import supton.entity.Messagelog;

@Service
public class MessagelogServiceImpl implements IMessagelogService {

	@Autowired
	private IMessagelogDao messagelogDao;

	public void save(Messagelog transientInstance) {
		messagelogDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Messagelog persistentInstance) {
		messagelogDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Messagelog
	 */
	public Messagelog findById(Integer id) {
		return messagelogDao.findById(id);
	}
	
	 
}