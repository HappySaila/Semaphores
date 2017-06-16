import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by HappySaila on 6/16/17.
 */
public class TraceItem {
    String time;
    String data;

    public TraceItem(String time, String data) {
        this.time = time;
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

    public int GetTimeValue(){
        Scanner s = new Scanner("09:08");
        String[] val = s.next().split(":");
        return Integer.parseInt(val[0])*60 + Integer.parseInt(val[1]);
    }

    @Override
    public String toString() {
        return time + data;
    }

    public static class TraceCompatator implements Comparator<TraceItem> {
        @Override
        public int compare(TraceItem s, TraceItem t) {
            int f = 0;
            try {
                f = s.getTime().compareTo(t.getTime());
            } catch (Exception e) {
                return 0;
            }
            return f;
        }
    }
}
