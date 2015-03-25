package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IControlltimedetailDao;
import supton.entity.Controlltimedetail;

@Service
public class ControlltimedetailServiceImpl implements IControlltimedetailService {

	@Autowired
	private IControlltimedetailDao controlltimedetailDao;

	public void save(Controlltimedetail transientInstance) {
		controlltimedetailDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Controlltimedetail persistentInstance) {
		controlltimedetailDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Controlltimedetail
	 */
	public Controlltimedetail findById(Integer id) {
		return controlltimedetailDao.findById(id);
	}
	
	public List<Controlltimedetail> findByOrganizationcode(String organizationcode)
	{
		return controlltimedetailDao.findByOrganizationcode(organizationcode);	
	}
	 
	 public List<Controlltimedetail> findByType(String type)
	 {
		 return controlltimedetailDao.findByType(type);	
	 }
	 
	 public int delete(Integer id)
	 {
		 return controlltimedetailDao.delete(id);	
	 }
	 
	 public List<Controlltimedetail> findByOrganizationcode(String organizationcode,String type)
	 {
		 return controlltimedetailDao.findByOrganizationcode(organizationcode,type);	
	 }
	 
	 public int deleteByControlltimeercisedateId(Integer controlltimeercisedateid)
	 {
		 return controlltimedetailDao.deleteByControlltimeercisedateId(controlltimeercisedateid);
	 }
	 
	 public int deleteByControlltimeId(Integer controlltimeid)
	 {
		 return controlltimedetailDao.deleteByControlltimeId(controlltimeid);
	 }
}