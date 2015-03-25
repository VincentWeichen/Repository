package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * Taskexport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="taskexport")
public class Taskexport  implements java.io.Serializable {


    // Fields    

     private Integer taskid;
     private String version;
     private String tid;
     private Integer type;
     private Integer status;
     private Integer times;


    // Constructors

    /** default constructor */
    public Taskexport() {
    }

    
    /** full constructor */
    public Taskexport(String tid, Integer type, Integer status, Integer times) {
        this.tid = tid;
        this.type = type;
        this.status = status;
        this.times = times;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="taskid", unique=true, nullable=false)
    public Integer getTaskid() {
        return this.taskid;
    }
    
    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }
    @Column(name="version", length=50)
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="tid", length=100)
    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    @Column(name="type")

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name="times")
    public Integer getTimes() {
        return this.times;
    }
    
    public void setTimes(Integer times) {
        this.times = times;
    }
   








}