package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Vaultlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vaultlog")
public class Vaultlog  implements java.io.Serializable {


    // Fields    

     private Integer logId;
     private Date createTime;
     private String tid;
     private Integer logIndex;
     private Date entryDate;
     private String inOutIndication;
     private String verificationSource;
     private String eventAlarmCode;
     private Integer doorNo;
     private Integer userId;
     private String cardId;
     private Integer functionKeyIndex;
     private Integer groupId;
     private String eventAlarmCodeDecode;
     private String doorStatus;


    // Constructors

    /** default constructor */
    public Vaultlog() {
    }

    
    /** full constructor */
    public Vaultlog(Date createTime, String tid, Integer logIndex, Date entryDate, String inOutIndication, String verificationSource, String eventAlarmCode, Integer doorNo, Integer userId, String cardId, Integer functionKeyIndex, Integer groupId, String eventAlarmCodeDecode,String doorStatus) {
        this.createTime = createTime;
        this.tid = tid;
        this.logIndex = logIndex;
        this.entryDate = entryDate;
        this.inOutIndication = inOutIndication;
        this.verificationSource = verificationSource;
        this.eventAlarmCode = eventAlarmCode;
        this.doorNo = doorNo;
        this.userId = userId;
        this.cardId = cardId;
        this.functionKeyIndex = functionKeyIndex;
        this.groupId = groupId;
        this.eventAlarmCodeDecode = eventAlarmCodeDecode;
        this.doorStatus = doorStatus;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue
    @Column(name="logID", unique=true, nullable=false)

    public Integer getLogId() {
        return this.logId;
    }
    
    public void setLogId(Integer logId) {
        this.logId = logId;
    }
    
    @Column(name="CreateTime", length=19)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="tid", length=50)

    public String getTid() {
        return this.tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    @Column(name="LogIndex")

    public Integer getLogIndex() {
        return this.logIndex;
    }
    
    public void setLogIndex(Integer logIndex) {
        this.logIndex = logIndex;
    }
    
    @Column(name="EntryDate", length=19)

    public Date getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    
    @Column(name="InOutIndication", length=50)

    public String getInOutIndication() {
        return this.inOutIndication;
    }
    
    public void setInOutIndication(String inOutIndication) {
        this.inOutIndication = inOutIndication;
    }
    
    @Column(name="VerificationSource", length=50)

    public String getVerificationSource() {
        return this.verificationSource;
    }
    
    public void setVerificationSource(String verificationSource) {
        this.verificationSource = verificationSource;
    }
    
    @Column(name="EventAlarmCode", length=50)

    public String getEventAlarmCode() {
        return this.eventAlarmCode;
    }
    
    public void setEventAlarmCode(String eventAlarmCode) {
        this.eventAlarmCode = eventAlarmCode;
    }
    
    @Column(name="DoorNo")

    public Integer getDoorNo() {
        return this.doorNo;
    }
    
    public void setDoorNo(Integer doorNo) {
        this.doorNo = doorNo;
    }
    
    @Column(name="UserID")

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="CardID", length=50)

    public String getCardId() {
        return this.cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    @Column(name="FunctionKeyIndex")

    public Integer getFunctionKeyIndex() {
        return this.functionKeyIndex;
    }
    
    public void setFunctionKeyIndex(Integer functionKeyIndex) {
        this.functionKeyIndex = functionKeyIndex;
    }
    
    @Column(name="GroupID")

    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    @Column(name="EventAlarmCodeDecode", length=100)

    public String getEventAlarmCodeDecode() {
        return this.eventAlarmCodeDecode;
    }
    
    public void setEventAlarmCodeDecode(String eventAlarmCodeDecode) {
        this.eventAlarmCodeDecode = eventAlarmCodeDecode;
    }

    @Column(name="DoorStatus", length=100)
	public String getDoorStatus() {
		return doorStatus;
	}


	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}
   








}