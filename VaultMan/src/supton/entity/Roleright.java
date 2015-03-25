package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Roleright entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="roleright")
public class Roleright  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String rolecode;
     private String operation;
     private String operationurl;


    // Constructors

    /** default constructor */
    public Roleright() {
    }

    
    /** full constructor */
    public Roleright(String rolecode, String operation, String operationurl) {
        this.rolecode = rolecode;
        this.operation = operation;
        this.operationurl = operationurl;
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
    
    @Column(name="rolecode", length=30)
    public String getRolecode() {
        return this.rolecode;
    }
    
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }
    
    @Column(name="operation", length=30)
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    @Column(name="operationurl", length=30)
    public String getOperationurl() {
        return this.operationurl;
    }
    
    public void setOperationurl(String operationurl) {
        this.operationurl = operationurl;
    }
   








}