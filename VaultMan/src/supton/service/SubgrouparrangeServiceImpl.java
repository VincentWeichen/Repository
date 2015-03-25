package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.ISubgrouparrangeDao;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;

@Service
public class SubgrouparrangeServiceImpl implements ISubgrouparrangeService {

	@Autowired
	private ISubgrouparrangeDao subgrouparrangeDao;

	public void save(Subgrouparrange transientInstance) {
		subgrouparrangeDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Subgrouparrange persistentInstance) {
		subgrouparrangeDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Subgrouparrange
	 */
	public Subgrouparrange findById(Integer id) {
		return subgrouparrangeDao.findById(id);
	}
	
	public int deleteByOrganizationcode(String organizationcode,String type)
	{
		return subgrouparrangeDao.deleteByOrganizationcode(organizationcode,type);
	}
	 
	public List<Subgrouparrange> findByOrganizationcode(String organizationcode)
	{
		return subgrouparrangeDao.findByOrganizationcode(organizationcode);
	}
	
	public Taskexport findTaskExport(String tid, Integer typeid) {
		return subgrouparrangeDao.findTaskExport(tid, typeid);
	}
	
	public void SetTaskExport(Taskexport taskexport) {
		subgrouparrangeDao.SetTaskExport(taskexport);
	}
}