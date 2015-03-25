package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Fingerprintuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="fingerprintuser")
public class Fingerprintuser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tid;
     private String tusercode;
     private String tusername;
     private Integer userType;
     private Integer fingerAmount;
     private String usercodesys;
     private String usernamesys;


    // Constructors

    /** default constructor */
    public Fingerprintuser() {
    }

    
    /** full constructor */
    public Fingerprintuser(String tusercode, String tusername, Integer userType, Integer fingerAmount, String usercodesys, String tid, String usernamesys) {
        this.tusercode = tusercode;
        this.tusername = tusername;
        this.userType = userType;
        this.fingerAmount = fingerAmount;
        this.usercodesys = usercodesys;
        this.tid = tid;
        this.usernamesys = usernamesys;
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
    
    @Column(name="tusercode", length=30)

    public String getTusercode() {
        return this.tusercode;
    }
    
    public void setTusercode(String tusercode) {
        this.tusercode = tusercode;
    }
    
    @Column(name="tusername", length=30)
    /**
	 * @return the fingerusercode
	 */
	public String getTusername() {
		return tusername;
	}

	/**
	 * @param fingerusercode the fingerusercode to set
	 */
	public void setTusername(String tusername) {
		this.tusername = tusername;
	}
    
    @Column(name="UserType")
    public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}
    
    @Column(name="FingerAmount")
    public Integer getFingerAmount() {
		return fingerAmount;
	}

	public void setFingerAmount(Integer fingerAmount) {
		this.fingerAmount = fingerAmount;
	}
    
    @Column(name="usercodesys", length=30)

    public String getUsercodesys() {
        return this.usercodesys;
    }
    
	public void setUsercodesys(String usercodesys) {
        this.usercodesys = usercodesys;
    }


	/**
	 * @return the tid
	 */
    @Column(name="tid", length=100)
	public String getTid() {
		return tid;
	}


	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}


	/**
	 * @return the usernamesys
	 */
	@Column(name="usernamesys", length=50)
	public String getUsernamesys() {
		return usernamesys;
	}


	/**
	 * @param usernamesys the usernamesys to set
	 */
	public void setUsernamesys(String usernamesys) {
		this.usernamesys = usernamesys;
	}

   
}