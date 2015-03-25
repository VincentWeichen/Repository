package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Template entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="template")
public class Template  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String templateId;
     private String templatexmlstring;
     private String createuser;
     private String createtime;


    // Constructors

    /** default constructor */
    public Template() {
    }

    
    /** full constructor */
    public Template(String templateId, String templatexmlstring, String createuser, String createtime) {
        this.templateId = templateId;
        this.templatexmlstring = templatexmlstring;
        this.createuser = createuser;
        this.createtime = createtime;
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
    
    @Column(name="templateId", length=50)

    public String getTemplateId() {
        return this.templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    @Column(name="templatexmlstring", length=65535)

    public String getTemplatexmlstring() {
        return this.templatexmlstring;
    }
    
    public void setTemplatexmlstring(String templatexmlstring) {
        this.templatexmlstring = templatexmlstring;
    }
    
    @Column(name="createuser", length=50)

    public String getCreateuser() {
        return this.createuser;
    }
    
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }
    
    @Column(name="createtime", length=50)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   
}