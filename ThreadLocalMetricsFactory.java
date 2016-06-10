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
    public Metrics createMetrics() throws UnknownHostException {
        Metrics metrics = new ThreadLocalMetrics();
        this.addMetricAttributes(metrics);
        return metrics;
    }

    // public Logger createTimedRotatingLog(String path, boolean
    // propogateToApplicationLogs){
    // logger = Logger.getLogger("service.log");
    // logger.propagate = propogateToApplicationLogs;
    //
    // Handler handler = Logger.handlers.WatchedFileHandler(path);

    // logger.addHandler(handler);
    // return logger;
    // }
}
