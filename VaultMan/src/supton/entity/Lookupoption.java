package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Lookupoption entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lookupoption")
public class Lookupoption  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer class_;
     private Integer sn;
     private String option;
     private String optionvalue;


    // Constructors

    /** default constructor */
    public Lookupoption() {
    }

    
    /** full constructor */
    public Lookupoption(Integer class_, Integer sn, String option, String optionvalue) {
        this.class_ = class_;
        this.sn = sn;
        this.option = option;
        this.optionvalue = optionvalue;
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
    
    @Column(name="class")

    public Integer getClass_() {
        return this.class_;
    }
    
    public void setClass_(Integer class_) {
        this.class_ = class_;
    }
    
    @Column(name="sn")

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
    }
    
    @Column(name="option", length=50)

    public String getOption() {
        return this.option;
    }
    
    public void setOption(String option) {
        this.option = option;
    }
    
    @Column(name="optionvalue", length=50)

    public String getOptionvalue() {
        return this.optionvalue;
    }
    
    public void setOptionvalue(String optionvalue) {
        this.optionvalue = optionvalue;
    }
   








}