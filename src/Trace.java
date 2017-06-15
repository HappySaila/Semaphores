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

    public static void Trace(){
        //check trace level 0
        if ((TraceIndex&0)!=0){
            Trace(1);
        }
        //check trace level 1,2,4,8,16
        for (int i = 1; i < 32; i*=2) {
            if ((TraceIndex&i)!=0){
                Trace(i);
            }
        }
    }

    public static void Trace(int i){
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
