//import jakarta.persistence.*;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Table(name = "schedule_base_line")
//public class sdf implements Serializable {
//
//    private static final long serialVersionUID = 8293813768826094160L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "SCHB_ID")
//    private Long id;
//
//    @Column(name = "SCHB_CODE")
//    private String code;
//
//    @Column(name = "SCHB_NAME")
//    private String name;
//
//
//    @Column(name = "SCHB_ITEM_TYPE")
//    private String scheduleItemType;
//
//
//
//    @Column(name = "SCHB_SCHEDULE_TYPE")
//    private String scheduleType;
//    @Temporal(TemporalType.DATE)
//    @Column(name = "SCHB_DATE")
//    private Date date;
//
//    @Column(name = "SCHB_TIME_SCALE")
//    private String timeScale;
//
//    @Column(name = "SCHB_STATUS")
//    private Integer status;
//
//
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "SCHB_CREATED_ON", updatable = false)
//    private Date createdOn;
//
//
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "SCHB_UPDATED_ON")
//    private Date updatedOn;
//
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getScheduleItemType() {
//        return scheduleItemType;
//    }
//
//    public void setScheduleItemType(String scheduleItemType) {
//        this.scheduleItemType = scheduleItemType;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getScheduleType() {
//        return scheduleType;
//    }
//
//    public void setScheduleType(String scheduleType) {
//        this.scheduleType = scheduleType;
//    }
//
//    public String getTimeScale() {
//        return timeScale;
//    }
//
//    public void setTimeScale(String timeScale) {
//        this.timeScale = timeScale;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Date getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(Date createdOn) {
//        this.createdOn = createdOn;
//    }
//
//    public Date getUpdatedOn() {
//        return updatedOn;
//    }
//
//    public void setUpdatedOn(Date updatedOn) {
//        this.updatedOn = updatedOn;
//    }
//
//    public ProjMstrEntity getProjId() {
//        return projId;
//    }
//
//    public void setProjId(ProjMstrEntity projId) {
//        this.projId = projId;
//    }
//
//    public UserMstrEntity getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(UserMstrEntity createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public UserMstrEntity getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(UserMstrEntity updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//}
