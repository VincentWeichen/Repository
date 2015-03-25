package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Subgroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="subgroup")
public class Subgroup  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tid;
     private String version;
     private String code;
     private String name;
     private String type;
     private String organizationcode;
     private String groupauthorize;
     private String usernames;
     private String usercodesys1;
     private String usernamesys1;
     private String tusercode1;
     private String usercodesys2;
     private String usernamesys2;
     private String tusercode2;
     private String usercodesys3;
     private String usernamesys3;
     private String tusercode3;
     private String usercodesys4;
     private String usernamesys4;
     private String tusercode4;
     private String usercodesys5;
     private String usernamesys5;
     private String tusercode5;


    // Constructors

    /** default constructor */
    public Subgroup() {
    }

    
    /** full constructor */
	public Subgroup(String tid, String version, String code, String name,
			String type, String organizationcode, String groupauthorize,
			String usernames, String usercodesys1, String usernamesys1, String tusercode1,
			String usercodesys2, String usernamesys2, String tusercode2, String usercodesys3, String usernamesys3, String tusercode3,
			String usercodesys4, String usernamesys4, String tusercode4, String usercodesys5, String usernamesys5, String tusercode5) {
		this.tid = tid;
		this.version = version;
		this.code = code;
		this.name = name;
		this.type = type;
		this.organizationcode = organizationcode;
		this.groupauthorize = groupauthorize;
		this.usernames = usernames;
		this.usercodesys1 = usercodesys1;
		this.usernamesys1 = usernamesys1;
		this.tusercode1 = tusercode1;
		this.usercodesys2 = usercodesys2;
		this.usernamesys2 = usernamesys2;
		this.tusercode2 = tusercode2;
		this.usercodesys3 = usercodesys3;
		this.usernamesys3 = usernamesys3;
		this.tusercode3 = tusercode3;
		this.usercodesys4 = usercodesys4;
		this.usernamesys4 = usernamesys4;
		this.tusercode4 = tusercode4;
		this.usercodesys5 = usercodesys5;
		this.usernamesys5 = usernamesys5;
		this.tusercode5 = tusercode5;

	}

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="tid", length=100)
    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    @Column(name="version", length=50)
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="code", length=30)

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="name", length=30)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="type", length=10)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="organizationcode", length=30)

    public String getOrganizationcode() {
        return this.organizationcode;
    }
    
    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode;
    }


	/**
	 * @return the groupauthorize
	 */
    @Column(name="groupauthorize", length=10)
	public String getGroupauthorize() {
		return groupauthorize;
	}


	/**
	 * @param groupauthorize the groupauthorize to set
	 */
	public void setGroupauthorize(String groupauthorize) {
		this.groupauthorize = groupauthorize;
	}



	@Column(name="usernames", length=255)
	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}
	
	@Column(name="usercodesys1", length=100)
	public String getUsercodesys1() {
		return usercodesys1;
	}

	public void setUsercodesys1(String usercodesys1) {
		this.usercodesys1 = usercodesys1;
	}

	@Column(name="usercodesys2", length=100)
	public String getUsercodesys2() {
		return usercodesys2;
	}

	public void setUsercodesys2(String usercodesys2) {
		this.usercodesys2 = usercodesys2;
	}
	
	@Column(name="usercodesys3", length=100)
	public String getUsercodesys3() {
		return usercodesys3;
	}

	public void setUsercodesys3(String usercodesys3) {
		this.usercodesys3 = usercodesys3;
	}
	
	@Column(name="usercodesys4", length=100)
	public String getUsercodesys4() {
		return usercodesys4;
	}

	public void setUsercodesys4(String usercodesys4) {
		this.usercodesys4 = usercodesys4;
	}
	
	@Column(name="usercodesys5", length=100)
	public String getUsercodesys5() {
		return usercodesys5;
	}

	public void setUsercodesys5(String usercodesys5) {
		this.usercodesys5 = usercodesys5;
	}
	
	@Column(name="tusercode1", length=100)
	public String getTusercode1() {
		return tusercode1;
	}

	public void setTusercode1(String tusercode1) {
		this.tusercode1 = tusercode1;
	}
	
	@Column(name="tusercode2", length=100)
	public String getTusercode2() {
		return tusercode2;
	}

	public void setTusercode2(String tusercode2) {
		this.tusercode2 = tusercode2;
	}

	@Column(name="tusercode3", length=100)
	public String getTusercode3() {
		return tusercode3;
	}

	public void setTusercode3(String tusercode3) {
		this.tusercode3 = tusercode3;
	}
	
	@Column(name="tusercode4", length=100)
	public String getTusercode4() {
		return tusercode4;
	}

	public void setTusercode4(String tusercode4) {
		this.tusercode4 = tusercode4;
	}
	
	@Column(name="tusercode5", length=100)
	public String getTusercode5() {
		return tusercode5;
	}

	public void setTusercode5(String tusercode5) {
		this.tusercode5 = tusercode5;
	}
	
	@Column(name="usernamesys1", length=100)
	public String getUsernamesys1() {
		return usernamesys1;
	}

	public void setUsernamesys1(String usernamesys1) {
		this.usernamesys1 = usernamesys1;
	}
	@Column(name="usernamesys2", length=100)
	public String getUsernamesys2() {
		return usernamesys2;
	}

	public void setUsernamesys2(String usernamesys2) {
		this.usernamesys2 = usernamesys2;
	}
	@Column(name="usernamesys3", length=100)
	public String getUsernamesys3() {
		return usernamesys3;
	}

	public void setUsernamesys3(String usernamesys3) {
		this.usernamesys3 = usernamesys3;
	}
	@Column(name="usernamesys4", length=100)
	public String getUsernamesys4() {
		return usernamesys4;
	}

	public void setUsernamesys4(String usernamesys4) {
		this.usernamesys4 = usernamesys4;
	}
	@Column(name="usernamesys5", length=100)
	public String getUsernamesys5() {
		return usernamesys5;
	}

	public void setUsernamesys5(String usernamesys5) {
		this.usernamesys5 = usernamesys5;
	}



}