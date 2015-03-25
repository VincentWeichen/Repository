package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String password;
	private Integer type;
	private String name;
	private String sex;
	private String telephone;
	private String cellphone;
	private String title;
	private String cardnumber;
	private String address;
	private byte[] photo;
	private String rolenames;
	
	private Role role;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String code, String password, Integer type, String name,
			String sex, String telephone, String cellphone, String title,
			String cardnumber, String address, byte[] photo,String rolenames) {
		this.code = code;
		this.password = password;
		this.type = type;
		this.name = name;
		this.sex = sex;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.title = title;
		this.cardnumber = cardnumber;
		this.address = address;
		this.photo = photo;
		this.rolenames = rolenames;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "cellphone", length = 50)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "title", length = 10)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "cardnumber", length = 50)
	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "photo")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	/**
	 * @return the rolenames
	 */
	@Column(name = "rolenames")
	public String getRolenames() {
		return rolenames;
	}

	/**
	 * @param rolenames the rolenames to set
	 */
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	/**
	 * @return the role
	 */
	@Transient
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}