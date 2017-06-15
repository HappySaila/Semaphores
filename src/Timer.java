import java.util.ArrayList;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Timer {
    public static Timer Instance;
    public static int GlobalTimer;
    private ArrayList<Person> employees;

    public static String GetGlobalTime(){
        int hours = 9 + GlobalTimer/60;
        int minutes = GlobalTimer%60;
        hours %= 24;
        return String.format("%02d:%02d", hours, minutes);
    }

    public void IncrementTime(int i){
        GlobalTimer += i;

        for (Person p: employees) {

        }
    }
}
