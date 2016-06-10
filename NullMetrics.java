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
        // TODO Auto-generated method stub
        if (this.displayMetricToConsole) {
            System.out.println(this.toString());
        }
    }

}
