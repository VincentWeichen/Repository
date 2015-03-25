package supton.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


/**
 * Subgrouparrange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="subgrouparrange")
public class Subgrouparrange  implements java.io.Serializable {


    // Fields    

     private Integer sn;
     private String tid;
     private String version;
     private String subgroupcode;
     private Integer sequence;
     //1执行组；2位预约组；3时段预约
     private String type;
	 private Date begindate;
     private Date enddate;
     private String switchtime;
     private Integer timeduration;
     //1：新建，2：启用，3：作废
     private String organizationcode;
     private String begindateStr;
     private String enddateStr;


    // Constructors

    /** default constructor */
    public Subgrouparrange() {
    }

    
    /** full constructor */
    public Subgrouparrange(String subgroupcode, String tid, Integer sequence, String type, Date begindate, Date enddate, String switchtime, Integer timeduration, Integer status, String organizationcode) {
        this.subgroupcode = subgroupcode;
        this.tid = tid;
        this.sequence = sequence;
        this.type = type;
        this.begindate = begindate;
        this.enddate = enddate;
        this.switchtime = switchtime;
        this.timeduration = timeduration;
        this.organizationcode = organizationcode;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="sn", unique=true, nullable=false)

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
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


	@Column(name="version", length=50)

    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="subgroupcode", length=30)

    public String getSubgroupcode() {
        return this.subgroupcode;
    }
    
    public void setSubgroupcode(String subgroupcode) {
        this.subgroupcode = subgroupcode;
    }
    
    @Column(name="sequence")

    public Integer getSequence() {
        return this.sequence;
    }
    
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    @Column(name="type", length=10)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="begindate", length=19)

    public Date getBegindate() {
        return this.begindate;
    }
    
    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }
    
    @Column(name="enddate", length=19)

    public Date getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    @Column(name="switchtime", length=20)
    public String getSwitchtime() {
        return this.switchtime;
    }
    
    public void setSwitchtime(String switchtime) {
        this.switchtime = switchtime;
    }
    
    @Column(name="timeduration")

    public Integer getTimeduration() {
        return this.timeduration;
    }
    
    public void setTimeduration(Integer timeduration) {
        this.timeduration = timeduration;
    }

	/**
	 * @return the organizationcode
	 */
    @Column(name="organizationcode", length=30)
	public String getOrganizationcode() {
		return organizationcode;
	}


	/**
	 * @param organizationcode the organizationcode to set
	 */
	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}
   
	/**
	 * @return the begindateStr
	 */
	@Transient
	public String getBegindateStr() {
		return begindateStr;
	}


	/**
	 * @param begindateStr the begindateStr to set
	 */
	public void setBegindateStr(String begindateStr) {
		this.begindateStr = begindateStr;
	}


	/**
	 * @return the enddateStr
	 */
	@Transient
	public String getEnddateStr() {
		return enddateStr;
	}


	/**
	 * @param enddateStr the enddateStr to set
	 */
	public void setEnddateStr(String enddateStr) {
		this.enddateStr = enddateStr;
	}
}