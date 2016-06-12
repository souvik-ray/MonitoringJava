package com.reliance.jiocloud.monitoring;
import java.net.UnknownHostException;

import org.slf4j.*;

public class ThreadLocalMetricsFactory extends AbstractMetricsFactory {

    private static ThreadLocalMetricsFactory myObj;

    private ThreadLocalMetricsFactory() {
        super();
    }

    public synchronized static ThreadLocalMetricsFactory getInstance(String serviceLogPath) {
        if (myObj == null) {
            myObj = new ThreadLocalMetricsFactory();
        }
        return myObj;
    }

    @Override
    public Metrics createMetrics() {
        Metrics metrics = new ThreadLocalMetrics();
        this.addMetricAttributes(metrics);
        return metrics;
    }
}
