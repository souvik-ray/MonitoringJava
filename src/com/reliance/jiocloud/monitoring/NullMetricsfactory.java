package com.reliance.jiocloud.monitoring;

public class NullMetricsfactory extends AbstractMetricsFactory {

    boolean displayMetricToConsole;

    public NullMetricsfactory() {
        super();
        this.displayMetricToConsole = false;
    }

    public NullMetricsfactory withDisplayMetricToConsole(boolean displayMetricToConsole) {
        this.displayMetricToConsole = displayMetricToConsole;
        return this;
    }

    @Override
    public Metrics createMetrics() {
        Metrics metrics = new NullMetrics(this.displayMetricToConsole);
        this.addMetricAttributes(metrics);
        return metrics;
    }

}
