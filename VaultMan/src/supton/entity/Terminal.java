package supton.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Terminal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="terminal")
public class Terminal  implements java.io.Serializable {


    // Fields    

     private String tid;
     private Integer status;
     private String ipaddress;
     private String macaddress;
     private String serialno;
     private String modelname;
     private String firmwareversion;
     private String organizationCode;
     private List<Vaultlog> vaultlogList;
     //应急门状态   3开门、4操作中、5正常（安全）
     private Integer emgdoor_status;
     private List<Organization> organizationList;
     

    // Constructors

    /** default constructor */
    public Terminal() {
    }

    
    /** full constructor */
    public Terminal(Integer status, String ipaddress, String macaddress, String serialno, String modelname, String firmwareversion,Integer emgdoor_status) {
        this.status = status;
        this.ipaddress = ipaddress;
        this.macaddress = macaddress;
        this.serialno = serialno;
        this.modelname = modelname;
        this.firmwareversion = firmwareversion;
        this.emgdoor_status = emgdoor_status;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="tid", unique=true, nullable=false, length=100)

    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="ipaddress", length=50)

    public String getIpaddress() {
        return this.ipaddress;
    }
    
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    
    @Column(name="macaddress", length=100)

    public String getMacaddress() {
        return this.macaddress;
    }
    
    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }
    
    @Column(name="serialno", length=100)

    public String getSerialno() {
        return this.serialno;
    }
    
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
    
    @Column(name="modelname", length=100)

    public String getModelname() {
        return this.modelname;
    }
    
    public void setModelname(String modelname) {
        this.modelname = modelname;
    }
    
    @Column(name="firmwareversion", length=100)

    public String getFirmwareversion() {
        return this.firmwareversion;
    }
    
    public void setFirmwareversion(String firmwareversion) {
        this.firmwareversion = firmwareversion;
    }
    
    @Column(name="emgdoor_status")
    public Integer getEmgdoor_status() {
		return emgdoor_status;
	}

	public void setEmgdoor_status(Integer emgdoor_status) {
		this.emgdoor_status = emgdoor_status;
	}
	/**
	 * @return the organizationCode
	 */
    @Transient
	public String getOrganizationCode() {
		return organizationCode;
	}


	/**
	 * @param organizationCode the organizationCode to set
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}


	/**
	 * @return the vaultlogList
	 */
	@Transient
	public List<Vaultlog> getVaultlogList() {
		return vaultlogList;
	}


	/**
	 * @param vaultlogList the vaultlogList to set
	 */
	public void setVaultlogList(List<Vaultlog> vaultlogList) {
		this.vaultlogList = vaultlogList;
	}

	/**
	 * @return the organizationList
	 */
	@Transient
	public List<Organization> getOrganizationList() {
		return organizationList;
	}


	/**
	 * @param organizationList the organizationList to set
	 */
	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	
   
}