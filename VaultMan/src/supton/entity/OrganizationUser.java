package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Organizationuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "organizationuser")
public class OrganizationUser implements java.io.Serializable {

	// Fields

	private Integer sn;
	private String organizationcode;
	private String usercode;

	// Constructors

	/** default constructor */
	public OrganizationUser() {
	}

	/** full constructor */
	public OrganizationUser(String organizationcode, String usercode) {
		this.organizationcode = organizationcode;
		this.usercode = usercode;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "sn", unique = true, nullable = false)
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Column(name = "organizationcode", length = 30)
	public String getOrganizationcode() {
		return this.organizationcode;
	}

	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}

	@Column(name = "usercode", length = 30)
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}