import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.*;

public abstract class AbstractMetrics implements Metrics {
    static final String STARTLINE = "===================================================================\n";
    static final String EQUALS = "=";
    static final String LINEBREAK = "\n";
    static final String COMMA = ",";
    static final String TIMING = "Timing";
    static final String COUNTERS = "Counters";
    static final String BLANK = " ";
    static final String EOE = "EOE";

    Map<String, String> properties;
    Map<String, Date> dates;
    Map<String, Long> timing;
    Map<String, Integer> counters;
    boolean blockMutates;
    Date startTime;
    Date endTime;
    long timeInMillis;

    public AbstractMetrics() {
        this.blockMutates = true;
        this.properties = new HashMap<String, String>();
        this.dates = new HashMap<String, Date>();
        this.timing = new HashMap<String, Long>();
        this.counters = new HashMap<String, Integer>();
        this.startTime = Calendar.getInstance().getTime();
    }

    public abstract void initializeMetrics();

    public void addProperty(String name, String value) {
        if (this.blockMutates == true) {
            this.properties.put(name, value);
        }
    }

    public void addDate(String name, Date date) {
        if (this.blockMutates == true) {
            this.dates.put(name, date);
        }
    }

    public void addCount(String name, int value) {
        if (this.blockMutates) {
            this.counters.put(name, new Integer(value));
        }
    }

    public void addTime(String name, long value, String unit) {
        if (this.blockMutates == true) {
            long normalizedValue = this.normalizeTimeToMillis(value, unit);
            this.timing.put(name, new Long(normalizedValue));
        }
    }

    public abstract void flushMetrics();

    public void close() {
        this.endTime = Calendar.getInstance().getTime();
        this.timeInMillis = this.endTime.getTime() - this.startTime.getTime();
        this.blockMutates = true;
        this.flushMetrics();
    }

    public String toString() {
        StringBuilder displayString = new StringBuilder("");
        displayString.append(AbstractMetrics.STARTLINE).append(" ");
        AbstractMetrics.appendItem(displayString, "StartTime", this.startTime.toString(), null,
                        AbstractMetrics.LINEBREAK);
        String formattedEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.endTime);

        // String formattedEndTime
        // =datetime.fromtimestamp(this.endTime).strftime('%Y-%m-%d %H:%M:%S');

        displayString = AbstractMetrics.appendItem(displayString, "EndTime", formattedEndTime, null,
                        AbstractMetrics.LINEBREAK);

        displayString = AbstractMetrics.appendItem(displayString, "Time", String.valueOf(this.timeInMillis), "ms",
                        AbstractMetrics.LINEBREAK);

        displayString = AbstractMetrics.appendToDisplayString(displayString, this.properties, null,
                        AbstractMetrics.LINEBREAK);

        displayString = AbstractMetrics.appendToDisplayString(displayString, this.dates, null,
                        AbstractMetrics.LINEBREAK);

        displayString = AbstractMetrics.appenditemsInSameLine(displayString, AbstractMetrics.TIMING, this.timing,
                        Unit.MILLIS);

        displayString = AbstractMetrics.appenditemsInSameLine(displayString, AbstractMetrics.COUNTERS, this.counters,
                        null);

        displayString = displayString.append(AbstractMetrics.EOE).append(" ");

        return displayString.toString();
    }

    public static StringBuilder appendToDisplayString(StringBuilder displayString, Map it, String unit, String delimiter) {
        //
        // for (Entry item : it.entrySet()) {
        // displayString = AbstractMetrics.appendItem(displayString,
        // item.getKey().toString(), item.getValue().toString(), unit,
        // delimiter);
        // }
        Iterator entries = it.entrySet().iterator();
        while (entries.hasNext()) {
            Entry item = (Entry) entries.next();
            displayString = AbstractMetrics.appendItem(displayString, item.getKey().toString(), item.getValue()
                            .toString(), unit, delimiter);
        }
        return displayString;

    }

    public static StringBuilder appendItem(StringBuilder displayString, String key, String value, String unit,
                    String delimiter) {

        displayString.append(key).append(" ");
        displayString.append(AbstractMetrics.EQUALS).append(" ");
        displayString.append(value.toString()).append(" ");
        if (unit != null) {
            displayString.append(AbstractMetrics.BLANK).append(" ");
            displayString.append(unit).append(" ");
        }
        displayString.append(delimiter).append(" ");
        return displayString;
    }

    public static StringBuilder appenditemsInSameLine(StringBuilder displayString, String header, Map counters,
                    String unit) {
        if (counters.size() > 0) {
            displayString.append(header).append(" ");
            displayString.append(AbstractMetrics.EQUALS).append(" ");
            displayString = AbstractMetrics.appendToDisplayString(displayString, counters, unit, AbstractMetrics.COMMA);
            displayString.append(AbstractMetrics.LINEBREAK).append(" ");
        }
        return displayString;

    }

    private long normalizeTimeToMillis(long value, String unit) {
        if (unit == Unit.SECONDS)
            return value * 1000;
        else
            return value;
    }

    public boolean isTimingMetric(String unit) {
        return (unit == Unit.SECONDS || unit == Unit.MILLIS);
    }
}
