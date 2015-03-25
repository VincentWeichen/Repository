package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Controlltimedetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="controlltimedetail")
public class Controlltimedetail  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer controlltimeid;
     //1：工作日管制；2：休休息日管制
     private String type;
     private Integer sequence;
     private String organizationcode;
     private String weekday;
     private String begindate;
     private String enddate;
     private Integer status;
     private Integer controlltimeercisedateid;

    // Constructors

    /** default constructor */
    public Controlltimedetail() {
    }

    
    /** full constructor */
    public Controlltimedetail(Integer controlltimeid, String type, Integer sequence, String organizationcode, String weekday, String begindate, String enddate, Integer status,Integer controlltimeercisedateid) {
        this.controlltimeid = controlltimeid;
        this.type = type;
        this.sequence = sequence;
        this.organizationcode = organizationcode;
        this.weekday = weekday;
        this.begindate = begindate;
        this.enddate = enddate;
        this.status = status;
        this.controlltimeercisedateid = controlltimeercisedateid;
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
    
    @Column(name="controlltimeid")

    public Integer getControlltimeid() {
        return this.controlltimeid;
    }
    
    public void setControlltimeid(Integer controlltimeid) {
        this.controlltimeid = controlltimeid;
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
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name="controlltimeercisedateid")
	public Integer getControlltimeercisedateid() {
		return controlltimeercisedateid;
	}


	public void setControlltimeercisedateid(Integer controlltimeercisedateid) {
		this.controlltimeercisedateid = controlltimeercisedateid;
	}
   

}