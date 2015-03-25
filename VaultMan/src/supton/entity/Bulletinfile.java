package supton.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Bulletinfile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="bulletinfile")
public class Bulletinfile  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer bulletinsn;
     private String filepath;
     private String filename;
     private String filealias;
     private String filetsize;


    // Constructors

    /** default constructor */
    public Bulletinfile() {
    }

    
    /** full constructor */
    public Bulletinfile(Integer bulletinsn, String filepath) {
        this.bulletinsn = bulletinsn;
        this.filepath = filepath;
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
    
    @Column(name="bulletinsn")

    public Integer getBulletinsn() {
        return this.bulletinsn;
    }
    
    public void setBulletinsn(Integer bulletinsn) {
        this.bulletinsn = bulletinsn;
    }
    
    @Column(name="filepath", length=200)

    public String getFilepath() {
        return this.filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Column(name="filename", length=200)
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name="filealias", length=200)
	public String getFilealias() {
		return filealias;
	}


	public void setFilealias(String filealias) {
		this.filealias = filealias;
	}

	@Column(name="filetsize", length=100)
	public String getFiletsize() {
		return filetsize;
	}


	public void setFiletsize(String filetsize) {
		this.filetsize = filetsize;
	}

}