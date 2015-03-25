package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Messagelog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="messagelog")
public class Messagelog  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Date logtime;
     private Date sendtime;
     private String message;
     private String cellphone;
     private Integer status;
     private String errormsg;
     private Integer times;


    // Constructors

    /** default constructor */
    public Messagelog() {
    }

    
    /** full constructor */
    public Messagelog(Date sendtime, Date logtime, String message, String cellphone, Integer status, String errormsg,Integer times) {
        this.logtime = logtime;
        this.sendtime = sendtime;
        this.message = message;
        this.cellphone = cellphone;
        this.status = status;
        this.errormsg = errormsg;
        this.times = times;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
    
	@Column(name="logtime", length=19)
    public Date getLogtime() {
		return logtime;
	}


	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}


	@Column(name="sendtime", length=19)
    public Date getSendtime() {
        return this.sendtime;
    }
	public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
    
    @Column(name="message", length=200)

    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Column(name="cellphone", length=50)

    public String getCellphone() {
        return this.cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="errormsg", length=50)

    public String getErrormsg() {
        return this.errormsg;
    }
    
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    @Column(name="times")
	public Integer getTimes() {
		return times;
	}


	public void setTimes(Integer times) {
		this.times = times;
	}
   








}