package supton.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


/**
 * Controlltime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="controlltime")
public class Controlltime  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String version;
     //1：周循环；2：年循环，3：假期/调班
     private String type;
     private Integer sequence;
     private String organizationcode;
     private Integer status;
     private List<Controlltimedetail> controlltimedetailWorkList;
     private List<Controlltimeercisedate> controlltimeercisedateWorkList;
     private List<Controlltimedetail> controlltimedetailRestList;
     private List<Controlltimeercisedate> controlltimeercisedateRestList;
     
     private List<Controlltimedetail> controlltimedetailWorkYearList;
     private List<Controlltimeercisedate> controlltimeercisedateWorkYearList;
     private List<Controlltimedetail> controlltimedetailRestYearList;
     private List<Controlltimeercisedate> controlltimeercisedateRestYearList;


    // Constructors

    /** default constructor */
    public Controlltime() {
    }

    
    /** full constructor */
    public Controlltime(String type, Integer sequence, String organizationcode, Integer status) {
        this.type = type;
        this.sequence = sequence;
        this.organizationcode = organizationcode;
        this.status = status;
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

    @Column(name="version", length=50)

    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="type", length=10)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="sequence")

    public Integer getSequence() {
        return this.sequence;
    }
    
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    @Column(name="organizationcode", length=30)

    public String getOrganizationcode() {
        return this.organizationcode;
    }
    
    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }


	/**
	 * @return the controlltimedetailWorkList
	 */
    @Transient
	public List<Controlltimedetail> getControlltimedetailWorkList() {
		return controlltimedetailWorkList;
	}


	/**
	 * @param controlltimedetailWorkList the controlltimedetailWorkList to set
	 */
	public void setControlltimedetailWorkList(
			List<Controlltimedetail> controlltimedetailWorkList) {
		this.controlltimedetailWorkList = controlltimedetailWorkList;
	}


	/**
	 * @return the controlltimeercisedateWorkList
	 */
	@Transient
	public List<Controlltimeercisedate> getControlltimeercisedateWorkList() {
		return controlltimeercisedateWorkList;
	}


	/**
	 * @param controlltimeercisedateWorkList the controlltimeercisedateWorkList to set
	 */
	public void setControlltimeercisedateWorkList(
			List<Controlltimeercisedate> controlltimeercisedateWorkList) {
		this.controlltimeercisedateWorkList = controlltimeercisedateWorkList;
	}


	/**
	 * @return the controlltimedetailRestList
	 */
	@Transient
	public List<Controlltimedetail> getControlltimedetailRestList() {
		return controlltimedetailRestList;
	}


	/**
	 * @param controlltimedetailRestList the controlltimedetailRestList to set
	 */
	public void setControlltimedetailRestList(
			List<Controlltimedetail> controlltimedetailRestList) {
		this.controlltimedetailRestList = controlltimedetailRestList;
	}


	/**
	 * @return the controlltimeercisedateRestList
	 */
	@Transient
	public List<Controlltimeercisedate> getControlltimeercisedateRestList() {
		return controlltimeercisedateRestList;
	}


	/**
	 * @param controlltimeercisedateRestList the controlltimeercisedateRestList to set
	 */
	public void setControlltimeercisedateRestList(
			List<Controlltimeercisedate> controlltimeercisedateRestList) {
		this.controlltimeercisedateRestList = controlltimeercisedateRestList;
	}

	@Transient
	public List<Controlltimedetail> getControlltimedetailWorkYearList() {
		return controlltimedetailWorkYearList;
	}


	public void setControlltimedetailWorkYearList(
			List<Controlltimedetail> controlltimedetailWorkYearList) {
		this.controlltimedetailWorkYearList = controlltimedetailWorkYearList;
	}

	@Transient
	public List<Controlltimeercisedate> getControlltimeercisedateWorkYearList() {
		return controlltimeercisedateWorkYearList;
	}


	public void setControlltimeercisedateWorkYearList(
			List<Controlltimeercisedate> controlltimeercisedateWorkYearList) {
		this.controlltimeercisedateWorkYearList = controlltimeercisedateWorkYearList;
	}

	@Transient
	public List<Controlltimedetail> getControlltimedetailRestYearList() {
		return controlltimedetailRestYearList;
	}


	public void setControlltimedetailRestYearList(
			List<Controlltimedetail> controlltimedetailRestYearList) {
		this.controlltimedetailRestYearList = controlltimedetailRestYearList;
	}

	@Transient
	public List<Controlltimeercisedate> getControlltimeercisedateRestYearList() {
		return controlltimeercisedateRestYearList;
	}


	public void setControlltimeercisedateRestYearList(
			List<Controlltimeercisedate> controlltimeercisedateRestYearList) {
		this.controlltimeercisedateRestYearList = controlltimeercisedateRestYearList;
	}

}