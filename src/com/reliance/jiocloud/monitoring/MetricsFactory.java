package com.reliance.jiocloud.monitoring;
import java.net.UnknownHostException;

public interface MetricsFactory {

    public Metrics createMetrics() throws UnknownHostException;
}
