package com.abk.smartalarm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String id;
    private String name;
    private String number;
    private Alarms alarms;
    private String password;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", number=" + number + "]";
    }
    /**
     * @return the alarms
     */
    public Alarms getAlarms() {
        return alarms;
    }
    /**
     * @param alarms the alarms to set
     */
    public void setAlarms(Alarms alarms) {
        this.alarms = alarms;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
