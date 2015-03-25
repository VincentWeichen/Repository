package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Flowinstance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="flowinstance"
    ,catalog="vaultman"
)

public class Flowinstance  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer templateId;
     private String templateXmlString;
     private String createUser;
     private Date createTime;
     private Date complateTime;
     private Integer state;


    // Constructors

    /** default constructor */
    public Flowinstance() {
    }

    
    /** full constructor */
    public Flowinstance(Integer templateId, String templateXmlString, String createUser, Date createTime, Date complateTime, Integer state) {
        this.templateId = templateId;
        this.templateXmlString = templateXmlString;
        this.createUser = createUser;
        this.createTime = createTime;
        this.complateTime = complateTime;
        this.state = state;
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
    
    @Column(name="TemplateId")

    public Integer getTemplateId() {
        return this.templateId;
    }
    
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    
    @Column(name="TemplateXmlString", length=32700)

    public String getTemplateXmlString() {
        return this.templateXmlString;
    }
    
    public void setTemplateXmlString(String templateXmlString) {
        this.templateXmlString = templateXmlString;
    }
    
    @Column(name="CreateUser", length=50)

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name="CreateTime", length=19)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="ComplateTime", length=19)

    public Date getComplateTime() {
        return this.complateTime;
    }
    
    public void setComplateTime(Date complateTime) {
        this.complateTime = complateTime;
    }
    
    @Column(name="State")

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}