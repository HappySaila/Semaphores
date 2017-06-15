/**
 * Created by HappySaila on 6/15/17.
 * Controller for execution tracing. A bitwise integer value is used to represent the level of detail:
 *
 * Bit 1: value 1: Trace Taxi departure.
 * Bit 2: value 2: Trace Taxi arrivals.
 * Bit 3: value 4: Trace Passenger hail.
 * Bit 4: value 8: Trace Passenger embark.
 * Bit 5: value 16: Trace Passenger disembark.
 *
 * A value of say, 11, causes taxi departure, taxi arrival and passenger embark to be traced.
 */
public class Trace {
    public static int TraceIndex = 0;

    public static void Trace(int i){
        i = ((TraceIndex&i)!=0) ? i : 0;
        switch(i){
            case 1:
                TraceDeparture();
                break;
            case 2:
                TraceArrival();
                break;
            case 4:
                TraceHail();
                break;
            case 8:
                TraceEmbark();
                break;
            case 16:
                TraceDisembark();
                break;
        }
    }

    static void TraceDeparture(){
        System.out.println("Departure");
    }
    static void TraceArrival(){
        System.out.println("Arrival");
    }
    static void TraceHail(){
        System.out.println("Hail");
    }
    static void TraceEmbark(){
        System.out.println("Embark");
    }
    static void TraceDisembark(){
        System.out.println("Disembark");
    }


}
