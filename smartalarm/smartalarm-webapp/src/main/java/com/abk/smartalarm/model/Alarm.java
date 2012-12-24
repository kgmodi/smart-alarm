package com.abk.smartalarm.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alarm {
    private String id;
    private String alarmTime;
    private AlarmType alarmType;
    private String userId;
    private String alarmName;
    private String fuid;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @return the alarmTime
     */
    public String getAlarmTime() {
        return alarmTime;
    }
    /**
     * @return the alarmType
     */
    public AlarmType getAlarmType() {
        return alarmType;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @return the alarmName
     */
    public String getAlarmName() {
        return alarmName;
    }
    /**
     * @return the fuid
     */
    public String getFuid() {
        return fuid;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @param alarmTime the alarmTime to set
     */
    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }
    /**
     * @param alarmType the alarmType to set
     */
    public void setAlarmType(AlarmType alarmType) {
        this.alarmType = alarmType;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @param alarmName the alarmName to set
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }
    /**
     * @param fuid the fuid to set
     */
    public void setFuid(String fuid) {
        this.fuid = fuid;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Alarm [id=" + id + ", alarmTime=" + alarmTime + ", alarmType="
                + alarmType + ", userId=" + userId + ", alarmName=" + alarmName
                + ", fuid=" + fuid + "]";
    }
   
    
}
