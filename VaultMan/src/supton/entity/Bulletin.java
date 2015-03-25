package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Bulletin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bulletin")
public class Bulletin  implements java.io.Serializable {


    // Fields    

     private Integer sn;
     private String title;
     private String content;
     private Date releasetime;


    // Constructors

    /** default constructor */
    public Bulletin() {
    }

    
    /** full constructor */
    public Bulletin(String title, String content, Date releasetime) {
        this.title = title;
        this.content = content;
        this.releasetime = releasetime;
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
    
    @Column(name="title", length=30)

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="content", length=200)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="releasetime", length=19)

    public Date getReleasetime() {
        return this.releasetime;
    }
    
    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }
   








}