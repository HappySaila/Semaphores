import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Taxi extends Thread{
    private static Taxi instance;

    public int currentLocation;
    private ArrayList<TravelRequest> onBoard;
    private ArrayList<TravelRequest> toFetch;
    private int[] branches;
    private Semaphore sem;
    private Semaphore taxiMove;
    private int numberOfPeople;
    private int completedPeople;
    private boolean isIdle=false;

    public Taxi(int numberBranches, int people){
        currentLocation = 0;
        instance = this;
        numberOfPeople = people;
        PopulateBranches(numberBranches);
        sem = new Semaphore(1);
        taxiMove = new Semaphore(people);

        onBoard = new ArrayList<>();
        toFetch = new ArrayList<>();
    }

    void PopulateBranches(int n){
        branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = i;
        }
    }

    @Override
    public void run(){
        while (completedPeople!=numberOfPeople){
            if (taxiMove.availablePermits() == 0){
                CriticalSection();
            }
        }
        Collections.sort(Trace.traces, new TraceItem.TraceCompatator());
        for (TraceItem t: Trace.traces) {
            System.out.println(t);
        }
        System.out.println("Jobs finished.");
    }

    public void CriticalSection(){
        //drop off current passengers at current location
        int prevOnBoard = onBoard.size();
        int prevToFetch = toFetch.size();
        dropOffPassengers();

        //pick up passengers at current location
        pickUpPassengers();
        if (onBoard.size()!=prevOnBoard || toFetch.size()!=prevToFetch){
            //increment clock for emarking
            Timer.Instance.IncrementTime(1);
        }

        //determine next location
        int nextDestination = determineNextLocation();
        if (nextDestination == -1){
            if(toFetch.size()==0 && onBoard.size()==0){
                //idle
                Timer.Instance.IncrementTime(1);
                if (!isIdle){
                    Trace.TraceIdle();
                }
                isIdle = true;
            } else{
                System.out.println("Could not find a suitable next location.");
                System.exit(0);
            }
        } else {
            isIdle = false;
        }


        //drive to next location
        Drive(nextDestination);

        ReleasePermits();
    }

    private void ReleasePermits(){
        for (int i = 0; i < numberOfPeople; i++) {
            taxiMove.release();
        }
    }

    public void PersonComplete(){
        //person has no more work to do.
        completedPeople++;
    }

    private void pickUpPassengers(){
        for (int i = 0; i < toFetch.size(); i++) {
            TravelRequest t = toFetch.get(i);
            if (t.getSource() == currentLocation){
                //remove and put into onBoard list
                onBoard.add(t);
                toFetch.remove(t);

                //person embarked
                Trace.TraceEmbark(t);
            }
        }
    }

    private void dropOffPassengers(){
        int before = onBoard.size();
        for (int i = 0; i < onBoard.size(); i++) {
            TravelRequest t = onBoard.get(i);
            if (onBoard.get(i).getDestination() == currentLocation){
                Trace.TraceDisembark(t);
                onBoard.remove(t);
                t.getPerson().Disembark();
            }
        }
        if (onBoard.size()==before && !isIdle){
            Trace.TraceDisembark(null);
        }
    }

    private int determineNextLocation(){
        for (int i = 1; i < branches.length+1; i++) {
            int potential = getNextBranch(currentLocation+i);

            //check if anyone is waiting at the next potential location
            for (TravelRequest t: toFetch) {
                if (t.getSource() == potential){
                    return potential;
                }
            }

            //check if anyone needs to be dropped off at the next potential location
            for(TravelRequest t: onBoard){
                if (t.getDestination() == potential){
                    return potential;
                }
            }

        }

        return -1;
    }

    private int getNextBranch(int i){
        if (i<branches.length){
            return i;
        }

        return i%branches.length;
    }

    private void Drive(int i){
        if (isIdle){
            return;
        }
        Trace.TraceDeparture(currentLocation);
        currentLocation = i;
        Timer.Instance.IncrementTime(2);
        Trace.TraceArrival(i);
    }

    public synchronized void Hail(TravelRequest travelRequest){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toFetch.add(travelRequest);
        travelRequest.getPerson().Embark();
        Trace.TraceHail(travelRequest);

        sem.release();
    }

    public static Taxi getInstance(){
        return instance;
    }

    public void Embark(){
        //put all passengers waiting to board taxi into onBoard list
    }

    public void Disembark(){
        //remove all passengers on taxi from onBoard that are destined for taxi current destination

    }

    public Semaphore getTaxiMove() {
        return taxiMove;
    }
}
