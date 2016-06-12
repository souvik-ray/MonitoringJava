package com.reliance.jiocloud.monitoring;
public class NullMetrics extends AbstractMetrics {

    boolean displayMetricToConsole;

    public NullMetrics(boolean displayMetricToConsole) {
        super();
        this.displayMetricToConsole = displayMetricToConsole;
    }

    @Override
    public void initializeMetrics() {

    }

    @Override
    public void flushMetrics() {
        System.out.println(this.toString());
        
    }

}
