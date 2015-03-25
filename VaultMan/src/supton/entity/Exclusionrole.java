package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Exclusionrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="exclusionrole")
public class Exclusionrole  implements java.io.Serializable {


    // Fields    

     private Integer sn;
     private String exclusionrole;


    // Constructors

    /** default constructor */
    public Exclusionrole() {
    }

    
    /** full constructor */
    public Exclusionrole(String exclusionrole) {
        this.exclusionrole = exclusionrole;
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
    
    @Column(name="exclusionrole", length=200)

    public String getExclusionrole() {
        return this.exclusionrole;
    }
    
    public void setExclusionrole(String exclusionrole) {
        this.exclusionrole = exclusionrole;
    }
   








}