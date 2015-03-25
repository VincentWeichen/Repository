package supton.entity.pseudo;

import java.util.List;

import supton.entity.Organization;
import supton.entity.User;



public class UserInfomation implements java.io.Serializable {

	// Fields
	private Integer sn;
	private Organization organization;
	private List<User> userList;
	private Integer countTotal;

	// Constructors

	/** default constructor */
	public UserInfomation() {
	}

	/** full constructor */
	public UserInfomation(Integer sn,Organization organization, List<User> userList, Integer countTotal) {
		this.sn = sn;
		this.organization = organization;
		this.userList = userList;
		this.countTotal = countTotal;
	}

	public synchronized final Integer getSn() {
		return sn;
	}
	public synchronized final void setSn(Integer sn) {
		this.sn = sn;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public synchronized final List<User> getUserList() {
		return userList;
	}
	
	public synchronized final void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public synchronized final Integer getCountTotal() {
		return countTotal;
	}

	public synchronized final void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
	}
}