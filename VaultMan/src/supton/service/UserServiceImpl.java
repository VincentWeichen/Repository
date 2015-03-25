package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IUserDao;
import supton.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userdao;

	public int lookUser() {
		return userdao.lookUser();
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public int deleteUser(int id) {
		return userdao.deleteUser(id);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		userdao.saveUser(user);
	}
	
	public List<User> getUserLists() {
		return userdao.getUserLists();
	}
	
	public User validateLogin(String userName, String userPassword) {
		return userdao.validateLogin(userName, userPassword);
	}
	
	public int deleteUser(String code)
	{
		return userdao.deleteUser(code);
	}
	
	public List<User> findByCode(String code)
	{
		return userdao.findByCode(code);
	}
}