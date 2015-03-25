package supton.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Flowinstancenode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="flowinstancenode"
    ,catalog="vaultman"
)

public class Flowinstancenode  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer parentState;
     private Integer instanceId;
     private String stateId;
     private String transactUser;
     private Date createTime;
     private Date completeTime;
     private Integer state;
     private String submitUser;
     private String submitOption;
     private String submitResult;
     private String nextUser;


    // Constructors

    /** default constructor */
    public Flowinstancenode() {
    }

    
    /** full constructor */
    public Flowinstancenode(Integer parentState, Integer instanceId, String stateId, String transactUser, Date createTime, Date completeTime, Integer state, String submitUser, String submitOption, String submitResult, String nextUser) {
        this.parentState = parentState;
        this.instanceId = instanceId;
        this.stateId = stateId;
        this.transactUser = transactUser;
        this.createTime = createTime;
        this.completeTime = completeTime;
        this.state = state;
        this.submitUser = submitUser;
        this.submitOption = submitOption;
        this.submitResult = submitResult;
        this.nextUser = nextUser;
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
    
    @Column(name="ParentState")

    public Integer getParentState() {
        return this.parentState;
    }
    
    public void setParentState(Integer parentState) {
        this.parentState = parentState;
    }
    
    @Column(name="InstanceId")

    public Integer getInstanceId() {
        return this.instanceId;
    }
    
    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }
    
    @Column(name="StateId", length=50)

    public String getStateId() {
        return this.stateId;
    }
    
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
    
    @Column(name="TransactUser", length=50)

    public String getTransactUser() {
        return this.transactUser;
    }
    
    public void setTransactUser(String transactUser) {
        this.transactUser = transactUser;
    }
    
    @Column(name="CreateTime", length=19)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CompleteTime", length=19)

    public Date getCompleteTime() {
        return this.completeTime;
    }
    
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
    
    @Column(name="State")

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    @Column(name="SubmitUser", length=50)

    public String getSubmitUser() {
        return this.submitUser;
    }
    
    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }
    
    @Column(name="SubmitOption")

    public String getSubmitOption() {
        return this.submitOption;
    }
    
    public void setSubmitOption(String submitOption) {
        this.submitOption = submitOption;
    }
    
    @Column(name="SubmitResult")

    public String getSubmitResult() {
        return this.submitResult;
    }
    
    public void setSubmitResult(String submitResult) {
        this.submitResult = submitResult;
    }
    
    @Column(name="NextUser", length=50)

    public String getNextUser() {
        return this.nextUser;
    }
    
    public void setNextUser(String nextUser) {
        this.nextUser = nextUser;
    }
   








}