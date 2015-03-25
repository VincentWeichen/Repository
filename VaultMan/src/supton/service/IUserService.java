package supton.service;


import java.util.List;

import supton.entity.User;


public interface IUserService {  
    public int lookUser();
    public int deleteUser(int id);  
    public void saveUser(User user);
	public List<User> getUserLists();
	public User validateLogin(String userName, String userPassword);
	public int deleteUser(String code);
	public List<User> findByCode(String code);
}  