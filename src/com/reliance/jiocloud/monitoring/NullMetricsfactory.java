package com.reliance.jiocloud.monitoring;
import java.net.UnknownHostException;

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
    public Metrics createMetrics() throws UnknownHostException {
        // TODO Auto-generated method stub
        Metrics metrics = new NullMetrics(this.displayMetricToConsole);
        this.addMetricAttributes(metrics);
        return metrics;
    }

}
