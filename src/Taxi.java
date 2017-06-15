import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by HappySaila on 6/15/17.
 */
public class Taxi {
    public static Taxi instance;
    public int currentDestination;
    private ArrayList<TravelRequest> onBoard;
    private ArrayList<TravelRequest> toFetch;
    private int[] branches;
    private Semaphore sem;

    public Taxi(int numberBranches){
        currentDestination = 0;
        instance = this;
        PopulateBranches(numberBranches);
        sem = new Semaphore(1);
    }

    void PopulateBranches(int n){
        branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = i;
        }
    }

    public void start(){
        System.out.println("taxi running...");
    }

    public synchronized void Hail(TravelRequest travelRequest){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toFetch.add(travelRequest);

        sem.release();
    }

    public void Embark(){
        //put all passengers waiting to board taxi into onBoard list
    }

    public void Disembark(){
        //remove all passengers on taxi from onBoard that are destined for taxi current destination

    }

}
