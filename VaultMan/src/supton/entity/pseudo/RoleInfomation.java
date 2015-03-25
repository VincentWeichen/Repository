package supton.entity.pseudo;

import java.util.List;

import supton.entity.Exclusionrole;
import supton.entity.User;



/**
 * @author Administrator
 *
 */
public class RoleInfomation implements java.io.Serializable {

	// Fields
	private String roleAllName;
	private String exclusionroleName;
	private Integer sn;
	private Exclusionrole exclusionrole;
	
	/** default constructor */
	public RoleInfomation() {
	}

	/** full constructor */
	public RoleInfomation(String roleAllName,String exclusionroleName, Integer sn,Exclusionrole exclusionrole) {

		this.roleAllName = roleAllName;
		this.exclusionroleName = exclusionroleName;
		this.sn = sn;
		this.exclusionrole = exclusionrole;
	}
	
	/**
	 * @return the roleAllName
	 */
	public String getRoleAllName() {
		return roleAllName;
	}
	/**
	 * @param roleAllName the roleAllName to set
	 */
	public void setRoleAllName(String roleAllName) {
		this.roleAllName = roleAllName;
	}
	/**
	 * @return the exclusionroleName
	 */
	public String getExclusionroleName() {
		return exclusionroleName;
	}
	/**
	 * @param exclusionroleName the exclusionroleName to set
	 */
	public void setExclusionroleName(String exclusionroleName) {
		this.exclusionroleName = exclusionroleName;
	}

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
	 * @return the exclusionrole
	 */
	public Exclusionrole getExclusionrole() {
		return exclusionrole;
	}

	/**
	 * @param exclusionrole the exclusionrole to set
	 */
	public void setExclusionrole(Exclusionrole exclusionrole) {
		this.exclusionrole = exclusionrole;
	}

	
	
}