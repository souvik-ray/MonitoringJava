import java.net.UnknownHostException;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        // TODO Auto-generated method stub
        AbstractMetricsFactory metricsFactory = new NullMetricsfactory().withDisplayMetricToConsole(true)
                        .withAccountId("xxxxxxxxxxxxxxx").withMarketplaceId("IdC1").withProgramName("CinderAPI")
                        .withOperationsName("CreateVolume");

        int i = 0;
        while (i < 100) {
            Metrics metrics = metricsFactory.createMetrics();

            metrics.addProperty("RequestId", "q311-r329-r302-jf2j-f92f-if93");
            metrics.addDate("Sample_Date_Field", Calendar.getInstance().getTime());
            metrics.addCount("Success", 1);
            metrics.addCount("Failure", 0);
            metrics.addCount("Error", 0);
            metrics.addCount("Fault", 0);
            metrics.addCount("Retry", 0);
            metrics.addTime("DatebaseConnectionTime", 500, Unit.MILLIS);
            metrics.addTime("RetryWaitTime", 1, Unit.SECONDS);

            Thread.sleep(10);
            metrics.close();
            i = i + 1;
        }
    }

}
