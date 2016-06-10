import java.util.Date;

/**
 * @author shubham
 */
public interface Metrics {

    /**
     * @param name
     * @param value
     */
    void addProperty(String name, String value);

    /**
     * @param name
     * @param date
     */
    public void addDate(String name, Date date);

    public void addCount(String name, int value);

    public void addTime(String name, long value, String unit);

    public void close();

}
