package supton.dao;

import java.util.List;

import supton.entity.User;


public interface IUserDao {
    /** 
     * 查看条数 
     * @return 
     */  
    public int lookUser();  
      
    /** 
     * 删除表数据 
     * @return 
     */  
    public int deleteUser(int id);  
      
    /** 
     * 添加数据 
     * @param user 
     */  
    public void saveUser(User user);  
    
    public List<User> getUserLists();
    
    public User validateLogin(String userName, String userPassword);
    
    public int deleteUser(String code);
    
    public List<User> findByCode(String code);
}
