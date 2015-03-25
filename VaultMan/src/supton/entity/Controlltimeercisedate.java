package supton.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Controlltimeercisedate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="controlltimeercisedate")
public class Controlltimeercisedate  implements java.io.Serializable {

     private Integer id;
     private Integer controlltimeid;
     private Integer sequence;
     private String organizationcode;
     private String weekday;
     private String begindate;
     private String enddate;
     //1：工作日管制；2：休休息日管制
     private String type;
     private String description;
     private List<Controlltimedetail> controlltimedetailList;

    // Constructors

    /** default constructor */
    public Controlltimeercisedate() {
    }

    
    /** full constructor */
    public Controlltimeercisedate(Integer controlltimeid, Integer sequence, String organizationcode, String weekday, String begindate, String enddate,String type,String description) {
        this.controlltimeid = controlltimeid;
        this.sequence = sequence;
        this.organizationcode = organizationcode;
        this.weekday = weekday;
        this.begindate = begindate;
        this.enddate = enddate;
        this.type = type;
        this.description = description;
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
    
    
    /**
	 * @return the controlltimeid
	 */
    @Column(name="controlltimeid")
	public Integer getControlltimeid() {
		return controlltimeid;
	}

	/**
	 * @param controlltimeid the controlltimeid to set
	 */
	public void setControlltimeid(Integer controlltimeid) {
		this.controlltimeid = controlltimeid;
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
    
    @Column(name="weekday", length=30)

    public String getWeekday() {
        return this.weekday;
    }
    
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
    
    @Column(name="begindate", length=30)
    public String getBegindate() {
        return this.begindate;
    }
    
    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }
    
    @Column(name="enddate", length=30)
    public String getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }


	/**
	 * @return the type
	 */
    @Column(name="type", length=10)
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="description", length=100)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Transient
	public List<Controlltimedetail> getControlltimedetailList() {
		return controlltimedetailList;
	}


	public void setControlltimedetailList(
			List<Controlltimedetail> controlltimedetailList) {
		this.controlltimedetailList = controlltimedetailList;
	}
   

}