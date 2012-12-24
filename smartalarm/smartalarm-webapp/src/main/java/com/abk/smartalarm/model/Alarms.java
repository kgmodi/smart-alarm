package com.abk.smartalarm.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alarms {

    private List<Alarm> alarms;

    /**
     * @return the alarms
     */
    public List<Alarm> getAlarms() {
        return alarms;
    }

    /**
     * @param alarms the alarms to set
     */
    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
