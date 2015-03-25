package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * VaultlogDecode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vaultlog_decode")
public class VaultlogDecode  implements java.io.Serializable {


    // Fields    

     private Integer sn;
     private Date operatingtime;
     private String operation;
     private String usercode;


    // Constructors

    /** default constructor */
    public VaultlogDecode() {
    }

    
    /** full constructor */
    public VaultlogDecode(Date operatingtime, String operation, String usercode) {
        this.operatingtime = operatingtime;
        this.operation = operation;
        this.usercode = usercode;
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
    
    @Column(name="operatingtime", length=19)

    public Date getOperatingtime() {
        return this.operatingtime;
    }
    
    public void setOperatingtime(Date operatingtime) {
        this.operatingtime = operatingtime;
    }
    
    @Column(name="operation", length=50)

    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    @Column(name="usercode", length=30)

    public String getUsercode() {
        return this.usercode;
    }
    
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
   








}