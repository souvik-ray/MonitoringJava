package com.reliance.jiocloud.monitoring;
import java.net.UnknownHostException;
import java.util.Date;

public class MetricUtil {
    String METRICSOBJECT = "METRICSOBJECT";

    public Metrics initializeThreadLocalMetrics(String serviceLogPath, String programName) throws UnknownHostException {
        Metrics metrics;
        try {
            metrics = this.fetchThreadLocalMetrics();
        } catch (Exception e) {
            metrics = ThreadLocalMetricsFactory.getInstance(serviceLogPath).withMarketplaceId(this.getMarketplaceId())
                            .withProgramName(programName).createMetrics();
        }
        return metrics;
    }

    public Metrics fetchThreadLocalMetrics() {
        return ThreadLocalMetrics.get();
    }

    private String getMarketplaceId() {

        return "IdC1";
    }

    public void closeMetrics() {
        Metrics metrics = this.fetchThreadLocalMetrics();
        metrics.close();
    }

    public void reportTimingMetricUtcTime(String metricName, Date endTime, Date startTime) {
        Metrics metrics = this.fetchThreadLocalMetrics();
        long delta = endTime.getTime() - startTime.getTime();
        metrics.addTime(metricName, delta, Unit.MILLIS);
    }
}
