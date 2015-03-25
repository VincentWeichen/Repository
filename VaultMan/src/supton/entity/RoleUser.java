package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Roleuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roleuser")
public class RoleUser implements java.io.Serializable {

	// Fields

	private Integer sn;
	private String rolecode;
	private String usercode;
	private Date expired;

	// Constructors

	/** default constructor */
	public RoleUser() {
	}

	/** full constructor */
	public RoleUser(String rolecode, String usercode, Date expired) {
		this.rolecode = rolecode;
		this.usercode = usercode;
		this.expired = expired;
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

	@Column(name = "rolecode", length = 30)
	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Column(name = "usercode", length = 30)
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "expired", length = 19)
	public Date getExpired() {
		return this.expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

}