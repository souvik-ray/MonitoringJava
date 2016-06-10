import org.slf4j.*;

public class ThreadLocalMetrics extends AbstractMetrics {

    private static ThreadLocal<Metrics> threadlocal = new ThreadLocal<Metrics>();
    private static final Logger logger = LoggerFactory.getLogger("service_log");

    public ThreadLocalMetrics() {
        super();
        ThreadLocalMetrics.threadlocal.set(this);
        // this.logger = logger;
    }

    public static Metrics get() {
        return ThreadLocalMetrics.threadlocal.get();
    }

    @Override
    public void initializeMetrics() {
        // TODO Auto-generated method stub

    }

    @Override
    public void flushMetrics() {
        // TODO Auto-generated method stub
        logger.info(this.toString());
    }

    public String toString() {
        return super.toString();
    }

    public void close() {
        super.close();
        ThreadLocalMetrics.threadlocal.remove();
    }

}
