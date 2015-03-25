package supton.entity.pseudo;

import java.util.List;

import supton.entity.Organization;
import supton.entity.Subgroup;
import supton.entity.User;



public class GroupUserInfomation implements java.io.Serializable {

	// Fields
	private Integer sn;
	private List<Subgroup> groupList;
	/**
	 * @return the sn
	 */
	public Integer getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	/**
	 * @return the groupList
	 */
	public List<Subgroup> getGroupList() {
		return groupList;
	}
	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<Subgroup> groupList) {
		this.groupList = groupList;
	}

	
}