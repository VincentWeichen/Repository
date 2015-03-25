package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Rolefunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="rolefunction")
public class Rolefunction  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String functioncode;
     private String functionname;
     private String rolecode;
     private String funwrite;
     private String funread;


    // Constructors

    /** default constructor */
    public Rolefunction() {
    }

    
    /** full constructor */
    public Rolefunction(String functioncode, String functionname, String rolecode, String funwrite, String funread) {
        this.functioncode = functioncode;
        this.functionname = functionname;
        this.rolecode = rolecode;
        this.funwrite = funwrite;
        this.funread = funread;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="functioncode", length=30)
    public String getFunctioncode() {
        return this.functioncode;
    }
    
    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode;
    }
    
    @Column(name="functionname", length=100)
    public String getFunctionname() {
        return this.functionname;
    }
    
    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }
    
    @Column(name="rolecode", length=30)
    public String getRolecode() {
        return this.rolecode;
    }
    
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }
    
    @Column(name="funwrite", length=30)
    public String getFunwrite() {
        return this.funwrite;
    }
    
    public void setFunwrite(String funwrite) {
        this.funwrite = funwrite;
    }
    
    @Column(name="funread", length=30)
    public String getFunread() {
        return this.funread;
    }
    
    public void setFunread(String funread) {
        this.funread = funread;
    }

}