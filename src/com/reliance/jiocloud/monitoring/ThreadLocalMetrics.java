package com.reliance.jiocloud.monitoring;
import org.slf4j.*;

public class ThreadLocalMetrics extends AbstractMetrics {
	private static final Logger logger = LoggerFactory.getLogger("service_log");
    private static ThreadLocal<Metrics> threadlocal = new ThreadLocal<Metrics>();
    
    public ThreadLocalMetrics() {
        ThreadLocalMetrics.threadlocal.set(this);
    }

    public static Metrics get() {
        return ThreadLocalMetrics.threadlocal.get();
    }

    @Override
    public void initializeMetrics() {
     }

    @Override
    public void flushMetrics() {
        logger.info(this.toString());
    }

    public String toString() {
        return super.toString();
    }

    public void close() {
        ThreadLocalMetrics.threadlocal.remove();
    }

}
