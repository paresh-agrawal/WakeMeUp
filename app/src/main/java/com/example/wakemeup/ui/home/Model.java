package com.example.wakemeup.ui.home;

public class Model {

    int lineColor;
    String destination;
    String minutesToTravel;
    String stopInBetween;
    boolean switchAlarm;

    public Model(int lineColor, String destination, String minutesToTravel, String stopInBetween, boolean switchAlarm) {
        this.lineColor = lineColor;
        this.destination = destination;
        this.minutesToTravel = minutesToTravel;
        this.stopInBetween = stopInBetween;
        this.switchAlarm = switchAlarm;
    }

    public int getLineColor() {
        return lineColor;
    }

    public boolean isSwitchAlarm() {
        return switchAlarm;
    }

    public String getDestination() {
        return destination;
    }

    public String getMinutesToTravel() {
        return minutesToTravel;
    }

    public String getStopInBetween() {
        return stopInBetween;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setMinutesToTravel(String minutesToTravel) {
        this.minutesToTravel = minutesToTravel;
    }

    public void setStopInBetween(String stopInBetween) {
        this.stopInBetween = stopInBetween;
    }

    public void setSwitchAlarm(boolean switchAlarm) {
        this.switchAlarm = switchAlarm;
    }
}
