import com.sun.deploy.trace.TraceLevel;

import java.util.ArrayList;

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
    static ArrayList<TraceItem> traces;

    static void TraceDeparture(int location){
        if ((TraceIndex&1)!=0) {
//            System.out.println(Timer.GetGlobalTime() + " - Leaving " + location + ".");
            traces.add(new TraceItem(Timer.GetGlobalTime(), (" - Leaving " + location + ".")));
        }
    }
    static void TraceArrival(int location){
        if ((TraceIndex&2)!=0) {
//            System.out.println(Timer.GetGlobalTime() + " - Arriving at " + location + ".");
            traces.add(new TraceItem(Timer.GetGlobalTime(), (" - Arriving at " + location + ".")));
        }
    }
    public static void TraceHail(TravelRequest t){
        if ((TraceIndex&4)!=0){
//            System.out.println(Timer.GetGlobalTime() + " - Person "+ t.getPerson().getPersonID() + " hail at " + t.getSource() + ".");
            traces.add(new TraceItem(Timer.GetGlobalTime(), ( " - Person "+ t.getPerson().getPersonID() + " hail at " + t.getSource() + ".")));
        }

    }
    static void TraceEmbark(TravelRequest t){
        if ((TraceIndex&8)!=0){
//            System.out.println(Timer.GetGlobalTime() + " - Person "+ t.getPerson().getPersonID() + " requests "+t.getDestination() + ".");
            traces.add(new TraceItem(Timer.GetGlobalTime(), (" - Person "+ t.getPerson().getPersonID() + " requests "+t.getDestination() + ".")));
        }

    }
    static void TraceDisembark(TravelRequest t){
        if ((TraceIndex&16)!=0){
            if (t!=null){
//                System.out.println(Timer.GetGlobalTime() + " - Person "+ t.getPerson().getPersonID() + " got off.");
                traces.add(new TraceItem(Timer.GetGlobalTime(), (" - Person "+ t.getPerson().getPersonID() + " got off.")));
            } else {
//                System.out.println(Timer.GetGlobalTime() + " - No body got off.");
                traces.add(new TraceItem(Timer.GetGlobalTime(), " - No body got off."));
            }

        }
    }
    static void TraceIdle(){
//        System.out.println(Timer.GetGlobalTime() + " - Taxi idle.");
        traces.add(new TraceItem(Timer.GetGlobalTime(), " - Taxi idle."));
    }


}
