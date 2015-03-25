package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IControlltimeercisedateDao;
import supton.entity.Controlltimeercisedate;

@Service
public class ControlltimeercisedateServiceImpl implements IControlltimeercisedateService {

	@Autowired
	private IControlltimeercisedateDao controlltimeercisedateDao;

	public void save(Controlltimeercisedate transientInstance) {
		controlltimeercisedateDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Controlltimeercisedate persistentInstance) {
		controlltimeercisedateDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Controlltimeercisedate
	 */
	public Controlltimeercisedate findById(Integer id) {
		return controlltimeercisedateDao.findById(id);
	}
	
	public List<Controlltimeercisedate> findByControlltimedetailid(Integer controlltimedetailid)
	{
		return controlltimeercisedateDao.findByControlltimedetailid(controlltimedetailid);
	}
	 
	 public List<Controlltimeercisedate> findByOrganizationcode(String organizationcode)
	 {
		 return controlltimeercisedateDao.findByOrganizationcode(organizationcode);
	 }
	 
	 public int deleteByOrganizationcode(String organizationcode,String type,Integer controlltimeid)
	 {
		 return controlltimeercisedateDao.deleteByOrganizationcode(organizationcode,type,controlltimeid);
	 }
	 
	 public int delete(Integer id)
	 {
		 return controlltimeercisedateDao.delete(id);
	 }
	 
	 public int deleteByControlltimeId(Integer controlltimeid)
	 {
		 return controlltimeercisedateDao.delete(controlltimeid);
	 }
}